package io.github.liutaurasvilda.gol;

import java.util.function.BiFunction;

enum Cell {

    ALIVE;

    /* currentCell can be either ALIVE or unpopulated (null) */
    static BiFunction<Cell, Long, Cell> regenerationRules() {
        return (currentCell, numberOfLivingNeighbors) -> {
            Cell newCell = null;
            if (Cell.ALIVE.equals(currentCell)) {
                if (numberOfLivingNeighbors == 2) newCell = currentCell;
                if (numberOfLivingNeighbors == 3) newCell = currentCell;
            } else if (numberOfLivingNeighbors == 3) {
                newCell = Cell.ALIVE;
            }
            return newCell;
        };
    }
}
