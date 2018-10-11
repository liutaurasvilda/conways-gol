package io.github.liutaurasvilda.gol.core;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public final class World implements Generation {

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
        map.put(Objects.requireNonNull(location), Cell.ALIVE);
    }

    public boolean hasPopulation() {
        return map.entrySet().stream()
                  .anyMatch(e -> e.getValue().equals(Cell.ALIVE));
    }

    @Override
    public World nextGeneration() {
        World newWorld = empty();
        MutationRules.Builder rules = new MutationRules.Builder();
        IntStream.range(0, SIZE)
            .forEach(r -> IntStream.range(0, SIZE)
            .forEach(c -> {
                rules.withLivingNeighbors(countAt(Location.of(r, c)));
                newWorld.map.put(Location.of(r, c), mutableAt(Location.of(r, c)).mutate(rules.build()));
            }));
        return newWorld;
    }

    private long countAt(Location location) {
        return location.neighborhood().stream()
                   .map(map::get).filter(n -> n.equals(Cell.ALIVE)).count();
    }

    private Mutable mutableAt(Location location) {
        return map.get(location);
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
