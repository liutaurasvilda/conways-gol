package io.github.liutaurasvilda.gol.core;

import java.util.function.Function;

final class MutationRules {

    static final class Builder {

        private long livingNeighbors;

        Builder() {
        }

        Builder withLivingNeighbors(long livingNeighbors) {
            this.livingNeighbors = livingNeighbors;
            return this;
        }

        private Function<Mutable, Mutable> f() {
            return mutable -> {
                if (mutable.equals(Cell.ALIVE)) {
                    if (livingNeighbors == 2) return Cell.ALIVE;
                    if (livingNeighbors == 3) return Cell.ALIVE;
                } else if (mutable.equals(Cell.DEAD)) {
                    if (livingNeighbors == 3) return Cell.ALIVE;
                }
                return Cell.DEAD;
            };
        }

        Function<Mutable, Mutable> build() {
            return f();
        }
    }
}
