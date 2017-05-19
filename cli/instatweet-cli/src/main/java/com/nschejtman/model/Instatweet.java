package com.nschejtman.model;

import org.joda.time.DateTime;

import java.io.Serializable;

public class Instatweet implements Comparable<Instatweet>, Serializable {
    private User user;
    private String text;
    private String picture;
    private DateTime dateTime;

    public Instatweet() {
    }

    public Instatweet(User user, String text, String picture, DateTime dateTime) {
        this.user = user;
        this.text = text;
        this.picture = picture;
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int compareTo(Instatweet o) {
        return dateTime.compareTo(o.dateTime);
    }
}
