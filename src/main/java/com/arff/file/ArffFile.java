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
    ArrayList<String> TweetValues;

    private final String[] control = {  "LenghtOfProfileName" , "LenghtOfProfileDescription", "NumberOfFollowings", "NumberOfFollowers",
                                        "NumberOfTweetsPost" , "AgeOfTheUserAccount", "RatioOfNumberOfFollowingsAndFollowers", "ReputationOfTheUser",
                                        "FollowingRate" , "NumberOfWords", "NumberOfCharacters",
                                        "NumberOfCapitalizationCharacter" , "NumberOfCapitalizationWordPerWord", "MaxWordLenght", "MeanWordLenght",
                                        "NumberOfExclamationMark" , "NumberOfQuestionMark", "NumberOfURL", "URLPerWord",
                                        "NumberOfHashtag" , "HashtagPerWord", "NumberOfMention", "MentionPerWord",
                                        "NumberOfSpamWord", "SpamWordPerWord"
                                       };


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


        for(int i=0; i<control.length; ++i){
            content.append("@attribute ").append(control[i]).append(" NUMERIC").append("\n");
        }

        content.append("@ATTRIBUTE class {spam,legitimate}\n");

        content.append("@data \n");

        for(int i=0; i<1000; ++i)
            content.append(TweetValues.get(i));

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


    public ArffFile(ArrayList<String> TweetValues){
        this.TweetValues = TweetValues;
    }

}