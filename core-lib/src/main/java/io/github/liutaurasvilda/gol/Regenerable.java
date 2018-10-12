package io.github.liutaurasvilda.gol;

import java.util.Objects;
import java.util.function.Function;

interface Regenerable {

    default Regenerable regenerate(Function<Regenerable, Regenerable> f) {
        return Objects.requireNonNull(f).apply(this);
    }
}
