package com.nschejtman.client.states;

import com.nschejtman.client.ApplicationContext;
import com.nschejtman.client.ApplicationState;
import com.nschejtman.client.Command;
import com.nschejtman.model.User;

public class LoggedState extends ApplicationState {


    public ApplicationState command(ApplicationContext context, Command command) {
        final String commandName = command.getName();
        if (commandName.equals("help")) {
            help();
            return this;
        } else if(commandName.equals("whoami")){
            whoami(context);
            return this;
        }
        return error();
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

    private void whoami(ApplicationContext context){
        final User user = (User) context.getVar("user");
        System.out.println(user.getUsername());
        System.out.println();
    }


}

