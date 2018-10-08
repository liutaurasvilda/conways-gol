package gol;

final class MutationRules {

    private final int livingNeighbors;

    private MutationRules(Builder builder) {
        this.livingNeighbors = builder.livingNeighbors;
    }

    int livingNeighbors() {
        return livingNeighbors;
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
