package io.github.liutaurasvilda.gol.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {

    @Test
    public void alive_cell_mutates_to_dead_with_less_than_two_alive_neighbors() {
        MutationRules.Builder rules = new MutationRules.Builder().withLivingNeighbors(1);
        assertEquals(Cell.DEAD, Cell.ALIVE.mutate(rules.build()));
    }

    @Test
    public void alive_cell_stays_alive_with_two_alive_neighbors() {
        MutationRules.Builder rules = new MutationRules.Builder().withLivingNeighbors(2);
        assertEquals(Cell.ALIVE, Cell.ALIVE.mutate(rules.build()));
    }

    @Test
    public void alive_cell_stays_alive_with_three_alive_neighbors() {
        MutationRules.Builder rules = new MutationRules.Builder().withLivingNeighbors(3);
        assertEquals(Cell.ALIVE, Cell.ALIVE.mutate(rules.build()));
    }

    @Test
    public void alive_cell_mutates_to_dead_with_more_than_three_alive_neighbors() {
        MutationRules.Builder rules = new MutationRules.Builder().withLivingNeighbors(4);
        assertEquals(Cell.DEAD, Cell.ALIVE.mutate(rules.build()));
    }

    @Test
    public void dead_cell_mutates_to_alive_with_three_alive_neighbors() {
        MutationRules.Builder rules = new MutationRules.Builder().withLivingNeighbors(3);
        assertEquals(Cell.ALIVE, Cell.DEAD.mutate(rules.build()));
    }

    @Test
    public void dead_cell_stays_dead_with_one_alive_neighbor() {
        MutationRules.Builder rules = new MutationRules.Builder().withLivingNeighbors(1);
        assertEquals(Cell.DEAD, Cell.DEAD.mutate(rules.build()));
    }

    @Test
    public void dead_cell_stays_dead_with_four_alive_neighbors() {
        MutationRules.Builder rules = new MutationRules.Builder().withLivingNeighbors(4);
        assertEquals(Cell.DEAD, Cell.DEAD.mutate(rules.build()));
    }

    @Test(expected = NullPointerException.class)
    public void throws_exception_if_has_no_mutation_rules_provided_to_mutate() {
        Cell.ALIVE.mutate(null);
    }

    @Test
    public void live_cell_toString() {
        assertEquals("0", Cell.ALIVE.toString());
    }

    @Test
    public void dead_cell_toString() {
        assertEquals(".", Cell.DEAD.toString());
    }
}