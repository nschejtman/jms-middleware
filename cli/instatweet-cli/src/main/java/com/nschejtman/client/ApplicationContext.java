package com.nschejtman.client;

import com.nschejtman.client.states.InitialState;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ApplicationContext {
    private ApplicationState state;
    private final Map<String, Object> vars;

    public void setPrecommand(String precommand) {
        this.precommand = precommand;
    }

    private String precommand;


    public ApplicationContext() {
        this.state = new InitialState(this);
        precommand = "";
        vars = new HashMap<String, Object>();
    }

    public void run() {
        Color.ANSI_RESET.set();
        System.out.print(precommand + "> ");
        final Scanner sc = new Scanner(System.in);
        Color.ANSI_GREEN.set();
        state = state.command(new Command(sc.nextLine()));
        if (!state.isFinal()) {
            run();
        }
    }


    public void setState(ApplicationState state) {
        this.state = state;
    }

    public Object getVar(String varname) {
        return vars.get(varname);
    }

    public void setVar(String varname, Object var) {
        vars.put(varname, var);
    }

    public void resetVar(String varname){
        vars.remove(varname);
    }


}
