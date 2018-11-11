package io.github.liutaurasvilda.gol;

import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {

    @Test
    public void alive_cell_with_two_alive_neighbors_regenerates_to_alive_cell() {
        assertEquals(Cell.ALIVE, Cell.regenerationRules().apply(Cell.ALIVE, 2L));
    }

    @Test
    public void alive_cell_with_three_alive_neighbors_regenerates_to_alive_cell() {
        assertEquals(Cell.ALIVE, Cell.regenerationRules().apply(Cell.ALIVE, 3L));
    }

    @Test
    public void unpopulated_cell_with_three_alive_neighbors_regenerates_to_alive_cell() {
        assertEquals(Cell.ALIVE, Cell.regenerationRules().apply(null, 3L));
    }

    @Test
    public void alive_cell_with_one_alive_neighbor_becomes_unpopulated() {
        assertNull(Cell.regenerationRules().apply(Cell.ALIVE, 1L));
    }
}