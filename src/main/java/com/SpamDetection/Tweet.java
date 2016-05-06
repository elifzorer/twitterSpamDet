package com.SpamDetection;

import java.util.ArrayList;

/**
 * Created by elf on 03.05.2016.
 */
public class Tweet {

    private long tweetID=0;
    private long id=0;
    private double user_created_age=0;
    private double statuses_count=0;
    private double followers_ratio=0;
    private double description_lenght=0;
    private double text_lenght;
    private double hashtag_count=0;
    private String text;
    private ArrayList<String> textBlockList = new ArrayList<String>();

    public long getId() {
        return id;
    }

    public long getTweetID() {
        return tweetID;
    }

    public double getUser_created_age() {
        return user_created_age;
    }

    public double getStatuses_count() {
        return statuses_count;
    }

    public double getFollowers_ratio() {
        return followers_ratio;
    }

    public double getDescription_lenght() {
        return description_lenght;
    }

    public double getText_lenght() {
        return text_lenght;
    }

    public String getText() {
        return text;
    }

    public void setTweetID(long tweetID) {
        this.tweetID = tweetID;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser_created_age(double user_created_age) {
        this.user_created_age = user_created_age;
    }

    public void setFollowers_ratio(double followers_ratio) {
        this.followers_ratio = followers_ratio;
    }

    public void setDescription_lenght(double description_lenght) {
        this.description_lenght = description_lenght;
    }

    public void setStatuses_count(double statuses_count) { this.statuses_count = statuses_count; }

    public void setText_lenght(double text_lenght) {
        this.text_lenght = text_lenght;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getHashtag_count() {
        return hashtag_count;
    }

    public void setHashtag_count(double hashtag_count) {
        this.hashtag_count = hashtag_count;
    }

    public ArrayList<String> getTextBlockList() {
        return textBlockList;
    }

    public void setTextBlockList(ArrayList<String> textBlockList) {
        this.textBlockList = textBlockList;
    }
}
