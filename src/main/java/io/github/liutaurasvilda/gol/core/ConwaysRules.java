package io.github.liutaurasvilda.gol.core;

import java.util.Objects;
import java.util.function.Function;

final class ConwaysRules implements MutationRules {

    private final long livingNeighbors;

    private ConwaysRules(Builder builder) {
        this.livingNeighbors = builder.livingNeighbors;
    }

    @Override
    public Mutable apply(Mutable mutable) {
        Function<Mutable, Mutable> f = m -> {
            if (m.equals(Cell.ALIVE)) {
                if (livingNeighbors == 2) return Cell.ALIVE;
                if (livingNeighbors == 3) return Cell.ALIVE;
            } else if (m.equals(Cell.DEAD)) {
                if (livingNeighbors == 3) return Cell.ALIVE;
            }
            return Cell.DEAD;
        };
        return f.apply(Objects.requireNonNull(mutable));
    }

    static final class Builder {

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
