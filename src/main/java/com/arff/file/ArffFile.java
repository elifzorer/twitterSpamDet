package com.arff.file;

/**
 * Created by elf on 03.05.2016.
 */


import com.SpamDetection.TweetInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ArffFile implements Serializable {
    private String relation = "twitter_spam_data";
    private int controlCount = 8;
    private ArrayList<TweetInfo> TweetList = new ArrayList<>();

    private final String[] control = { "user_created_age", "statuses_count", "description_lenght", "hashtag_count",
            "text_lenght", "text_content", "special_control"};

    public ArrayList<TweetInfo> getTweetList() {
        return TweetList;
    }

    public void setTweetList(ArrayList<TweetInfo> tweetList) {
        this.TweetList = tweetList;
    }

    @Override
    public String toString() {
        /*
        @relation 'twitter_spam_detection'
        @attribute word1 {0,1}
        ...
        @attribute wordn {0,1}

        @data
        0,0,0,0,1,0,1...class
        */

        StringBuilder content = new StringBuilder();
        content.append("@relation '").append(relation).append("'\n");


        for(int i=0; i<controlCount-1; ++i){
            content.append("@attribute ").append(control[i]).append(" NUMERIC").append("\n");
        }

        content.append("@ATTRIBUTE class {spam,legitimate}\n");

        content.append("@data \n");
        if(TweetList != null)
            for(int i=0; i<TweetList.size(); ++i)
                content.append(TweetList.get(i).getUser_created_age()).append(" ").
                        append(TweetList.get(i).getStatuses_count()).append(" ").
                        append(TweetList.get(i).getDescription_lenght()).append(" ").
                        append(TweetList.get(i).getHashtag_count()).append(" ").
                        append(TweetList.get(i).getText_lenght()).append("\n");

        return content.toString();
    }


    public boolean saveArffFile(String savePath){
        try {
            Writer output;
            File file = new File(savePath);
            output = new BufferedWriter(new FileWriter(file));
            output.write(this.toString());
            output.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public ArffFile(ArrayList<TweetInfo> tweetList ){
        setTweetList(tweetList);
    }

}