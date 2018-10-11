package io.github.liutaurasvilda.gol.core;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
        return Location.of((r + distance.r + World.SIZE) % World.SIZE,
                (c + distance.c + World.SIZE) % World.SIZE);
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
