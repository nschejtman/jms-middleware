package com.nschejtman.client;

public abstract class ApplicationState {
    public abstract ApplicationState commandImpl(Command command);

    public boolean isFinal() {
        return false;
    }


    ApplicationState command(Command command) {
        Color.ANSI_RESET.set();
        return commandImpl(command);
    }

    protected ApplicationState error(String commandName) {
        Color.ANSI_RED.set();
        System.out.println("Invalid command " + commandName);
        Color.ANSI_RESET.set();
        System.out.println();
        return this;
    }

}
