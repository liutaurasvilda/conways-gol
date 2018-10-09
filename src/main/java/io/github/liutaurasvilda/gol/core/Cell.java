package io.github.liutaurasvilda.gol.core;

import java.util.Objects;

final class Cell implements Mutable {

    private final State state;

    private Cell(State state) {
        this.state = state;
    }

    static Mutable alive() {
        return new Cell(State.ALIVE);
    }

    static Mutable dead() {
        return new Cell(State.DEAD);
    }

    @Override
    public State state() {
        return state;
    }

    @Override
    public Mutable mutate(MutationRules rules) {
        Objects.requireNonNull(rules);
        return rules.apply(this);
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
