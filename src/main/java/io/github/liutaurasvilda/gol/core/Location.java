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
        return Arrays.stream(Direction.values())
                .map(this::neighbor)
                .collect(Collectors.toList());
    }

    private Location neighbor(Direction direction) {
        return direction.neighborOf(this);
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

    private enum Direction {

        TOP_LEFT(-1, -1),    TOP(-1, 0),    TOP_RIGHT(-1, +1),
        LEFT(0, -1),                        RIGHT(0, +1),
        BOTTOM_LEFT(+1, -1), BOTTOM(+1, 0), BOTTOM_RIGHT(+1, +1);

        private final int rowIndex;
        private final int columnIndex;

        Direction(int rowIndex, int columnIndex) {
            this.rowIndex = rowIndex;
            this.columnIndex = columnIndex;
        }

        Location neighborOf(Location location) {
            return Location.of((location.rowIndex + rowIndex + World.SIZE) % World.SIZE,
                    (location.columnIndex + columnIndex + World.SIZE) % World.SIZE);
        }
    }
}
