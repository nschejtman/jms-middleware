package com.nschejtman.jms;

import com.nschejtman.client.console.Logger;
import com.nschejtman.model.Instatweet;
import com.nschejtman.model.User;
import com.nschejtman.utils.UserDao;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

public class JMSTweetListener implements MessageListener {
    private User receiver;

    JMSTweetListener(User receiver) {
        this.receiver = receiver;
    }

    public void onMessage(Message message) {
        System.out.println();
        Logger.info("onMessageCalled");
        Logger.info("Receiver --> " + receiver.toString());
        try {
            final Instatweet tweet = message.getBody(Instatweet.class);
            Logger.info(tweet.toString());
            receiver.addToTimeline(tweet);
            UserDao.update(receiver);
            Logger.info("Receiver timeline size " + receiver.getTimeline().size());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
