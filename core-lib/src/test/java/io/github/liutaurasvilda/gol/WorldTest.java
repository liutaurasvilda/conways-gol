package io.github.liutaurasvilda.gol;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class WorldTest {

    @Test
    public void empty_world_has_no_population() {
        World world = World.empty();
        assertThat(world.hasPopulation(),is(false));
    }

    @Test
    public void world_with_one_alive_cell_has_population() {
        World world = World.empty();
        world.aliveAt(Location.of(0, 0));
        assertThat(world.hasPopulation(), is(true));
    }

    @Test
    public void world_with_no_population_has_no_population_in_next_generation() {
        World world = World.empty();
        assertThat(world.nextGeneration().hasPopulation(), is(false));
    }

    @Test
    public void world_with_one_alive_cell_has_no_population_in_next_generation() {
        World world = World.empty();
        world.aliveAt(Location.of(0, 0));
        assertThat(world.nextGeneration().hasPopulation(), is(false));
    }

    @Test
    public void world_with_two_inline_alive_cells_has_no_population_in_next_generation() {
        World world = World.empty();
        world.aliveAt(Location.of(0, 0));
        world.aliveAt(Location.of(1, 0));
        assertThat(world.nextGeneration().hasPopulation(), is(false));
    }

    @Test
    public void world_with_three_inline_alive_cells_has_population_in_next_generation() {
        World world = World.empty();
        world.aliveAt(Location.of(0, 0));
        world.aliveAt(Location.of(1, 0));
        world.aliveAt(Location.of(2, 0));
        assertThat(world.nextGeneration().hasPopulation(), is(true));
    }

    @Test
    public void empty_world_has_size_of_10() {
        assertThat(World.empty().getSize(), is(equalTo(10)));
    }

    @Test
    public void world_with_size_set_less_than_10_has_size_10() {
        World world = World.empty();
        world.setSize(9);
        assertThat(world.getSize(), is(equalTo(10)));
    }

    @Test
    public void world_with_size_set_20_has_size_20() {
        World world = World.empty();
        world.setSize(20);
        assertThat(world.getSize(), is(equalTo(20)));

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
        assertThat(world.toString(), is(equalTo(expected)));
    }
}