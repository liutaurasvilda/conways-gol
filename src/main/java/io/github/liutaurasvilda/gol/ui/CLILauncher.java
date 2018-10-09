package io.github.liutaurasvilda.gol.ui;

import io.github.liutaurasvilda.gol.core.Location;
import io.github.liutaurasvilda.gol.core.World;

final class CLILauncher {

    private CLILauncher() {
    }

    public static void main(String[] args) {
        World w = World.empty();
        w.aliveAt(Location.of(4, 4));
        w.aliveAt(Location.of(5, 4));
        w.aliveAt(Location.of(6, 4));
        while (w.hasPopulation()) {
            System.out.println(w);
            w = w.nextGeneration();
        }
    }
}
