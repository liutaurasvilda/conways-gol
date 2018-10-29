package io.github.liutaurasvilda.gol;

import java.util.function.Function;

final class RegenerationRules {

    private RegenerationRules() {
    }

    static final class Builder {

        private long livingNeighbors;

        Builder() {
        }

        Builder withLivingNeighbors(long livingNeighbors) {
            this.livingNeighbors = livingNeighbors;
            return this;
        }

        Function<Regenerable, Regenerable> build() {
            return r -> {
                Regenerable regenerable = null;
                if (Cell.ALIVE.equals(r)) {
                    if (livingNeighbors == 2) regenerable = r;
                    if (livingNeighbors == 3) regenerable = r;
                } else if (livingNeighbors == 3) {
                    regenerable = Cell.ALIVE;
                }
                return regenerable;
            };
        }
    }
}
