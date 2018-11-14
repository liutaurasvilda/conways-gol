package io.github.liutaurasvilda.gol;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.AbstractMap.SimpleEntry;

import static java.util.stream.Collectors.toMap;

public final class World {

    private static final int DEFAULT_SIZE = 10;

    private final Map<Location, Cell> worldMap;
    private int size;

    private World(Map<Location, Cell> worldMap, int size) {
        this.worldMap = worldMap;
        this.size = size;
    }

    public static World empty() {
        return new World(new HashMap<>(), DEFAULT_SIZE);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size > DEFAULT_SIZE) {
            this.size = size;
        }
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
        Map<Location, Cell> newWorldMap = IntStream.range(0, size)
                .mapToObj(rowIndex -> IntStream.range(0, size)
                        .mapToObj(columnIndex -> Location.of(rowIndex, columnIndex)))
                .flatMap(Function.identity())
                .map(location -> new SimpleEntry<>(location,
                        Cell.inNextGeneration(at(location), havingLivingNeighborsAround(location))))
                .filter(e -> e.getValue() == Cell.ALIVE)
                .collect(toMap(SimpleEntry::getKey, SimpleEntry::getValue));
        return new World(newWorldMap, this.size);
    }

    private Cell at(Location location) {
        Cell cell = worldMap.get(location);
        return cell != null ? cell : Cell.DEAD;
    }

    private int havingLivingNeighborsAround(Location location) {
        return (int)location.neighborhood()
                .map(neighborLocation -> worldMap.get(worldWrapped(neighborLocation)))
                .filter(neighbor -> neighbor == Cell.ALIVE)
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
                sb.append(at(Location.of(i, j)) == Cell.ALIVE ? "0" : ".");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
