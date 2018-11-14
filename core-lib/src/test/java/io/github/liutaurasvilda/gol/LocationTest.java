package io.github.liutaurasvilda.gol;

import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LocationTest {

    @Test
    public void two_location_references_refer_to_same_object() {
        Location location = Location.of(0, 0);
        assertThat(location, is(location));
    }

    @Test
    public void two_locations_in_same_row_and_column_are_equal() {
        assertThat(Location.of(0, 0), is(equalTo(Location.of(0, 0))));
    }

    @Test
    public void two_locations_in_different_rows_are_not_equal() {
        assertThat(Location.of(4, 4), is(not(equalTo(Location.of(7, 4)))));
    }

    @Test
    public void two_locations_in_different_columns_are_not_equal() {
        assertThat(Location.of(4, 4), is(not(equalTo(Location.of(4, 7)))));
    }

    @Test
    public void two_locations_in_different_rows_and_different_columns_are_not_equal() {
        assertThat(Location.of(4, 4), is(not(equalTo(Location.of(7, 7)))));
    }

    @Test
    public void location_and_pure_object_are_not_equal() {
        assertThat(Location.of(0, 0), is(not(equalTo(new Object()))));
    }

    @Test
    public void location_and_null_are_not_equal() {
        assertThat(Location.of(0, 0), is(not(equalTo(null))));
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
        assertThat(Location.of(4, 4).neighborhood().toArray(),
                arrayContainingInAnyOrder(expectedNeighborhood.toArray()));
    }
}