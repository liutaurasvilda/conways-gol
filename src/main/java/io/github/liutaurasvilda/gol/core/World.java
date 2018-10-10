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
                 .forEach(r -> IntStream.range(0, SIZE)
                 .forEach(c -> map.put(Location.of(r, c), Cell.DEAD)));
    }

    public static World empty() {
        return new World();
    }

    public void aliveAt(Location location) {
        map.put(location, Cell.ALIVE);
    }

    public boolean hasPopulation() {
        return map.entrySet()
                  .stream()
                  .anyMatch(e -> e.getValue() == Cell.ALIVE);
    }

    public World nextGeneration() {
        World newWorld = new World();
        IntStream.range(0, SIZE)
                 .forEach(r -> IntStream.range(0, SIZE)
                 .forEach(c -> {
                     ConwaysRules.Builder rules = new ConwaysRules.Builder();
                     rules.withLivingNeighbors(Location.of(r, c).numberOfLivingNeighborsInA(map));
                     newWorld.map.put(Location.of(r, c), this.map.get(Location.of(r, c)).mutate(rules.build()));
                 }));
        return newWorld;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, SIZE)
                 .forEach(r -> { IntStream.range(0, SIZE)
                 .forEach(c -> sb.append(map.get(Location.of(r, c))));
                     sb.append("\n");
                 });
        return sb.toString();
    }
}
