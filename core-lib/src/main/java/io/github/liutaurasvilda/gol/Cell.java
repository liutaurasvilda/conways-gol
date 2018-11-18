package io.github.liutaurasvilda.gol;

public enum Cell {

    ALIVE {
        @Override
        public Cell next(int neighbors) {
            return neighbors == 2 || neighbors == 3
                    ? this : DEAD;
        }
    },

    DEAD {
        @Override
        public Cell next(int neighbors) {
            return neighbors == 3
                    ? ALIVE : this;
        }
    };

    abstract Cell next(int neighbors);
}
