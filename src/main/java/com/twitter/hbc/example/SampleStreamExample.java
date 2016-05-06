/**
 * Copyright 2013 Twitter, Inc.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

package com.twitter.hbc.example;

import com.SpamDetection.TweetInfo;
import com.SpamDetection.TweetToVector;
import com.SpamDetection.isSpam;
import com.arff.file.ArffFile;
import com.com.Json.Parser.ConvertJSON;
import com.com.Json.Parser.TwitterFilterFunction;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesSampleEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.BasicClient;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.apache.spark.unsafe.map.HashMapGrowthStrategy;
import org.codehaus.jettison.json.JSONException;
import scala.Tuple2;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.codehaus.jettison.json.JSONObject;


import java.util.*;
import java.util.regex.Pattern;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.*;
import org.apache.spark.mllib.clustering.StreamingKMeans;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.api.java.*;
import org.apache.log4j.*;


import scala.Tuple2;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.kafka.KafkaUtils;


public class SampleStreamExample {

  private static ArrayList<TweetInfo> tweetList = new ArrayList<TweetInfo>();
  public static String zkQuorum = "localhost:2181"; //is a list of one or more zookeeper servers that make quorum
  public static String group = "spark-consumer-group";   //<group> is the name of kafka consumer group
  public static String topic = "twitter-topic";
  public static Integer numThreads = 2; // is the number of threads the kafka consumer should use
  private static final Pattern SPACE = Pattern.compile(" ");
  static StreamingKMeans model = new StreamingKMeans();

  public static int tweetCount = 100;
  private final ObjectMapper mapper = new ObjectMapper();

  public static void run(String consumerKey, String consumerSecret, String token, String secret) throws InterruptedException {
    // Create an appropriately sized blocking queue
    BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10000);

    SparkConf conf = new SparkConf()
            .setAppName("Spark Streaming")
            .set("spark.driver.allowMultipleContexts", "true")
            .setMaster("local[2]");


/*
    JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(4));
    Logger logger = Logger.getRootLogger();
    logger.setLevel(Level.ERROR);
    Map<String, Integer> topicMap = new HashMap<>();
    topicMap.put(topic, numThreads);
    JavaPairReceiverInputDStream<String, String> messages =
            KafkaUtils.createStream(jssc, zkQuorum, group, topicMap);

*/
    // Define our endpoint: By default, delimited=length is set (we need this for our processor)
    // and stall warnings are on.
    StatusesSampleEndpoint endpoint = new StatusesSampleEndpoint();
    endpoint.stallWarnings(false);

    Authentication auth = new OAuth1(consumerKey, consumerSecret, token, secret);
    //Authentication auth = new com.twitter.hbc.httpclient.auth.BasicAuth(username, password);

    // Create a new BasicClient. By default gzip is enabled.
    BasicClient client = new ClientBuilder()
            .name("sampleExampleClient")
            .hosts(Constants.STREAM_HOST)
            .endpoint(endpoint)
            .authentication(auth)
            .processor(new StringDelimitedProcessor(queue))
            .build();

    // Establish a connection
    client.connect();

    // Do whatever needs to be done with messages
    for (int msgRead = 0; msgRead < tweetCount; msgRead++) {
      if (client.isDone()) {

        System.out.println("Client connection closed unexpectedly: " + client.getExitEvent().getMessage());
        break;
      }

      String msg = queue.poll(5, TimeUnit.SECONDS);

      if (msg == null) {
        System.out.println("Did not receive a message in 5 seconds");
      } else {

        if(!msg.contains("\"delete\""))   // gelen delete'li tweetleri eliyor
        {
            System.out.print(msg);
            JSONObject jsonObject = ConvertJSON.stringToJson(msg);
            isSpam isSpam = new isSpam(jsonObject, tweetList);
        }
      }
    }

    ArffFile arff = new ArffFile(tweetList);
    if(arff.saveArffFile("spam.arff"))
        System.out.println("SPAM.ARFF Succeed");



    client.stop();

    // Print some stats
    System.out.printf("The client read %d messages!\n", client.getStatsTracker().getNumMessages());
  }

  public static void main(String[] args) {
    try {
      SampleStreamExample.run("naAb9r49Na4fRwPaTZnQthypk", "reSg5nAKr2rU1TxeCff3uDrNL1TbizpDsVe0rS8h02mGK5ybmS", "706562595134828548-v46p6ROA9P5OKp3p3UKpnXbLovtru5m", "S3RWCFcx0oGBwmOa4LhHrf5cC5FkEKFXlcObqlIdaoAjh");
    } catch (InterruptedException e) {
      System.out.println(e);
    }
  }
}
