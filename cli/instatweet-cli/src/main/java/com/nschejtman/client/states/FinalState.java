package com.nschejtman.client.states;

import com.nschejtman.client.ApplicationState;
import com.nschejtman.client.ApplicationCommand;

public class FinalState extends ApplicationState {

    public ApplicationState commandImpl(ApplicationCommand command) {
        return null;
    }

    @Override
    public boolean isFinal() {
        return true;
    }
}
