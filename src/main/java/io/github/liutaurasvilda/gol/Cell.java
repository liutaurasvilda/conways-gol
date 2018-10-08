package io.github.liutaurasvilda.gol;

import java.util.Objects;

final class Cell {

    enum State {
        ALIVE, DEAD
    }

    private final State state;

    private Cell(State state) {
        this.state = state;
    }

    State state() {
        return state;
    }

    static Cell alive() {
        return new Cell(State.ALIVE);
    }

    static Cell dead() {
        return new Cell(State.DEAD);
    }

    Cell mutate(MutationRules rules) {
        Objects.requireNonNull(rules);
        int livingNeighbors = rules.livingNeighbors();
        if (state == State.ALIVE && livingNeighbors < 2) {
            return dead();
        } else if (state == State.ALIVE && (livingNeighbors == 2 || livingNeighbors == 3)) {
            return alive();
        } else if (state == State.DEAD && livingNeighbors == 3) {
            return alive();
        } else {
            return dead();
        }
    }
}
