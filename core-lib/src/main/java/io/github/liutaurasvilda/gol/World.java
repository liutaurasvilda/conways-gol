package io.github.liutaurasvilda.gol;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Stream;

import static io.github.liutaurasvilda.gol.Cell.ALIVE;
import static io.github.liutaurasvilda.gol.Cell.DEAD;
import static java.util.stream.Collectors.toMap;

public final class World {

    private static final int DEFAULT_SIZE = 10;

    private final Map<Location, Cell> worldMap;
    private final int size;

    private World(Map<Location, Cell> worldMap, int size) {
        this.worldMap = worldMap;
        this.size = size;
    }

    public static World generation(List<Location> seed, int worldSize) {
        if (Objects.requireNonNull(seed).stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Seed location cannot be null");
        }
        int size = Math.max(worldSize, DEFAULT_SIZE);
        Map<Location, Cell> worldMap = seed.stream()
                .map(location -> new SimpleEntry<>(worldWrapped(location, size), ALIVE))
                .collect(toMap(SimpleEntry::getKey, SimpleEntry::getValue));
        return new World(worldMap, size);
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
        return new World(newWorldMap, size);
    }

    private Stream<Location> worldLocations() {
        return IntStream.range(0, size)
                .mapToObj(rowIndex -> IntStream.range(0, size)
                        .mapToObj(columnIndex -> Location.of(rowIndex, columnIndex)))
                .flatMap(Function.identity());
    }

    private Cell cellAt(Location location) {
        Cell existing = worldMap.get(location);
        return existing != null ? existing : DEAD;
    }

    private int livingNeighborsOf(Location location) {
        return (int) location.neighborhood()
                .map(neighborLocation -> worldMap.get(worldWrapped(neighborLocation, size)))
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
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(cellAt(Location.of(i, j)) == ALIVE ? "0" : ".");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
