package io.github.liutaurasvilda.gol;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Stream;

import static io.github.liutaurasvilda.gol.Cell.*;
import static java.util.stream.Collectors.toMap;

public final class World {

    private static final int DEFAULT_SIZE = 10;

    private final Map<Location, Cell> worldMap;
    private final int worldSize;

    private World(Map<Location, Cell> worldMap, int worldSize) {
        this.worldMap = worldMap;
        this.worldSize = worldSize;
    }

    public static World generation(List<Location> seed, int size) {
        if (Objects.requireNonNull(seed).stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Seed location cannot be null");
        }
        int worldSize = size > DEFAULT_SIZE ? size : DEFAULT_SIZE;
        Map<Location, Cell> worldMap = seed.stream()
                .map(location -> new SimpleEntry<>(worldWrapped(location, worldSize), ALIVE))
                .collect(toMap(SimpleEntry::getKey, SimpleEntry::getValue));
        return new World(worldMap, worldSize);
    }

    public boolean hasPopulation() {
        return !worldMap.isEmpty();
    }

    public World nextGeneration() {
        Map<Location, Cell> newWorldMap = worldLocations()
                .map(location -> new SimpleEntry<>(location,
                        cellAt(location).next(livingNeighborsOf(location))))
                .filter(e -> e.getValue() == ALIVE)
                .collect(toMap(SimpleEntry::getKey, SimpleEntry::getValue));
        return new World(newWorldMap, worldSize);
    }

    private Stream<Location> worldLocations() {
        return IntStream.range(0, worldSize)
                .mapToObj(rowIndex -> IntStream.range(0, worldSize)
                        .mapToObj(columnIndex -> Location.of(rowIndex, columnIndex)))
                .flatMap(Function.identity());
    }

    private Cell cellAt(Location location) {
        Cell existing = worldMap.get(location);
        return existing != null ? existing : DEAD;
    }

    private int livingNeighborsOf(Location location) {
        return (int)location.neighborhood()
                .map(neighborLocation -> worldMap.get(worldWrapped(neighborLocation, worldSize)))
                .filter(neighbor -> neighbor == ALIVE)
                .count();
    }

    private static Location worldWrapped(Location location, int size) {
        int rowIndex = (location.rowIndex() + size) % size;
        int columnIndex = (location.columnIndex() + size) % size;
        return Location.of(rowIndex < 0 ? rowIndex + size : rowIndex,
                columnIndex < 0 ? columnIndex + size : columnIndex);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < worldSize; i++) {
            for (int j = 0; j < worldSize; j++) {
                sb.append(cellAt(Location.of(i, j)) == ALIVE ? "0" : ".");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
