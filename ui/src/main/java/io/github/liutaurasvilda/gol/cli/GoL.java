package io.github.liutaurasvilda.gol.cli;

import io.github.liutaurasvilda.gol.Location;
import io.github.liutaurasvilda.gol.World;

final class GoL {

    GoL() {
        play();
    }

    private void play() {
        World world = World.empty();
        world.aliveAt(Location.of(4, 5));
        world.aliveAt(Location.of(5, 6));
        world.aliveAt(Location.of(6, 4));
        world.aliveAt(Location.of(6, 5));
        world.aliveAt(Location.of(6, 6));
        while (world.hasPopulation()) {
            System.out.println(world);
            world = world.nextGeneration();
        }
        System.out.println(world);
    }
}
