package com.SpamDetection;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by elf on 03.05.2016.
 */
public class isSpam {

    private JSONObject user = null;
    private TweetInfo tweetInfo;
    public JSONObject tmpObj = null;


    public isSpam(JSONObject jsonObject, ArrayList<TweetInfo> tweetList){

        this.tweetInfo = new TweetInfo();
        if(jsonObject != null){
            try {
               // user = jsonObject.getJSONArray("user");
                user = jsonObject.getJSONObject("user");
                findUser(user);
                hashtag_count(jsonObject);
                getText(jsonObject);
                new TextControl(tweetInfo.getText(), tweetInfo.getTextBlockList());

                System.out.println("isSpam: " + tweetInfo.isSpam() + "  " +
                                   "HCount: " + tweetInfo.getHashtag_count() + "  " +
                                   "TLen: " + tweetInfo.getText_lenght() + "   " +
                                   "UCAge: " + tweetInfo.getUser_created_age() + "   " +
                                   "SCount: " + tweetInfo.getStatuses_count() + "   " +
                                   "FolRat: " + tweetInfo.getFollowers_ratio() + "   " +
                                   "DLen: " + tweetInfo.getDescription_lenght() + "\n" +
                                   "Text: " + tweetInfo.getText() + "\n" +
                                   "Text Block List: " + tweetInfo.getTextBlockList()
                );

                tweetList.add(tweetInfo);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public void findUser(JSONObject user) throws JSONException {

        if(user != null) {

                userCreatedAge(user);
                statuses_count(user);
                followers_ratio(user);
                description_lenght(user);
        }
    }


    public void userCreatedAge(JSONObject user) throws JSONException {

        if(user != null) {
            try {
                int user_created_age = TwitterDateParser.parseTwitterUTC((String) user.get("created_at"));
                double user_created_age_result = 0;

                // yanlis bir yaklasimimiz var burada. inceleyelim
                if(user_created_age < 1)  user_created_age_result = 1.0;
                else if(user_created_age < 2)  user_created_age_result = 0.66;
                else if(user_created_age < 3)  user_created_age_result = 0.33;
                else user_created_age_result = 0.0;
                tweetInfo.setUser_created_age(user_created_age_result);

            } catch (ParseException e) {
                e.printStackTrace();
            }


        }
    }

    public void getText(JSONObject obj) throws JSONException {

        if(obj != null) {
            tweetInfo.setText(obj.get("text").toString());
        }
    }

    public void statuses_count(JSONObject user) throws JSONException {

        if(user != null) {
            // Users That Create Little Content =  false if users who have only ever created less than 50 tweets
            int statuses_count = (int)user.get("statuses_count");

            double statuses_count_result = 0;
            if(statuses_count < 50) statuses_count_result = 1.0;
            else if(statuses_count < 100) statuses_count_result = 0.75;
            else if(statuses_count < 200) statuses_count_result = 0.50;
            else if(statuses_count < 300) statuses_count_result = 0.25;
            else statuses_count_result = 0.0;
            tweetInfo.setStatuses_count(statuses_count_result);
        }
    }

    public void followers_ratio(JSONObject user) throws JSONException {

        int followers_ratioe = (int)user.get("followers_count");

        if(user != null) {
            // false if Users With Few Followers and big friends
            //float followers_ratio = ((float)user.get("followers_count") + 1) / ((float)user.get("friends_count") + 1);
            //float followers_ratio_result = (followers_ratio > 0.01)? 1 : 0;
            //tweetInfo.setFollowers_ratio(followers_ratio_result);
        }
    }

    public void description_lenght(JSONObject user) throws JSONException {
        int description_lenght=0;
        if(user != null) {
            description_lenght = (user.get("description").toString().length());
            // false Users With Short Descriptions
            double description_length_result = 0.0;

            if(description_lenght < 20) description_length_result = 1.0;
            else if(description_lenght < 40) description_length_result = 0.66;
            else if(description_lenght < 60) description_length_result = 0.33;
            else description_length_result = 0.0;
            tweetInfo.setDescription_lenght(description_length_result);

        }
    }


    public void hashtag_count(JSONObject root) throws JSONException {

        tweetInfo.setText(root.get("text").toString());
        //System.out.println(text);
        int text_lenght = tweetInfo.getText().length();

        String[] result = tweetInfo.getText().split("\\s");
        int hashtag_count = 0;
        for (int x = 0; x < result.length; x++) {
            if (result[x].startsWith("#")) {
                hashtag_count++;
            }
        }

        double hashtag_count_result=0.0;

        // hashtag countlar 0-1 araligina map ediliyor
        if(hashtag_count == 0)
            hashtag_count_result = 0.0;
        else if(hashtag_count == 1)  hashtag_count_result = 0.25;
        else if(hashtag_count == 2)  hashtag_count_result = 0.50;
        else if(hashtag_count == 3)  hashtag_count_result = 0.75;
        else if(hashtag_count >= 4)  hashtag_count_result = 1.0;
        tweetInfo.setHashtag_count(hashtag_count_result);

        // false if Messages with Short Content Length

       // double text_lenght_result = Double.valueOf(df.format(text_lenght/140.0));
        double text_lenght_result = Math.round((text_lenght/140.0) * 100.0) / 100.0;
        tweetInfo.setText_lenght(text_lenght_result);

    }



    public TweetInfo getTweetInfo() {
        return tweetInfo;
    }

    public void setTweetInfo(TweetInfo tweetInfo) {
        this.tweetInfo = tweetInfo;
    }
}
