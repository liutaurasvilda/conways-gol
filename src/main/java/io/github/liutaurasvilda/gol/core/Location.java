package io.github.liutaurasvilda.gol.core;

import java.util.*;
import java.util.stream.Collectors;

public final class Location {

    private final int x;
    private final int y;

    private Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Location of(int x, int y) {
        return new Location(x, y);
    }

    long numberOfLivingNeighborsAround(Map<Location, Mutable> map) {
        List<Location> neighbors = neighborsOf(this);
        return map.entrySet()
                  .stream()
                  .filter(e -> neighbors.contains(e.getKey()))
                  .filter(e -> e.getValue().phase() == Mutable.Phase.ALIVE).count();
    }

    private List<Location> neighborsOf(Location location) {
        List<Location> neighborsLocations = Arrays.asList(
                Location.of(-1, -1),
                Location.of(-1, 0),
                Location.of(-1, +1),
                Location.of(0, -1),
                Location.of(0, +1),
                Location.of(+1, -1),
                Location.of(+1, 0),
                Location.of(+1, +1)
        );
        return neighborsLocations
                .stream()
                .map(location::neighbor)
                .collect(Collectors.toList());
    }

    private Location neighbor(Location to) {
        return Location.of((x + to.x) % World.SIZE, (y + to.y) % World.SIZE);
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
