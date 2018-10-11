package io.github.liutaurasvilda.gol.core;

import java.util.function.Function;

final class MutationRules {

    private MutationRules() {
    }

    static final class Builder {

        private long livingNeighbors;

        Builder() {
        }

        Builder withLivingNeighbors(long livingNeighbors) {
            this.livingNeighbors = livingNeighbors;
            return this;
        }

        Function<Mutable, Mutable> build() {
            return mutable -> {
                if (mutable.equals(Cell.ALIVE)) {
                    if (livingNeighbors == 2) return Cell.ALIVE;
                    if (livingNeighbors == 3) return Cell.ALIVE;
                } else if (mutable.equals(Cell.EMPTY)) {
                    if (livingNeighbors == 3) return Cell.ALIVE;
                }
                return Cell.EMPTY;
            };
        }
    }
}
