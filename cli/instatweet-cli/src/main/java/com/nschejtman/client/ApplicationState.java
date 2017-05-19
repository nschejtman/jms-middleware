package com.nschejtman.client;

public abstract class ApplicationState {
    public abstract ApplicationState command(ApplicationContext context, Command command);

    public boolean isFinal(){
        return false;
    }

    protected ApplicationState error(){
        System.out.println("Invalid command");
        System.out.println();
        return this;
    }

}
