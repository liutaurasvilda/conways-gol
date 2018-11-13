package io.github.liutaurasvilda.gol;

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
        assertTrue(!world.tick().hasPopulation());
    }

    @Test
    public void world_with_one_alive_cell_has_no_population_in_next_generation() {
        World world = World.empty();
        world.aliveAt(Location.of(0, 0));
        assertTrue(!world.tick().hasPopulation());
    }

    @Test
    public void world_with_two_inline_alive_cells_has_no_population_in_next_generation() {
        World world = World.empty();
        world.aliveAt(Location.of(0, 0));
        world.aliveAt(Location.of(1, 0));
        assertTrue(!world.tick().hasPopulation());
    }

    @Test
    public void world_with_three_inline_alive_cells_has_population_in_next_generation() {
        World world = World.empty();
        world.aliveAt(Location.of(0, 0));
        world.aliveAt(Location.of(1, 0));
        world.aliveAt(Location.of(2, 0));
        assertTrue(world.tick().hasPopulation());
    }

    @Test
    public void empty_world_has_size_of_ten() {
        World world = World.empty();
        assertEquals(10, world.getSize());
    }

    @Test
    public void world_with_size_set_less_than_ten_has_size_ten() {
        World world = World.empty();
        world.setSize(9);
        assertEquals(10, world.getSize());
    }

    @Test
    public void world_with_size_set_twenty_has_size_twenty() {
        World world = World.empty();
        world.setSize(20);
        assertEquals(20, world.getSize());
    }

    @Test
    public void world_toString() {
        String expected =
                "..........\n" +
                "..........\n" +
                "..........\n" +
                "..........\n" +
                ".....0....\n" +
                "......0...\n" +
                "....000...\n" +
                "..........\n" +
                "..........\n" +
                "..........\n";
        World world = World.empty();
        world.aliveAt(Location.of(4, 5));
        world.aliveAt(Location.of(5, 6));
        world.aliveAt(Location.of(6, 4));
        world.aliveAt(Location.of(6, 5));
        world.aliveAt(Location.of(6, 6));
        assertEquals(expected, world.toString());
    }
}