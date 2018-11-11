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

        Function<Cell, Cell> build() {
            return c -> {
                Cell cell = null;
                if (Cell.ALIVE.equals(c)) {
                    if (livingNeighbors == 2) cell = c;
                    if (livingNeighbors == 3) cell = c;
                } else if (livingNeighbors == 3) {
                    cell = Cell.ALIVE;
                }
                return cell;
            };
        }
    }
}
