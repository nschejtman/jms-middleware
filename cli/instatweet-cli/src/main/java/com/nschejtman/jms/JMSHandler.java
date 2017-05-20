package com.nschejtman.jms;


import com.nschejtman.model.Instatweet;
import com.nschejtman.model.User;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.TemporaryTopic;
import java.util.HashMap;
import java.util.Map;

public class JMSHandler {
    private static JMSContext context;
    private static Map<String, TemporaryTopic> userTopics = new HashMap<>();

    public void tweet(Instatweet tweet) {
        final TemporaryTopic topic = userTopics.get(tweet.getUser().getUsername());
        final JMSProducer producer = context.createProducer();
        producer.send(topic, tweet);
    }

    public void follow(User follower, User followed) {
        final TemporaryTopic topic = userTopics.get(followed.getUsername());
        final JMSConsumer consumer = context.createConsumer(topic);
        consumer.setMessageListener(new JMSTweetListener(follower));
    }

    public void registerUser(User user) {
        final TemporaryTopic temporaryTopic = context.createTemporaryTopic();
        userTopics.put(user.getUsername(), temporaryTopic);
    }

    public static void setContext(JMSContext context) {
        JMSHandler.context = context;
    }
}
