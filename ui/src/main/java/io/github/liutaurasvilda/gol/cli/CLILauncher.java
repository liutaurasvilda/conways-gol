package io.github.liutaurasvilda.gol.cli;

import io.github.liutaurasvilda.gol.Location;
import io.github.liutaurasvilda.gol.World;

final class CLILauncher {

    private CLILauncher() {
    }

    public static void main(String[] args) {
        launch();
    }

    private static void launch() {
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
    }
}
