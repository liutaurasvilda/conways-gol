package io.github.liutaurasvilda.gol.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConwaysRulesTest {

    @Test
    public void rules_with_less_than_two_alive_neighbors_applied_to_alive_cell_returns_dead_cell() {
        ConwaysRules.Builder rules = new ConwaysRules.Builder().withLivingNeighbors(1);
        assertEquals(Cell.DEAD, rules.build().apply(Cell.ALIVE));
    }

    @Test
    public void rules_with_two_alive_neighbors_applied_to_alive_cell_returns_alive_cell() {
        ConwaysRules.Builder rules = new ConwaysRules.Builder().withLivingNeighbors(2);
        assertEquals(Cell.ALIVE, rules.build().apply(Cell.ALIVE));
    }

    @Test
    public void rules_with_three_alive_neighbors_applied_to_alive_cell_returns_alive_cell() {
        ConwaysRules.Builder rules = new ConwaysRules.Builder().withLivingNeighbors(3);
        assertEquals(Cell.ALIVE, rules.build().apply(Cell.ALIVE));
    }

    @Test
    public void rules_with_more_than_three_alive_neighbors_applied_to_alive_cell_returns_dead_cell() {
        ConwaysRules.Builder rules = new ConwaysRules.Builder().withLivingNeighbors(4);
        assertEquals(Cell.DEAD, rules.build().apply(Cell.ALIVE));
    }

    @Test
    public void rules_with_three_alive_neighbors_applied_to_dead_cell_returns_alive_cell() {
        ConwaysRules.Builder rules = new ConwaysRules.Builder().withLivingNeighbors(3);
        assertEquals(Cell.ALIVE, rules.build().apply(Cell.DEAD));
    }

    @Test
    public void rules_with_one_alive_neighbor_applied_to_dead_cell_returns_dead_cell() {
        ConwaysRules.Builder rules = new ConwaysRules.Builder().withLivingNeighbors(1);
        assertEquals(Cell.DEAD, rules.build().apply(Cell.DEAD));
    }

    @Test
    public void rules_with_four_alive_neighbors_applied_to_dead_cell_returns_dead_cell() {
        ConwaysRules.Builder rules = new ConwaysRules.Builder().withLivingNeighbors(4);
        assertEquals(Cell.DEAD, rules.build().apply(Cell.DEAD));
    }

    @Test(expected = NullPointerException.class)
    public void rules_applied_to_null_throws_exception() {
        ConwaysRules.Builder rules = new ConwaysRules.Builder().withLivingNeighbors(4);
        rules.build().apply(null);
    }
}