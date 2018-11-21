package io.github.liutaurasvilda.gol;

enum Cell {

    ALIVE {
        @Override
        public Cell next(int livingNeighbors) {
            return livingNeighbors == 2 || livingNeighbors == 3
                    ? this : DEAD;
        }
    },

    DEAD {
        @Override
        public Cell next(int livingNeighbors) {
            return livingNeighbors == 3
                    ? ALIVE : this;
        }
    };

    abstract Cell next(int neighbors);
}
