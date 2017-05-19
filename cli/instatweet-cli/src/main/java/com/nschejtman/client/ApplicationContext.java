package com.nschejtman.client;

import com.nschejtman.client.states.InitialState;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ApplicationContext {
    private ApplicationState state;
    private final Map<String, Object>  vars;


    public ApplicationContext() {
        this.state = new InitialState();
        vars = new HashMap<String, Object>();
    }

    public void run(){
        final Scanner sc = new Scanner(System.in);
        state = state.command(this , new Command(sc.nextLine()));
        if (!state.isFinal()){
            run();
        }
    }


    public void setState(ApplicationState state) {
        this.state = state;
    }

    public Object getVar(String varname){
        return vars.get(varname);
    }

    public void addVar(String varname, Object var){
        vars.put(varname, var);
    }



}
