package io.github.liutaurasvilda.gol.core;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public final class World {

    static final int SIZE = 10;
    private final Map<Location, Mutable> map;

    private World() {
        this.map = new LinkedHashMap<>();
        IntStream.range(0, SIZE)
                 .forEach(x -> IntStream.range(0, SIZE)
                 .forEach(y -> map.put(Location.of(x, y), Cell.dead())));
    }

    public static World empty() {
        return new World();
    }

    public boolean hasPopulation() {
        return map.entrySet()
                  .stream()
                  .anyMatch(e -> e.getValue().state() == Mutable.State.ALIVE);
    }

    public void aliveAt(Location location) {
        map.put(location, Cell.alive());
    }

    public World nextGeneration() {
        World newWorld = new World();
        IntStream.range(0, SIZE)
                 .forEach(x -> IntStream.range(0, SIZE)
                 .forEach(y -> {
                     ConwaysRules.Builder rules = new ConwaysRules.Builder();
                     rules.withLivingNeighbors(Location.of(x, y).numberOfLivingNeighborsAround(map));
                     newWorld.map.put(Location.of(x, y), this.map.get(Location.of(x, y)).mutate(rules.build()));
                 }));
        return newWorld;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, SIZE)
                 .forEach(x -> {
                     IntStream.range(0, SIZE)
                              .forEach(y -> sb.append(map.get(Location.of(x, y))));
                     sb.append("\n");
                 });
        return sb.toString();
    }
}
