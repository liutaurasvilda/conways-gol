package io.github.liutaurasvilda.gol.core;

public class GoLLauncher {

    private GoLLauncher() {
    }

    public static void main(String[] args) {
        World w = World.empty();
        w.aliveAt(Location.of(4, 4));
        w.aliveAt(Location.of(5, 4));
        w.aliveAt(Location.of(6, 4));
        System.out.println(w);
        System.out.println(w.nextGeneration());
        System.out.println(w.nextGeneration().nextGeneration());
    }
}
