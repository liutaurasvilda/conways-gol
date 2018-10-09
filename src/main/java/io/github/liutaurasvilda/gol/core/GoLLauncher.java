package io.github.liutaurasvilda.gol.core;

/**
 * @author AxiomSL
 */
public class GoLLauncher {

    private GoLLauncher() {
    }

    public static void main(String[] args) {
        World w = World.empty();
        w.aliveAt(Location.of(4, 4));
        w.aliveAt(Location.of(4, 5));
        w.aliveAt(Location.of(5, 4));
        w.aliveAt(Location.of(5, 5));
        System.out.println(w);
        w = w.nextGeneration();
        System.out.println(w);
    }
}
