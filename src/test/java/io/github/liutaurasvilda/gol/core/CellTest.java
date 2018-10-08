package io.github.liutaurasvilda.gol.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author AxiomSL
 */
public class CellTest {

    @Test
    public void alive_cell_state_is_alive() {
        assertEquals(Cell.State.ALIVE, Cell.alive().state());
    }

    @Test
    public void dead_cell_state_is_dead() {
        assertEquals(Cell.State.DEAD, Cell.dead().state());
    }

    @Test
    public void alive_cell_mutates_to_dead_with_less_than_two_alive_neighbors() {
        MutationRules.Builder rules = new MutationRules.Builder().livingNeighbors(1);
        assertEquals(Cell.State.DEAD, Cell.alive().mutate(rules.build()).state());
    }

    @Test
    public void alie_cell_stays_alive_with_two_alive_neighbors() {
        MutationRules.Builder rules = new MutationRules.Builder().livingNeighbors(2);
        assertEquals(Cell.State.ALIVE, Cell.alive().mutate(rules.build()).state());
    }

    @Test
    public void alive_cell_stays_alive_with_three_alive_neighbors() {
        MutationRules.Builder rules = new MutationRules.Builder().livingNeighbors(3);
        assertEquals(Cell.State.ALIVE, Cell.alive().mutate(rules.build()).state());
    }

    @Test
    public void alive_cell_mutates_to_dead_with_more_than_three_alive_neighbors() {
        MutationRules.Builder rules = new MutationRules.Builder().livingNeighbors(4);
        assertEquals(Cell.State.DEAD, Cell.alive().mutate(rules.build()).state());
    }

    @Test
    public void dead_cell_mutates_to_alive_with_three_alive_neighbors() {
        MutationRules.Builder rules = new MutationRules.Builder().livingNeighbors(3);
        assertEquals(Cell.State.ALIVE, Cell.dead().mutate(rules.build()).state());
    }
}