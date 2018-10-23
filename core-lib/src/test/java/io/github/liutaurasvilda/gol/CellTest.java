package io.github.liutaurasvilda.gol;

import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {

    @Test
    public void alive_cell_toString() {
        assertEquals("0", Cell.ALIVE.toString());
    }

    @Test
    public void empty_cell_toString() {
        assertEquals(".", Cell.EMPTY.toString());
    }
}
