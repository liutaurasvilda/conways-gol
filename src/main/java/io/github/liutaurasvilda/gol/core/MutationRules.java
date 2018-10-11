package io.github.liutaurasvilda.gol.core;

import java.util.function.Function;

final class MutationRules {

    private final long livingNeighbors;

    private MutationRules(Builder builder) {
        this.livingNeighbors = builder.livingNeighbors;
    }

    private Function<Mutable, Mutable> get() {
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

    static final class Builder {

        private long livingNeighbors;

        Builder() {
        }

        Builder withLivingNeighbors(long livingNeighbors) {
            this.livingNeighbors = livingNeighbors;
            return this;
        }

        Function<Mutable, Mutable> build() {
            return new MutationRules(this).get();
        }
    }
}
