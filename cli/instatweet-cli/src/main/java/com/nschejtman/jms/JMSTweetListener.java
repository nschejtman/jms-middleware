package com.nschejtman.jms;

import com.nschejtman.model.Instatweet;
import com.nschejtman.model.User;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

public class JMSTweetListener implements MessageListener {
    private User receiver;

    JMSTweetListener(User receiver) {
        this.receiver = receiver;
    }

    public void onMessage(Message message) {
        try {
            final Instatweet tweet = message.getBody(Instatweet.class);
            receiver.addToTimeline(tweet);
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
