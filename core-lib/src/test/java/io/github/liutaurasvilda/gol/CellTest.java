package io.github.liutaurasvilda.gol;

import org.junit.Test;

import static io.github.liutaurasvilda.gol.Cell.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CellTest {

    @Test
    public void alive_cell_with_less_than_2_living_neighbors_dies() {
        assertThat(ALIVE.next(1), is(DEAD));
    }

    @Test
    public void alive_cell_with_2_or_3_living_neighbors_lives_on() {
        assertThat(ALIVE.next(2), is(ALIVE));
        assertThat(ALIVE.next(3), is(ALIVE));
    }

    @Test
    public void alive_cell_with_more_than_3_living_neighbors_dies() {
        assertThat(ALIVE.next(4), is(DEAD));
    }

    @Test
    public void dead_cell_with_exactly_3_living_neighbors_comes_to_life() {
        assertThat(DEAD.next(3), is(ALIVE));
    }
}