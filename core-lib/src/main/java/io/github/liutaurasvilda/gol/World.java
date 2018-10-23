package io.github.liutaurasvilda.gol;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public final class World {

    static final int SIZE = 10;
    private final Map<Location, Regenerable> worldMap;

    private World(Map<Location, Regenerable> worldMap) {
        this.worldMap = worldMap;
    }

    public static World empty() {
        Map<Location, Regenerable> emptyWorldMap = IntStream.range(0, SIZE)
                .mapToObj(rowIndex -> IntStream.range(0, SIZE)
                        .mapToObj(columnIndex -> Location.of(rowIndex, columnIndex)))
                .flatMap(Function.identity())
                .collect(toMap(Function.identity(),
                        regenerable -> Cell.EMPTY, (location, regenerable) -> location, LinkedHashMap::new));
        return new World(emptyWorldMap);
    }

    public void aliveAt(Location location) {
        worldMap.put(Objects.requireNonNull(location), Cell.ALIVE);
    }

    public boolean hasPopulation() {
        return worldMap.entrySet().stream()
                .map(Map.Entry::getValue)
                .anyMatch(regenerable -> !regenerable.equals(Cell.EMPTY));
    }

    public World nextGeneration() {
        RegenerationRules.Builder rules = new RegenerationRules.Builder();
        Map<Location, Regenerable> newWorldMap = worldMap.entrySet().stream()
                .map(Map.Entry::getKey)
                .collect(toMap(Function.identity(),
                        location -> rules.withLivingNeighbors(countAt(location)).build().apply(mutableAt(location)),
                        (location, regenerable) -> location, LinkedHashMap::new));
        return new World(newWorldMap);
    }

    private Regenerable mutableAt(Location location) {
        return worldMap.get(location);
    }

    private long countAt(Location location) {
        return location.neighborhood()
                .map(worldMap::get)
                .filter(neighbor -> neighbor.equals(Cell.ALIVE))
                .count();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        AtomicInteger counter = new AtomicInteger(1);
        worldMap.entrySet().stream()
                .map(Map.Entry::getValue)
                .forEach(regenerable -> {
                    sb.append(regenerable);
                    if (counter.getAndIncrement() == SIZE) {
                        sb.append("\n");
                        counter.set(1);
                    }
                });
        return sb.toString();
    }
}
