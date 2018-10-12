package io.github.liutaurasvilda.gol.core;

import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class MutationRulesTest {

    private final MutationRules.Builder rules = new MutationRules.Builder();

    @Test
    public void rules_with_less_than_two_alive_neighbors_applied_to_alive_cell_returns_empty_cell() {
        assertEquals(Cell.EMPTY, rules.withLivingNeighbors(1).build().apply(Cell.ALIVE));
    }

    @Test
    public void rules_with_two_alive_neighbors_applied_to_alive_cell_returns_alive_cell() {
        assertEquals(Cell.ALIVE, rules.withLivingNeighbors(2).build().apply(Cell.ALIVE));
    }

    @Test
    public void rules_with_three_alive_neighbors_applied_to_alive_cell_returns_alive_cell() {
        assertEquals(Cell.ALIVE, rules.withLivingNeighbors(3).build().apply(Cell.ALIVE));
    }

    @Test
    public void rules_with_more_than_three_alive_neighbors_applied_to_alive_cell_returns_empty_cell() {
        assertEquals(Cell.EMPTY, rules.withLivingNeighbors(4).build().apply(Cell.ALIVE));
    }

    @Test
    public void rules_with_two_alive_neighbor_applied_to_empty_cell_returns_empty_cell() {
        assertEquals(Cell.EMPTY, rules.withLivingNeighbors(2).build().apply(Cell.EMPTY));
    }

    @Test
    public void rules_with_three_alive_neighbors_applied_to_empty_cell_returns_alive_cell() {
        assertEquals(Cell.ALIVE, rules.withLivingNeighbors(3).build().apply(Cell.EMPTY));
    }

    @Test
    public void rules_with_four_alive_neighbors_applied_to_empty_cell_returns_empty_cell() {
        assertEquals(Cell.EMPTY, rules.withLivingNeighbors(4).build().apply(Cell.EMPTY));
    }

    @Test
    public void rules_applied_to_other_than_alive_or_empty_cell_returns_empty_cell() {
        assertEquals(Cell.EMPTY, rules.withLivingNeighbors(0).build().apply(new Mutable() {
            @Override
            public Mutable mutate(Function<Mutable, Mutable> f) {
                return this;
            }
        }));
    }

    @Test(expected = NullPointerException.class)
    public void rules_applied_to_null_throws_exception() {
        rules.withLivingNeighbors(4).build().apply(null);
    }
}