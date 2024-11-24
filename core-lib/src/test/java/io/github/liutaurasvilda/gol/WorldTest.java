package io.github.liutaurasvilda.gol;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorldTest {

    private static final int WORLD_SIZE = 10;

    @Test
    public void world_generation_with_no_seed_has_no_population() {
        List<Location> seed = List.of();
        World world = World.generation(seed, WORLD_SIZE);
        assertFalse(world.hasPopulation());
    }

    @Test
    public void world_generation_with_one_seed_has_population() {
        List<Location> seed = List.of(Location.of(0, 0));
        World world = World.generation(seed, WORLD_SIZE);
        assertTrue(world.hasPopulation());
    }

    @Test
    public void world_generation_with_no_seed_has_no_population_in_next_generation() {
        List<Location> seed = List.of();
        World world = World.generation(seed, WORLD_SIZE);
        assertFalse(world.nextGeneration().hasPopulation());
    }

    @Test
    public void world_generation_with_one_seed_has_no_population_in_next_generation() {
        List<Location> seed = List.of(Location.of(0, 0));
        World world = World.generation(seed, WORLD_SIZE);
        assertFalse(world.nextGeneration().hasPopulation());
    }

    @Test
    public void world_generation_with_two_seed_has_no_population_in_next_generation() {
        List<Location> seed = Arrays.asList(
                Location.of(0, 0),
                Location.of(1, 0)
        );
        World world = World.generation(seed, WORLD_SIZE);
        assertFalse(world.nextGeneration().hasPopulation());
    }

    @Test
    public void world_generation_with_three_inline_seed_has_population_in_next_generation() {
        List<Location> seed = Arrays.asList(
                Location.of(0, 0),
                Location.of(1, 0),
                Location.of(2, 0)
        );
        World world = World.generation(seed, WORLD_SIZE);
        assertTrue(world.nextGeneration().hasPopulation());
    }

    @Test
    public void world_toString() {
        String expected =
                """
                        ..........
                        ..........
                        ..........
                        ..........
                        .....0....
                        ......0...
                        ....000...
                        ..........
                        ..........
                        ..........
                        """;
        List<Location> seed = Arrays.asList(
                Location.of(4, 5),
                Location.of(5, 6),
                Location.of(6, 4),
                Location.of(6, 5),
                Location.of(6, 6)
        );
        World world = World.generation(seed, WORLD_SIZE);
        assertEquals(expected, world.toString());
    }
}