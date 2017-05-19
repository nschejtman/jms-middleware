package com.nschejtman.client.states;

import com.nschejtman.client.ApplicationContext;
import com.nschejtman.client.ApplicationState;
import com.nschejtman.client.Color;
import com.nschejtman.client.Command;
import com.nschejtman.model.User;

public class LoggedState extends ApplicationState {
    private final ApplicationContext context;

    public LoggedState(ApplicationContext context) {
        this.context = context;
        final String precommand = Color.ANSI_BLUE.get() +
                ((User) context.getVar("user")).getUsername() +
                Color.ANSI_RESET.get();
        context.setPrecommand(precommand);
    }

    public ApplicationState commandImpl(Command command) {
        final String commandName = command.getName();
        if (commandName.equals("help")) {
            help();
            return this;
        } else if(commandName.equals("whoami")){
            whoami();
            return this;
        } else if(commandName.equals("logout")){
            return logout();
        }
        return error(commandName);
    }

    private void help() {
        System.out.println();
        System.out.println("Available commands");
        System.out.println("--------------------");
        System.out.println("whoami");
        System.out.println("tweet -t text -i path/to/image");
        System.out.println("follow -u username");
        System.out.println("search -u username");
        System.out.println("logout");
        System.out.println();
    }

    private void whoami(){
        final User user = (User) context.getVar("user");
        System.out.println(user.getUsername());
        System.out.println();
    }

    private ApplicationState logout(){
        context.resetVar("user");
        context.setPrecommand("");
        System.out.println();
        System.out.println("Logged out successfully");
        System.out.println();
        return new InitialState(context);
    }


}

