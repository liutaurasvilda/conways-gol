package io.github.liutaurasvilda.gol;

import org.junit.jupiter.api.Test;

import static io.github.liutaurasvilda.gol.Cell.ALIVE;
import static io.github.liutaurasvilda.gol.Cell.DEAD;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CellTest {

    @Test
    public void alive_cell_with_less_than_2_living_neighbors_dies() {
        assertEquals(DEAD, ALIVE.next(1));
    }

    @Test
    public void alive_cell_with_2_or_3_living_neighbors_lives_on() {
        assertEquals(ALIVE, ALIVE.next(2));
        assertEquals(ALIVE, ALIVE.next(3));
    }

    @Test
    public void alive_cell_with_more_than_3_living_neighbors_dies() {
        assertEquals(DEAD, ALIVE.next(4));
    }

    @Test
    public void dead_cell_with_exactly_3_living_neighbors_comes_to_life() {
        assertEquals(ALIVE, DEAD.next(3));
    }
}