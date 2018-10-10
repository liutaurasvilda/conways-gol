package io.github.liutaurasvilda.gol.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {

    @Test
    public void two_alive_cells_are_indistinguishable() {
        assertEquals(Cell.alive(), Cell.alive());
    }

    @Test
    public void two_dead_cells_are_indistinguishable() {
        assertEquals(Cell.dead(), Cell.dead());
    }

    @Test
    public void alive_cell_mutates_to_dead_with_less_than_two_alive_neighbors() {
        ConwaysRules.Builder rules = new ConwaysRules.Builder().withLivingNeighbors(1);
        assertEquals(Cell.dead(), Cell.alive().mutate(rules.build()));
    }

    @Test
    public void alive_cell_stays_alive_with_two_alive_neighbors() {
        ConwaysRules.Builder rules = new ConwaysRules.Builder().withLivingNeighbors(2);
        assertEquals(Cell.alive(), Cell.alive().mutate(rules.build()));
    }

    @Test
    public void alive_cell_stays_alive_with_three_alive_neighbors() {
        ConwaysRules.Builder rules = new ConwaysRules.Builder().withLivingNeighbors(3);
        assertEquals(Cell.alive(), Cell.alive().mutate(rules.build()));
    }

    @Test
    public void alive_cell_mutates_to_dead_with_more_than_three_alive_neighbors() {
        ConwaysRules.Builder rules = new ConwaysRules.Builder().withLivingNeighbors(4);
        assertEquals(Cell.dead(), Cell.alive().mutate(rules.build()));
    }

    @Test
    public void dead_cell_mutates_to_alive_with_three_alive_neighbors() {
        ConwaysRules.Builder rules = new ConwaysRules.Builder().withLivingNeighbors(3);
        assertEquals(Cell.alive(), Cell.dead().mutate(rules.build()));
    }

    @Test
    public void dead_cell_stays_dead_with_one_alive_neighbor() {
        ConwaysRules.Builder rules = new ConwaysRules.Builder().withLivingNeighbors(1);
        assertEquals(Cell.dead(), Cell.dead().mutate(rules.build()));
    }

    @Test
    public void dead_cell_stays_dead_with_four_alive_neighbor() {
        ConwaysRules.Builder rules = new ConwaysRules.Builder().withLivingNeighbors(4);
        assertEquals(Cell.dead(), Cell.dead().mutate(rules.build()));
    }

    @Test
    public void two_live_cells_hashes_are_same() {
        assertEquals(Cell.alive().hashCode(), Cell.alive().hashCode());
    }

    @Test
    public void live_and_dead_cells_hashes_are_different() {
        assertNotEquals(Cell.alive().hashCode(), Cell.dead().hashCode());
    }

    @Test
    public void live_cell_toString() {
        assertEquals("0", Cell.alive().toString());
    }

    @Test
    public void dead_cell_toString() {
        assertEquals(".", Cell.dead().toString());
    }
}