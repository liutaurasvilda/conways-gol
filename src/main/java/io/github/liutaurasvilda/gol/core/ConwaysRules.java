package io.github.liutaurasvilda.gol.core;

import java.util.function.BiFunction;

final class ConwaysRules implements MutationRules {

    private final int livingNeighbors;

    private ConwaysRules(Builder builder) {
        this.livingNeighbors = builder.livingNeighbors;
    }

    @Override
    public Cell apply(Cell.State state) {
        BiFunction<Cell.State, Integer, Cell> f = (s, n) -> {
            switch (state) {
                case ALIVE:
                    if (livingNeighbors == 2) return Cell.alive();
                    if (livingNeighbors == 3) return Cell.alive();
                case DEAD:
                    if (livingNeighbors == 3) return Cell.alive();
                default: return Cell.dead();
            }
        };
        return f.apply(state, livingNeighbors);
    }

    final static class Builder {

        private int livingNeighbors;

        Builder() {
        }

        Builder withLivingNeighbors(int livingNeighbors) {
            this.livingNeighbors = livingNeighbors;
            return this;
        }

        ConwaysRules build() {
            return new ConwaysRules(this);
        }
    }
}