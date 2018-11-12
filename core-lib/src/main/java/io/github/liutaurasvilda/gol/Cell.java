package io.github.liutaurasvilda.gol;

import java.util.function.BiFunction;

enum Cell {

    ALIVE;

    static BiFunction<Cell, Integer, Cell> regeneration() {
        return (currentCell, numberOfLivingNeighbors) -> {
            Cell newCell = null;
            if (ALIVE.equals(currentCell)) {
                if (numberOfLivingNeighbors == 2) newCell = ALIVE;
                if (numberOfLivingNeighbors == 3) newCell = ALIVE;
            } else if (numberOfLivingNeighbors == 3) {
                newCell = ALIVE;
            }
            return newCell;
        };
    }
}
