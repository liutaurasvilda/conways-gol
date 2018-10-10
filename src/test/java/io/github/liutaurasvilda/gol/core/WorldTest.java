package io.github.liutaurasvilda.gol.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class WorldTest {

    @Test
    public void empty_world_has_no_population() {
        World world = World.empty();
        assertTrue(!world.hasPopulation());
    }

    @Test
    public void world_with_one_alive_cell_has_population() {
        World world = World.empty();
        world.aliveAt(Location.of(0, 0));
        assertTrue(world.hasPopulation());
    }

    @Test
    public void world_with_no_population_has_no_population_in_next_generation() {
        World world = World.empty();
        assertTrue(!world.nextGeneration().hasPopulation());
    }

    @Test
    public void world_with_one_alive_cell_has_no_population_in_next_generation() {
        World world = World.empty();
        world.aliveAt(Location.of(0, 0));
        assertTrue(!world.nextGeneration().hasPopulation());
    }

    @Test
    public void world_with_two_inline_alive_cells_has_no_population_in_next_generation() {
        World world = World.empty();
        world.aliveAt(Location.of(0, 0));
        world.aliveAt(Location.of(1, 0));
        assertTrue(!world.nextGeneration().hasPopulation());
    }

    @Test
    public void world_with_three_inline_alive_cells_has_population_in_next_generation() {
        World world = World.empty();
        world.aliveAt(Location.of(0, 0));
        world.aliveAt(Location.of(1, 0));
        world.aliveAt(Location.of(2, 0));
        assertTrue(world.nextGeneration().hasPopulation());
    }

    @Test
    public void world_toString() {
        String expected =
                "..........\n" +
                "..........\n" +
                "..........\n" +
                "..........\n" +
                "....0.....\n" +
                "....0.....\n" +
                "....0.....\n" +
                "..........\n" +
                "..........\n" +
                "..........\n";
        World world = World.empty();
        world.aliveAt(Location.of(4, 4));
        world.aliveAt(Location.of(5, 4));
        world.aliveAt(Location.of(6, 4));
        assertEquals(expected, world.toString());
    }
}