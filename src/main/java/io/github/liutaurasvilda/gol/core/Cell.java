package io.github.liutaurasvilda.gol.core;

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
        return rules.apply(this.state);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return state == cell.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

    @Override
    public String toString() {
        return state == State.ALIVE ? "0" : ".";
    }
}
