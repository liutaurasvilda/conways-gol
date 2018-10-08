package gol;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author AxiomSL
 */
public class WorldTest {

    @Test
    public void new_world_is_empty() {
        World world = World.empty();
        assertTrue(world.isEmpty());
    }

    @Test
    public void world_with_one_alive_cell_is_not_empty() {
        World world = World.empty();
        world.aliveAt(Location.of(0, 0));
        assertTrue(!world.isEmpty());
    }

    @Test
    public void world_with_alive_and_later_dead_cell_is_empty() {
        World world = World.empty();
        world.aliveAt(Location.of(0, 0));
        world.deadAt(Location.of(0, 0));
        assertTrue(world.isEmpty());
    }

    @Test
    public void empty_world_stays_empty_in_next_generation() {
        World world = World.empty();
        World newWorld = world.nextGeneration();
        assertTrue(newWorld.isEmpty());
    }
}