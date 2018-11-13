package io.github.liutaurasvilda.gol;

enum Cell {

    ALIVE, DEAD;

    static Cell inNextGeneration(Cell current, int numberOfLivingNeighbors) {
        if (current == ALIVE) {
            if (numberOfLivingNeighbors == 2) return current;
            if (numberOfLivingNeighbors == 3) return current;
        } else if (current == DEAD) {
            if (numberOfLivingNeighbors == 3) return ALIVE;
        }
        return DEAD;
    }
}
