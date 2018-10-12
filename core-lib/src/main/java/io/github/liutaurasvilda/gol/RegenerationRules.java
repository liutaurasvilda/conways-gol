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
            return regenerable -> {
                if (regenerable.equals(Cell.ALIVE)) {
                    if (livingNeighbors == 2) return Cell.ALIVE;
                    if (livingNeighbors == 3) return Cell.ALIVE;
                } else if (regenerable.equals(Cell.EMPTY)) {
                    if (livingNeighbors == 3) return Cell.ALIVE;
                }
                return Cell.EMPTY;
            };
        }
    }
}
