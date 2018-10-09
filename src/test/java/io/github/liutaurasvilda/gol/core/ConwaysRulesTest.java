package io.github.liutaurasvilda.gol.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConwaysRulesTest {

    @Test
    public void alive_cell_mutates_to_dead_with_less_than_two_alive_neighbors() {
        ConwaysRules.Builder rules = new ConwaysRules.Builder().withLivingNeighbors(1);
        assertEquals(Cell.dead(), rules.build().apply(Cell.alive()));
    }

    @Test
    public void alive_cell_stays_alive_with_two_alive_neighbors() {
        ConwaysRules.Builder rules = new ConwaysRules.Builder().withLivingNeighbors(2);
        assertEquals(Cell.alive(), rules.build().apply(Cell.alive()));
    }

    @Test
    public void alive_cell_stays_alive_with_three_alive_neighbors() {
        ConwaysRules.Builder rules = new ConwaysRules.Builder().withLivingNeighbors(3);
        assertEquals(Cell.alive(), rules.build().apply(Cell.alive()));
    }

    @Test
    public void alive_cell_mutates_to_dead_with_more_than_three_alive_neighbors() {
        ConwaysRules.Builder rules = new ConwaysRules.Builder().withLivingNeighbors(4);
        assertEquals(Cell.dead(), rules.build().apply(Cell.alive()));
    }

    @Test
    public void dead_cell_mutates_to_alive_with_three_alive_neighbors() {
        ConwaysRules.Builder rules = new ConwaysRules.Builder().withLivingNeighbors(3);
        assertEquals(Cell.alive(), rules.build().apply(Cell.dead()));
    }

    @Test
    public void dead_cell_stays_dead_with_one_alive_neighbor() {
        ConwaysRules.Builder rules = new ConwaysRules.Builder().withLivingNeighbors(1);
        assertEquals(Cell.dead(), rules.build().apply(Cell.dead()));
    }

    @Test
    public void dead_cell_stays_dead_with_four_alive_neighbor() {
        ConwaysRules.Builder rules = new ConwaysRules.Builder().withLivingNeighbors(4);
        assertEquals(Cell.dead(), rules.build().apply(Cell.dead()));
    }
}