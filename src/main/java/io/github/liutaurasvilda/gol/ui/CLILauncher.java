package io.github.liutaurasvilda.gol.ui;

import io.github.liutaurasvilda.gol.core.Location;
import io.github.liutaurasvilda.gol.core.World;

final class CLILauncher {

    private CLILauncher() {
    }

    public static void main(String[] args) {
        World world = World.empty();
        world.aliveAt(Location.of(4, 4));
        world.aliveAt(Location.of(5, 4));
        world.aliveAt(Location.of(6, 4));
        while (world.hasPopulation()) {
            System.out.println(world);
            world = world.nextGeneration();
        }
    }
}
