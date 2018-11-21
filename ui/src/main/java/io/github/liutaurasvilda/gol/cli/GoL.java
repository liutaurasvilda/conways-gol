package io.github.liutaurasvilda.gol.cli;

import io.github.liutaurasvilda.gol.Location;
import io.github.liutaurasvilda.gol.World;

import java.util.Arrays;
import java.util.List;

final class GoL {

    GoL() {
        play();
    }

    private void play() {
        List<Location> seed = Arrays.asList(
                Location.of(4, 5),
                Location.of(5, 6),
                Location.of(6, 4),
                Location.of(6, 5),
                Location.of(6, 6)
        );
        World world = World.generation(seed, 10);
        while (world.hasPopulation()) {
            System.out.println(world);
            world = world.nextGeneration();
        }
        System.out.println(world);
    }
}
