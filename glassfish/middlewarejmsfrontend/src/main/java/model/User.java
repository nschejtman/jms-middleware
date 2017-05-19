package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private List<User> following;
    private List<Instatweet> timeline;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.following = new ArrayList<User>();
        this.timeline = new ArrayList<Instatweet>();
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<Instatweet> getTimeline() {
        return timeline;
    }

    public void setTimeline(List<Instatweet> timeline) {
        this.timeline = timeline;
    }

    public void addToTimeline(Instatweet tweet){
        timeline.add(tweet);
    }
}
