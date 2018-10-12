package io.github.liutaurasvilda.gol.core;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public final class World {

    static final int SIZE = 10;
    private final Map<Location, Mutable> worldMap;

    private World(Map<Location, Mutable> worldMap) {
        this.worldMap = worldMap;
    }

    public static World empty() {
        Map<Location, Mutable> emptyWorldMap = IntStream.range(0, SIZE)
                .mapToObj(row -> IntStream.range(0, SIZE)
                        .mapToObj(column -> Location.of(row, column)))
                .flatMap(Function.identity())
                .collect(toMap(Function.identity(), cell -> Cell.EMPTY, (location, cell) -> location, LinkedHashMap::new));
        return new World(emptyWorldMap);
    }

    public void aliveAt(Location location) {
        worldMap.put(Objects.requireNonNull(location), Cell.ALIVE);
    }

    public boolean hasPopulation() {
        return worldMap.entrySet().stream()
                .map(Map.Entry::getValue)
                .anyMatch(cell -> !cell.equals(Cell.EMPTY));
    }

    public World nextGeneration() {
        MutationRules.Builder rules = new MutationRules.Builder();
        Map<Location, Mutable> newWorldMap = worldMap.entrySet().stream()
                .map(Map.Entry::getKey)
                .collect(toMap(Function.identity(),
                        location -> mutableAt(location).mutate(rules.withLivingNeighbors(countAt(location)).build()),
                        (location, cell) -> location, LinkedHashMap::new));
        return new World(newWorldMap);
    }

    private long countAt(Location location) {
        return location.neighborhood().stream()
                .map(worldMap::get).filter(neighbor -> neighbor.equals(Cell.ALIVE)).count();
    }

    private Mutable mutableAt(Location location) {
        return worldMap.get(location);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, SIZE)
                .forEach(r -> {
                    IntStream.range(0, SIZE)
                            .forEach(c -> sb.append(worldMap.get(Location.of(r, c))));
                    sb.append("\n");
                });
        return sb.toString();
    }
}
