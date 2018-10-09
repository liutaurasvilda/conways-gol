package io.github.liutaurasvilda.gol.core;

interface Mutable {

    enum Phase {
        ALIVE, DEAD
    }

    Phase phase();

    Mutable mutate(MutationRules rules);
}
