package io.github.liutaurasvilda.gol;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.AbstractMap.SimpleEntry;

import static java.util.stream.Collectors.toMap;

public final class World {

    private final int size = 10;
    private final Map<Location, Regenerable> worldMap;

    private World(Map<Location, Regenerable> worldMap) {
        this.worldMap = worldMap;
    }

    public static World empty() {
        return new World(new HashMap<>());
    }

    public void aliveAt(Location location) {
        worldMap.put(Objects.requireNonNull(
                worldWrapped(Location.of(
                        Math.abs(location.rowIndex()),
                        Math.abs(location.columnIndex())))),
                Cell.ALIVE);
    }

    public boolean hasPopulation() {
        return !worldMap.isEmpty();
    }

    public World nextGeneration() {
        RegenerationRules.Builder rules = new RegenerationRules.Builder();
        Map<Location, Regenerable> newWorldMap = IntStream.range(0, size)
                .mapToObj(rowIndex -> IntStream.range(0, size)
                        .mapToObj(columnIndex -> Location.of(rowIndex, columnIndex)))
                .flatMap(Function.identity())
                .map(location -> new SimpleEntry<>(location,
                        rules.withLivingNeighbors(numberOfLivingNeighborsAt(location)).build().apply(regenerableAt(location))))
                .filter(e -> e.getValue() != null)
                .collect(toMap(SimpleEntry::getKey, SimpleEntry::getValue));
        return new World(newWorldMap);
    }

    private Regenerable regenerableAt(Location location) {
        return worldMap.get(location);
    }

    private long numberOfLivingNeighborsAt(Location location) {
        return location.neighborhood()
                .map(neighbor -> worldMap.get(worldWrapped(neighbor)))
                .filter(Cell.ALIVE::equals)
                .count();
    }

    private Location worldWrapped(Location location) {
        return Location.of((location.rowIndex() + size) % size,
                (location.columnIndex() + size) % size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(Cell.ALIVE.equals(regenerableAt(Location.of(i, j))) ? "0" : ".");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
