package io.github.liutaurasvilda.gol;

import java.util.Map;
import java.util.Objects;

final class Location {

    private final int x;
    private final int y;

    private Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    static Location of(int x, int y) {
        return new Location(x, y);
    }

    int numberOfLivingNeighborsAround(Map<Location, Cell> map, int size) {
        // TODO implement
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x &&
                y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
