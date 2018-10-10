package io.github.liutaurasvilda.gol.core;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

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
    public void location_and_pure_object_are_not_equal() {
        assertNotEquals(Location.of(0, 0), new Object());
    }

    @Test
    public void location_and_null_are_not_equal() {
        assertNotEquals(Location.of(0, 0), null);
    }

    @Test
    public void existing_location_is_found_in_locations_map() {
        Map<Location, Mutable> map = new LinkedHashMap<>();
        map.put(Location.of(4, 4), Cell.ALIVE);
        assertNotNull(map.get(Location.of(4, 4)));
    }

    @Test
    public void non_existing_location_is_not_found_in_locations_map() {
        Map<Location, Mutable> map = new LinkedHashMap<>();
        map.put(Location.of(4, 4), Cell.ALIVE);
        assertNull(map.get(Location.of(7, 7)));
    }
}