package io.github.liutaurasvilda.gol.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MutationRulesTest {

    @Test
    public void rules_with_less_than_two_alive_neighbors_applied_to_alive_cell_returns_empty_cell() {
        MutationRules.Builder rules = new MutationRules.Builder().withLivingNeighbors(1);
        assertEquals(Cell.EMPTY, rules.build().apply(Cell.ALIVE));
    }

    @Test
    public void rules_with_two_alive_neighbors_applied_to_alive_cell_returns_alive_cell() {
        MutationRules.Builder rules = new MutationRules.Builder().withLivingNeighbors(2);
        assertEquals(Cell.ALIVE, rules.build().apply(Cell.ALIVE));
    }

    @Test
    public void rules_with_three_alive_neighbors_applied_to_alive_cell_returns_alive_cell() {
        MutationRules.Builder rules = new MutationRules.Builder().withLivingNeighbors(3);
        assertEquals(Cell.ALIVE, rules.build().apply(Cell.ALIVE));
    }

    @Test
    public void rules_with_more_than_three_alive_neighbors_applied_to_alive_cell_returns_empty_cell() {
        MutationRules.Builder rules = new MutationRules.Builder().withLivingNeighbors(4);
        assertEquals(Cell.EMPTY, rules.build().apply(Cell.ALIVE));
    }

    @Test
    public void rules_with_three_alive_neighbors_applied_to_empty_cell_returns_alive_cell() {
        MutationRules.Builder rules = new MutationRules.Builder().withLivingNeighbors(3);
        assertEquals(Cell.ALIVE, rules.build().apply(Cell.EMPTY));
    }

    @Test
    public void rules_with_one_alive_neighbor_applied_to_empty_cell_returns_empty_cell() {
        MutationRules.Builder rules = new MutationRules.Builder().withLivingNeighbors(1);
        assertEquals(Cell.EMPTY, rules.build().apply(Cell.EMPTY));
    }

    @Test
    public void rules_with_four_alive_neighbors_applied_to_empty_cell_returns_empty_cell() {
        MutationRules.Builder rules = new MutationRules.Builder().withLivingNeighbors(4);
        assertEquals(Cell.EMPTY, rules.build().apply(Cell.EMPTY));
    }

    @Test(expected = NullPointerException.class)
    public void rules_applied_to_null_throws_exception() {
        MutationRules.Builder rules = new MutationRules.Builder().withLivingNeighbors(4);
        rules.build().apply(null);
    }
}