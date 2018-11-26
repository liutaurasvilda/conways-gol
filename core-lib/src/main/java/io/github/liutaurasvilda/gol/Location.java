package io.github.liutaurasvilda.gol;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

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

    int rowIndex() {
        return rowIndex;
    }

    int columnIndex() {
        return columnIndex;
    }

    Stream<Location> neighborhood() {
        return Arrays.stream(Direction.values())
                .map(this::neighbor);
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

        NORTH_WEST(-1, -1), NORTH(-1, 0), NORTH_EAST(-1, +1),
        WEST(0, -1),                      EAST(0, +1),
        SOUTH_WEST(+1, -1), SOUTH(+1, 0), SOUTH_EAST(+1, +1);

        private final int rowOffset;
        private final int columnOffset;

        Direction(int rowOffset, int columnOffset) {
            this.rowOffset = rowOffset;
            this.columnOffset = columnOffset;
        }

        private Location neighborOf(Location location) {
            return Location.of(location.rowIndex + rowOffset,
                    location.columnIndex + columnOffset);
        }
    }
}
