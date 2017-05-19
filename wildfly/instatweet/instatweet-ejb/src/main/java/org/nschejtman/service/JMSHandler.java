package org.nschejtman.service;

import org.nschejtman.model.Instatweet;
import org.nschejtman.model.User;

import javax.annotation.Resource;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSHandler {

    @Resource(lookup = "java:/ConnectionFactory")
    private ConnectionFactory cf;

    public void tweet(Instatweet tweet) {
        final Topic topic;
        try {
            topic = InitialContext.doLookup("jms/user/" + tweet.getUser().getUsername());
            final JMSContext context = cf.createContext();
            final JMSProducer producer = context.createProducer();
            producer.send(topic, tweet);
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    public void follow(User follower, User followed) {
        final Topic topic;
        try {
            topic = InitialContext.doLookup("jms/user/" + followed.getUsername());
            final JMSContext context = cf.createContext();
            final JMSConsumer consumer = context.createConsumer(topic);
            consumer.setMessageListener(new JMSTweetListener(follower));
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void registerUser(User user) {
        final JMSContext context = cf.createContext();
        final Topic topic = context.createTopic("jms/user/" + user.getUsername());
    }
}
