package io.github.liutaurasvilda.gol;

import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class RegenerationRulesTest {

    private final RegenerationRules.Builder rules = new RegenerationRules.Builder();

    @Test
    public void rules_with_two_alive_neighbors_applied_to_alive_cell_returns_alive_cell() {
        assertEquals(Cell.ALIVE, rules.withLivingNeighbors(2).build().apply(Cell.ALIVE));
    }

    @Test
    public void rules_with_three_alive_neighbors_applied_to_alive_cell_returns_alive_cell() {
        assertEquals(Cell.ALIVE, rules.withLivingNeighbors(3).build().apply(Cell.ALIVE));
    }

    @Test
    public void rules_with_three_alive_neighbors_applied_to_unpopulated_cell_returns_alive_cell() {
        assertEquals(Cell.ALIVE, rules.withLivingNeighbors(3).build().apply(null));
    }
}