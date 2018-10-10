package io.github.liutaurasvilda.gol.core;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class LocationTest {

    @Test
    public void same_locations_are_equal() {
        assertEquals(Location.of(0, 0), Location.of(0, 0));
    }

    @Test
    public void existing_location_is_found_in_locations_map() {
        Map<Location, Mutable> map = new LinkedHashMap<>();
        map.put(Location.of(4, 4), Cell.alive());
        assertNotNull(map.get(Location.of(4, 4)));
    }

    @Test
    public void non_existing_location_is_not_found_in_locations_map() {
        Map<Location, Mutable> map = new LinkedHashMap<>();
        map.put(Location.of(4, 4), Cell.alive());
        assertNull(map.get(Location.of(7, 7)));
    }

    @Test
    public void number_of_living_neighbors_in_a_given_map() {
        Map<Location, Mutable> map = new LinkedHashMap<>();
        map.put(Location.of(4, 4), Cell.alive());
        map.put(Location.of(4, 5), Cell.alive());
        map.put(Location.of(4, 6), Cell.alive());
        assertEquals(2, Location.of(4, 5).numberOfLivingNeighborsInA(map));
    }
}