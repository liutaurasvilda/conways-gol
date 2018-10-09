package io.github.liutaurasvilda.gol.core;

import java.util.*;
import java.util.stream.Collectors;

public final class Location {

    private final int r;
    private final int c;

    private Location(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public static Location of(int r, int c) {
        return new Location(r, c);
    }

    long numberOfLivingNeighborsInA(Map<Location, Mutable> map) {
        List<Location> neighbors = neighborsOf(this);
        return map.entrySet()
                  .stream()
                  .filter(e -> neighbors.contains(e.getKey()))
                  .filter(e -> e.getValue().phase() == Mutable.Phase.ALIVE).count();
    }

    private List<Location> neighborsOf(Location location) {
        List<Location> neighborsCoordinates = Arrays.asList(
                Location.of(-1, -1),
                Location.of(-1, 0),
                Location.of(-1, +1),
                Location.of(0, -1),
                Location.of(0, +1),
                Location.of(+1, -1),
                Location.of(+1, 0),
                Location.of(+1, +1)
        );
        return neighborsCoordinates
                .stream()
                .map(location::neighbor)
                .collect(Collectors.toList());
    }

    private Location neighbor(Location to) {
        return Location.of((r + to.r) % World.SIZE, (c + to.c) % World.SIZE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return r == location.r &&
                c == location.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, c);
    }
}
