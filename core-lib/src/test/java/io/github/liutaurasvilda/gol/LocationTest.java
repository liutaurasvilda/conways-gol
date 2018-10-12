package io.github.liutaurasvilda.gol;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class LocationTest {

    @Test
    public void two_same_locations_are_equal() {
        assertEquals(Location.of(0, 0), Location.of(0, 0));
    }

    @Test
    public void two_same_location_references_refer_to_same_object() {
        Location location = Location.of(0, 0);
        assertEquals(location, location);
    }

    @Test
    public void two_locations_in_different_columns_are_not_equal() {
        assertNotEquals(Location.of(4, 4), Location.of(4, 7));
    }

    @Test
    public void two_locations_in_different_rows_and_columns_are_not_equal() {
        assertNotEquals(Location.of(4, 4), Location.of(7, 7));
    }

    @Test
    public void location_and_pure_object_are_not_equal() {
        assertNotEquals(Location.of(0, 0), new Object());
    }

    @Test
    public void location_and_null_are_not_equal() {
        assertNotEquals(Location.of(0, 0), null);
    }

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