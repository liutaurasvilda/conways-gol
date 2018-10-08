package io.github.liutaurasvilda.gol.core;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

final class World {

    private final Map<Location, Cell> map;
    private final int SIZE = 50;

    private World() {
        this.map = new HashMap<>();
        IntStream.range(0, SIZE)
                 .forEach(x -> IntStream.range(0, SIZE)
                 .forEach(y -> map.put(Location.of(x, y), Cell.dead())));
    }

    static World empty() {
        return new World();
    }

    boolean isEmpty() {
        return map.entrySet()
                  .stream()
                  .allMatch(e -> e.getValue().state() == Cell.State.DEAD);
    }

    void aliveAt(Location location) {
        map.put(location, Cell.alive());
    }

    void deadAt(Location location) {
        map.put(location, Cell.dead());
    }

    World nextGeneration() {
        World newWorld = new World();
        IntStream.range(0, SIZE)
                 .forEach(x -> IntStream.range(0, SIZE)
                 .forEach(y -> {
                     MutationRules.Builder rules = new MutationRules.Builder();
                     rules.livingNeighbors(Location.of(x, y).numberOfLivingNeighborsAround(map, SIZE));
                     newWorld.map.put(Location.of(x, y), this.map.get(Location.of(x, y)).mutate(rules.build()));
                 }));
        return newWorld;
    }
}
