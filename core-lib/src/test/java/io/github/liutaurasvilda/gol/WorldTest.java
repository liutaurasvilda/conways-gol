package io.github.liutaurasvilda.gol;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class WorldTest {

    private static final int WORLD_SIZE = 10;

    @Test
    public void world_generation_with_no_seed_has_no_population() {
        List<Location> seed = Collections.emptyList();
        World world = World.generation(seed, WORLD_SIZE);
        assertThat(world.hasPopulation(), is(false));
    }

    @Test
    public void world_generation_with_one_seed_has_population() {
        List<Location> seed = Collections.singletonList(Location.of(0, 0));
        World world = World.generation(seed, WORLD_SIZE);
        assertThat(world.hasPopulation(), is(true));
    }

    @Test
    public void world_generation_with_no_seed_has_no_population_in_next_generation() {
        List<Location> seed = Collections.emptyList();
        World world = World.generation(seed, WORLD_SIZE);
        assertThat(world.nextGeneration().hasPopulation(), is(false));
    }

    @Test
    public void world_generation_with_one_seed_has_no_population_in_next_generation() {
        List<Location> seed = Collections.singletonList(Location.of(0, 0));
        World world = World.generation(seed, WORLD_SIZE);
        assertThat(world.nextGeneration().hasPopulation(), is(false));
    }

    @Test
    public void world_generation_with_two_seed_has_no_population_in_next_generation() {
        List<Location> seed = Arrays.asList(
                Location.of(0, 0),
                Location.of(1, 0)
        );
        World world = World.generation(seed, WORLD_SIZE);
        assertThat(world.nextGeneration().hasPopulation(), is(false));
    }

    @Test
    public void world_generation_with_three_inline_seed_has_population_in_next_generation() {
        List<Location> seed = Arrays.asList(
                Location.of(0, 0),
                Location.of(1, 0),
                Location.of(2, 0)
        );
        World world = World.generation(seed, WORLD_SIZE);
        assertThat(world.nextGeneration().hasPopulation(), is(true));
    }

    @Test(expected = NullPointerException.class)
    public void world_generation_with_seed_being_null_throws_an_exception() {
        World world = World.generation(null, WORLD_SIZE);
        assertThat(world.nextGeneration().hasPopulation(), is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void world_generation_with_one_seed_being_null_throws_an_exception() {
        List<Location> seed = Arrays.asList(
                Location.of(0, 0),
                null
        );
        World world = World.generation(seed, WORLD_SIZE);
        assertThat(world.nextGeneration().hasPopulation(), is(true));
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
        List<Location> seed = Arrays.asList(
                Location.of(4, 5),
                Location.of(5, 6),
                Location.of(6, 4),
                Location.of(6, 5),
                Location.of(6, 6)
        );
        World world = World.generation(seed, WORLD_SIZE);
        assertThat(world.toString(), is(equalTo(expected)));
    }
}