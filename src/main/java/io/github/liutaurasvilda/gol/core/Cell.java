package io.github.liutaurasvilda.gol.core;

enum Cell implements Mutable {

    ALIVE("0"), EMPTY(".");

    private final String symbol;

    Cell(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
