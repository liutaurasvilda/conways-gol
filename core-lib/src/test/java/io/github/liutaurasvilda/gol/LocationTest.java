package io.github.liutaurasvilda.gol;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class LocationTest {

    @Test
    public void location_neighborhood_identified_correctly() {
        Stream<Location> expectedNeighborhood = Stream.of(
                Location.of(3, 3),
                Location.of(3, 4),
                Location.of(3, 5),
                Location.of(4, 3),
                Location.of(4, 5),
                Location.of(5, 3),
                Location.of(5, 4),
                Location.of(5, 5)
        );
        assertArrayEquals(expectedNeighborhood.toArray(), Location.of(4, 4).neighborhood().toArray());
    }
}