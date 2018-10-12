package io.github.liutaurasvilda.gol.core;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Location {

    private final int rowIndex;
    private final int columnIndex;

    private Location(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public static Location of(int rowIndex, int columnIndex) {
        return new Location(rowIndex, columnIndex);
    }

    List<Location> neighborhood() {
        List<Location> distances = Arrays.asList(
                Location.of(-1, -1),
                Location.of(-1, 0),
                Location.of(-1, +1),
                Location.of(0, -1),
                Location.of(0, +1),
                Location.of(+1, -1),
                Location.of(+1, 0),
                Location.of(+1, +1)
        );
        return distances.stream()
                .map(this::neighbor)
                .collect(Collectors.toList());
    }

    private Location neighbor(Location distance) {
        return Location.of((rowIndex + distance.rowIndex + World.SIZE) % World.SIZE,
                (columnIndex + distance.columnIndex + World.SIZE) % World.SIZE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return rowIndex == location.rowIndex &&
                columnIndex == location.columnIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, columnIndex);
    }
}
