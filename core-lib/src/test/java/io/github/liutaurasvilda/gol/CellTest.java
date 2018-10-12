package io.github.liutaurasvilda.gol;

import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {

    private final RegenerationRules.Builder rules = new RegenerationRules.Builder();

    @Test
    public void alive_cell_regenerates_to_empty_with_less_than_two_alive_neighbors() {
        assertEquals(Cell.EMPTY, Cell.ALIVE.regenerate(rules.withLivingNeighbors(1).build()));
    }

    @Test
    public void alive_cell_stays_alive_with_two_alive_neighbors() {
        assertEquals(Cell.ALIVE, Cell.ALIVE.regenerate(rules.withLivingNeighbors(2).build()));
    }

    @Test
    public void alive_cell_stays_alive_with_three_alive_neighbors() {
        assertEquals(Cell.ALIVE, Cell.ALIVE.regenerate(rules.withLivingNeighbors(3).build()));
    }

    @Test
    public void alive_cell_regenerates_to_empty_with_more_than_three_alive_neighbors() {
        assertEquals(Cell.EMPTY, Cell.ALIVE.regenerate(rules.withLivingNeighbors(4).build()));
    }

    @Test
    public void empty_cell_regenerates_to_alive_with_three_alive_neighbors() {
        assertEquals(Cell.ALIVE, Cell.EMPTY.regenerate(rules.withLivingNeighbors(3).build()));
    }

    @Test
    public void empty_cell_stays_empty_with_one_alive_neighbor() {
        assertEquals(Cell.EMPTY, Cell.EMPTY.regenerate(rules.withLivingNeighbors(1).build()));
    }

    @Test
    public void empty_cell_stays_empty_with_four_alive_neighbors() {
        assertEquals(Cell.EMPTY, Cell.EMPTY.regenerate(rules.withLivingNeighbors(4).build()));
    }

    @Test(expected = NullPointerException.class)
    public void throws_exception_if_has_no_regeneration_rules_provided_to_regenerate() {
        Cell.ALIVE.regenerate(null);
    }

    @Test
    public void alive_cell_toString() {
        assertEquals("0", Cell.ALIVE.toString());
    }

    @Test
    public void empty_cell_toString() {
        assertEquals(".", Cell.EMPTY.toString());
    }
}
