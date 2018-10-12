package io.github.liutaurasvilda.gol.core;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public final class World {

    static final int SIZE = 10;
    private final Map<Location, Mutable> map;

    private World() {
        this.map = IntStream.range(0, SIZE)
                .mapToObj(row -> IntStream.range(0, SIZE)
                        .mapToObj(column -> Location.of(row, column)))
                .flatMap(Function.identity())
                .collect(toMap(Function.identity(), cell -> Cell.EMPTY, (location, cell) -> location, LinkedHashMap::new));
    }

    private World(Map<Location, Mutable> map) {
        this.map = map;
    }

    public static World empty() {
        return new World();
    }

    public void aliveAt(Location location) {
        map.put(Objects.requireNonNull(location), Cell.ALIVE);
    }

    public boolean hasPopulation() {
        return map.entrySet().stream()
                .map(Map.Entry::getValue)
                .anyMatch(cell -> !cell.equals(Cell.EMPTY));
    }

    public World nextGeneration() {
        MutationRules.Builder rules = new MutationRules.Builder();
        Map<Location, Mutable> newMap = map.entrySet().stream()
                .map(Map.Entry::getKey)
                .collect(toMap(Function.identity(),
                        location -> mutableAt(location).mutate(rules.withLivingNeighbors(countAt(location)).build()),
                        (location, cell) -> location, LinkedHashMap::new));
        return new World(newMap);
    }

    private long countAt(Location location) {
        return location.neighborhood().stream()
                .map(map::get).filter(neighbor -> neighbor.equals(Cell.ALIVE)).count();
    }

    private Mutable mutableAt(Location location) {
        return map.get(location);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, SIZE)
                .forEach(r -> {
                    IntStream.range(0, SIZE)
                            .forEach(c -> sb.append(map.get(Location.of(r, c))));
                    sb.append("\n");
                });
        return sb.toString();
    }
}
