package io.github.liutaurasvilda.gol;

import java.util.function.BiFunction;

enum Cell {

    ALIVE;

    static BiFunction<Boolean, Long, Cell> regenerate() {
        return (isAlive, numberOfLivingNeighbors) -> {
            Cell cell = null;
            if (isAlive) {
                if (numberOfLivingNeighbors == 2) cell = ALIVE;
                if (numberOfLivingNeighbors == 3) cell = ALIVE;
            } else if (numberOfLivingNeighbors == 3) {
                cell = ALIVE;
            }
            return cell;
        };
    }
}
