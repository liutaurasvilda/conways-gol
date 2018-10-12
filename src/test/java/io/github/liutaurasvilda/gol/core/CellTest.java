package io.github.liutaurasvilda.gol.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {

    private final MutationRules.Builder rules = new MutationRules.Builder();

    @Test
    public void alive_cell_mutates_to_empty_with_less_than_two_alive_neighbors() {
        assertEquals(Cell.EMPTY, Cell.ALIVE.mutate(rules.withLivingNeighbors(1).build()));
    }

    @Test
    public void alive_cell_stays_alive_with_two_alive_neighbors() {
        assertEquals(Cell.ALIVE, Cell.ALIVE.mutate(rules.withLivingNeighbors(2).build()));
    }

    @Test
    public void alive_cell_stays_alive_with_three_alive_neighbors() {
        assertEquals(Cell.ALIVE, Cell.ALIVE.mutate(rules.withLivingNeighbors(3).build()));
    }

    @Test
    public void alive_cell_mutates_to_empty_with_more_than_three_alive_neighbors() {
        assertEquals(Cell.EMPTY, Cell.ALIVE.mutate(rules.withLivingNeighbors(4).build()));
    }

    @Test
    public void empty_cell_mutates_to_alive_with_three_alive_neighbors() {
        assertEquals(Cell.ALIVE, Cell.EMPTY.mutate(rules.withLivingNeighbors(3).build()));
    }

    @Test
    public void empty_cell_stays_empty_with_one_alive_neighbor() {
        assertEquals(Cell.EMPTY, Cell.EMPTY.mutate(rules.withLivingNeighbors(1).build()));
    }

    @Test
    public void empty_cell_stays_empty_with_four_alive_neighbors() {
        assertEquals(Cell.EMPTY, Cell.EMPTY.mutate(rules.withLivingNeighbors(4).build()));
    }

    @Test(expected = NullPointerException.class)
    public void throws_exception_if_has_no_mutation_rules_provided_to_mutate() {
        Cell.ALIVE.mutate(null);
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
