package io.github.liutaurasvilda.gol.core;

import java.util.Objects;

final class Cell implements Mutable {

    private final Phase phase;

    private Cell(Phase phase) {
        this.phase = phase;
    }

    static Mutable alive() {
        return new Cell(Phase.ALIVE);
    }

    static Mutable dead() {
        return new Cell(Phase.DEAD);
    }

    @Override
    public Phase phase() {
        return phase;
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
        return phase == cell.phase;
    }

    @Override
    public int hashCode() {
        return Objects.hash(phase);
    }

    @Override
    public String toString() {
        return phase == Phase.ALIVE ? "0" : ".";
    }
}
