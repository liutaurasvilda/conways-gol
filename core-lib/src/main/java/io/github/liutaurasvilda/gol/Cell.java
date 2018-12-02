package io.github.liutaurasvilda.gol;

enum Cell {

    ALIVE {
        @Override
        public Cell next(int livingNeighbors) {
            return livingNeighbors == 2 || livingNeighbors == 3
                    ? ALIVE : DEAD;
        }
    },

    DEAD {
        @Override
        public Cell next(int livingNeighbors) {
            return livingNeighbors == 3
                    ? ALIVE : DEAD;
        }
    };

    abstract Cell next(int livingNeighbors);
}
