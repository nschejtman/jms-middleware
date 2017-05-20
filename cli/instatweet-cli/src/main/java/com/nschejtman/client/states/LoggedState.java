package com.nschejtman.client.states;

import com.nschejtman.client.ApplicationContext;
import com.nschejtman.client.ApplicationState;
import com.nschejtman.client.console.Color;
import com.nschejtman.client.ApplicationCommand;
import com.nschejtman.jms.JMSHandler;
import com.nschejtman.model.Instatweet;
import com.nschejtman.model.User;
import com.nschejtman.utils.UserDao;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.List;

public class LoggedState extends ApplicationState {
    private final ApplicationContext context;

    LoggedState(ApplicationContext context) {
        this.context = context;
        final String precommand = Color.ANSI_BLUE.get() +
                context.getUser().getUsername() +
                Color.ANSI_RESET.get();
        context.setPrecommand(precommand);
    }

    public ApplicationState commandImpl(ApplicationCommand command) {
        final String commandName = command.getName();
        switch (commandName) {
            case "help":
                help();
                return this;
            case "whoami":
                whoami();
                return this;
            case "logout":
                return logout();
            case "search":
                return search(command);
            case "follow":
                return follow(command);
            case "tweet":
                return tweet(command);
            case "timeline":
                return timeline();
            case "exit":
                return new FinalState();
        }
        return notACommand(commandName);
    }

    private void help() {
        System.out.println();
        System.out.println("Available commands");
        System.out.println("--------------------");
        System.out.println("    whoami");
        System.out.println("    timeline");
        System.out.println("    tweet -t text -i path/to/image");
        System.out.println("    follow -u username");
        System.out.println("    search -u username");
        System.out.println("    logout");
        System.out.println("    exit");
        System.out.println();
    }

    private void whoami() {
        final User user = context.getUser();
        System.out.println(user.getUsername());
        System.out.println();
    }

    private ApplicationState logout() {
        context.resetUser();
        context.setPrecommand("");
        System.out.println();
        System.out.println("Logged out successfully");
        System.out.println();
        return new InitialState(context);
    }

    private ApplicationState search(ApplicationCommand command) {
        final String searchText = command.getParam("-u", "-1");
        final List<String> results = UserDao.search(searchText);
        System.out.println();
        for (String result : results) {
            System.out.println(result);
        }
        System.out.println();
        return this;
    }

    private ApplicationState follow(ApplicationCommand command) {
        final String username = command.getParam("-u", "-1");
        final User followed = UserDao.get(username);
        if (followed != null) {
            final JMSHandler jmsHandler = new JMSHandler();
            final User user = context.getUser();
            jmsHandler.follow(user, followed);
            System.out.println();
            System.out.println("Following " + username);
            System.out.println();
            return this;
        } else {
            return error("User " + username + "not found");
        }
    }

    private ApplicationState tweet(ApplicationCommand command) {
        final String text = command.getParam("-t", "-1");
        final String imagePath = command.getParam("-p", "-2");
        final User user = context.getUser();
        final Instatweet instatweet;
        try {
            instatweet = new Instatweet(user, text, imagePath, DateTime.now());
            final JMSHandler jmsHandler = new JMSHandler();
            jmsHandler.tweet(instatweet);
        } catch (IOException | IllegalArgumentException e) {
            error(e.getMessage());
        }
        return this;
    }

    private ApplicationState timeline() {
        System.out.println();
        final User user = context.getUser();
        final List<Instatweet> timeline = user.getTimeline();
        for (Instatweet instatweet : timeline) {
            System.out.print(instatweet.getUser().getUsername() + "     |       ");
            System.out.print(instatweet.getDateTime().toString("hh:mm dd/MM/yyyy") + "\n");
            System.out.println(instatweet.getText());
            System.out.println(instatweet.getImageName());
            System.out.println();
            System.out.println();
        }
        System.out.println();
        return this;
    }

}

