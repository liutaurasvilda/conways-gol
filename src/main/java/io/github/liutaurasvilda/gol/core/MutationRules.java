package io.github.liutaurasvilda.gol.core;

import java.util.function.BiFunction;

final class MutationRules {

    private final int livingNeighbors;

    private MutationRules(Builder builder) {
        this.livingNeighbors = builder.livingNeighbors;
    }

    int withLivingNeighbors() {
        return livingNeighbors;
    }

    Cell apply(Cell.State state, int neighbors) {
        BiFunction<Cell.State, Integer, Cell> f = (s, n) -> {
            switch (state) {
                case StaALIVE:
                    if (livingNeighbors == 2) return Cell.alive();
                    if (livingNeighbors == 3) return Cell.alive();
                    else return Cell.dead();
                case DEAD:
                    if (livingNeighbors == 3) return Cell.alive();
                    else return Cell.dead();
                default: return Cell.dead();
            }
        };
        return f.apply(state, neighbors);
    }

    final static class Builder {

        private int livingNeighbors;

        Builder() {
        }

        Builder livingNeighbors(int livingNeighbors) {
            this.livingNeighbors = livingNeighbors;
            return this;
        }

        MutationRules build() {
            return new MutationRules(this);
        }
    }
}
