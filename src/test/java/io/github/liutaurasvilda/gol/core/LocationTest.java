package io.github.liutaurasvilda.gol.core;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author AxiomSL
 */
public class LocationTest {

    @Test
    public void same_locations_are_equal() {
        assertEquals(Location.of(0, 0), Location.of(0, 0));
    }

    @Test
    public void existing_location_is_found_in_locations_map() {
        Map<Location, Cell> map = new HashMap<>();
        map.put(Location.of(4, 4), Cell.alive());
        assertNotNull(map.get(Location.of(4, 4)));
    }

    @Test
    public void non_existing_location_is_not_found_in_locations_map() {
        Map<Location, Cell> map = new HashMap<>();
        map.put(Location.of(4, 4), Cell.alive());
        assertNull(map.get(Location.of(7, 7)));
    }
}