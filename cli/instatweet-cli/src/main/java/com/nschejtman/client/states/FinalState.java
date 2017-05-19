package com.nschejtman.client.states;

import com.nschejtman.client.ApplicationState;
import com.nschejtman.client.Command;

public class FinalState extends ApplicationState {

    public ApplicationState commandImpl(Command command) {
        return null;
    }

    @Override
    public boolean isFinal() {
        return true;
    }
}
