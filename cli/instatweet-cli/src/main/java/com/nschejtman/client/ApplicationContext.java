package com.nschejtman.client;

import com.nschejtman.client.console.Color;
import com.nschejtman.client.states.InitialState;
import com.nschejtman.model.User;

import java.util.Scanner;

public class ApplicationContext {
    private ApplicationState state;
    private User user;

    public void setPrecommand(String precommand) {
        this.precommand = precommand;
    }

    private String precommand;


    public ApplicationContext() {
        this.state = new InitialState(this);
        precommand = "";
    }

    public void run() {
        Color.ANSI_RESET.set();
        System.out.print(precommand + "> ");
        final Scanner sc = new Scanner(System.in);
        Color.ANSI_GREEN.set();
        state = state.command(new ApplicationCommand(sc.nextLine()));
        if (!state.isFinal()) {
            run();
        }
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void resetUser() {
        user = null;
    }


}
