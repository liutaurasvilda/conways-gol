package io.github.liutaurasvilda.gol.core;

import java.util.Objects;
import java.util.function.Function;

interface Mutable {

    default Mutable mutate(Function<Mutable, Mutable> f) {
        return Objects.requireNonNull(f).apply(this);
    }
}
