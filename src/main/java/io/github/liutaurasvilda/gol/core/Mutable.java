package io.github.liutaurasvilda.gol.core;

import java.util.Objects;

interface Mutable {

    default Mutable mutate(MutationRules rules) {
        return Objects.requireNonNull(rules).apply(this);
    }
}
