package io.github.liutaurasvilda.gol.core;

interface Mutable {

    enum State {
        ALIVE, DEAD
    }

    State state();

    Mutable mutate(MutationRules rules);
}
