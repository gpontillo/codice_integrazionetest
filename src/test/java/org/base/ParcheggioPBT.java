package org.base;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import static org.junit.jupiter.api.Assertions.*;

public class ParcheggioPBT {
    @Property
    @Report(Reporting.GENERATED)
    void testCostoMinimoOreUgualeNumeroMinimoOre(
            @ForAll @IntRange(min = 0, max = 24) int numeroMinimoOre) {

        Parcheggio parcheggio = new Parcheggio(numeroMinimoOre, 0);

        assertEquals(2.0, parcheggio.calcolaCostoParcheggio(numeroMinimoOre));
    }

    @Property
    @Report(Reporting.GENERATED)
    void testCostoSuperioreNumeroMinimoOre(
            @ForAll @IntRange(min = 0, max = 23) int numeroMinimoOre,
            @ForAll @IntRange(min = 1, max = 24) int oreParcheggio) {

        // Ensuring oreParcheggio is greater than numeroMinimoOre for the test to be valid
        Assume.that(oreParcheggio > numeroMinimoOre);

        Parcheggio parcheggio = new Parcheggio(numeroMinimoOre, 0);

        double expectedCost = 2.0 + ((oreParcheggio - numeroMinimoOre) * 0.5);
        assertEquals(expectedCost, parcheggio.calcolaCostoParcheggio(oreParcheggio));
    }

    @Property
    @Report(Reporting.GENERATED)
    void testCostoMassimoApplicato(
            @ForAll @IntRange(min = 0, max = 23) int numeroMinimoOre,
            @ForAll @IntRange(min = 1, max = 24) int oreParcheggio,
            @ForAll @DoubleRange(min = 0.0, max = 100.0) double costoMassimo) {

        // Ensuring oreParcheggio is greater than numeroMinimoOre for the test to be valid
        Assume.that(oreParcheggio > numeroMinimoOre);

        Parcheggio parcheggio = new Parcheggio(numeroMinimoOre, costoMassimo);

        double expectedCost = 2.0 + ((oreParcheggio - numeroMinimoOre) * 0.5);
        if (costoMassimo > 0) {
            expectedCost = Math.min(expectedCost, costoMassimo);
        }
        assertEquals(expectedCost, parcheggio.calcolaCostoParcheggio(oreParcheggio));
    }
}
