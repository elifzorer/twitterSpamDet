package com.SpamDetection;

/**
 * Created by elf on 03.05.2016.
 */
public class TweetInfo extends Tweet {

    private boolean isSpam;

    public boolean isSpam() {
        return isSpam;
    }

    public void setSpam(boolean spam) {
        isSpam = spam;
    }

}
