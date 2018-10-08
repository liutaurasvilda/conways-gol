package gol;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author AxiomSL
 */
public class MutationRulesTest {

    @Test
    public void builds_mutation_rules_with_4_living_neighbors() {
        MutationRules rules = new MutationRules.Builder().livingNeighbors(4).build();
        assertEquals(4, rules.livingNeighbors());
    }
}