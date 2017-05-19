package com.nschejtman.client.states;

import com.nschejtman.client.ApplicationContext;
import com.nschejtman.client.ApplicationState;
import com.nschejtman.client.Color;
import com.nschejtman.client.Command;
import com.nschejtman.jms.JMSHandler;
import com.nschejtman.model.User;
import com.nschejtman.utils.UserDao;

public class InitialState extends ApplicationState {
    private final ApplicationContext context;

    public InitialState(ApplicationContext context) {
        this.context = context;
        context.setPrecommand("");
    }

    public ApplicationState commandImpl(Command command) {
        final String commandName = command.getName();
        switch (commandName) {
            case "help":
                help();
                return this;
            case "login":
                return login(context, command);
            case "register":
                return register(context, command);
            case "exit":
                return new FinalState();
        }
        return notACommand(commandName);
    }

    private void help(){
        System.out.println();
        System.out.println("Available commands");
        System.out.println("--------------------");
        System.out.println("    login -u username -p password");
        System.out.println("    register -u username -p password");
        System.out.println("    exit");
        System.out.println();
        System.out.println(Color.ANSI_BLUE.get() + "[INFO]" + Color.ANSI_RESET.get() + " parameter specification -u -p " +
                "is " +
                "optional");
        System.out.println();
    }

    private ApplicationState login(ApplicationContext context, Command command){
        final String username = command.getParam("-u", "-1");
        final String password = command.getParam("-p", "-2");
        if (UserDao.validate(username, password)) {
            final User user = UserDao.get(username);
            context.setVar("user", user);
            System.out.println("Logged as " + username);
            System.out.println();
            return new LoggedState(context);
        } else {
            System.out.println("Invalid user or password");
            System.out.println();
            return this;
        }
    }

    private ApplicationState register(ApplicationContext context, Command command){
        final String username = command.getParam("-u", "-1");
        final String password = command.getParam("-p", "-2");
        UserDao.register(username, password);
        final User user = new User(username, password);
        final JMSHandler jmsHandler = new JMSHandler();
        jmsHandler.registerUser(user);
        context.setVar("user", user);
        System.out.println();
        System.out.println("Registration successful");
        System.out.println("Logged as " + username);
        System.out.println();
        return new LoggedState(context);
    }


}
