package io.github.liutaurasvilda.gol.core;

import java.util.function.Function;

final class ConwaysRules implements MutationRules {

    private final long livingNeighbors;

    private ConwaysRules(Builder builder) {
        this.livingNeighbors = builder.livingNeighbors;
    }

    @Override
    public Cell apply(Cell cell) {
        Function<Cell, Cell> f = c -> {
            switch (cell.state()) {
                case ALIVE:
                    if (livingNeighbors == 2) return Cell.alive();
                    if (livingNeighbors == 3) return Cell.alive();
                case DEAD:
                    if (livingNeighbors == 3) return Cell.alive();
                default: return Cell.dead();
            }
        };
        return f.apply(cell);
    }

    final static class Builder {

        private long livingNeighbors;

        Builder() {
        }

        Builder withLivingNeighbors(long livingNeighbors) {
            this.livingNeighbors = livingNeighbors;
            return this;
        }

        ConwaysRules build() {
            return new ConwaysRules(this);
        }
    }
}
