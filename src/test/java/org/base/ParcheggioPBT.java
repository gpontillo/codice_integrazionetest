package org.base;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import net.jqwik.api.statistics.Statistics;

import static org.junit.jupiter.api.Assertions.*;

public class ParcheggioPBT {
    @Property
    @Report(Reporting.GENERATED)
    void costoMinimoRispettato(
            @ForAll("oreParcheggioMinoriUguali") OreCombinate oreCombinate) {

        Statistics.label("oreParcheggio").collect(oreCombinate.oreParcheggio);
        Statistics.label("numeroMinimoOre").collect(oreCombinate.numeroMinimoOre);
        Statistics.label("Minori o uguali").collect(oreCombinate.oreParcheggio < oreCombinate.numeroMinimoOre ? "Minore" : "Uguale");

        Parcheggio parcheggio = new Parcheggio(oreCombinate.numeroMinimoOre, 0);

        assertEquals(2.0, parcheggio.calcolaCostoParcheggio(oreCombinate.oreParcheggio));
    }

    @Property
    @Report(Reporting.GENERATED)
    void costoSuperioreNumeroMinimoOre(
            @ForAll("oreParcheggioMaggiori") OreCombinate oreCombinate) {

        Statistics.label("oreParcheggio").collect(oreCombinate.oreParcheggio);
        Statistics.label("numeroMinimoOre").collect(oreCombinate.numeroMinimoOre);
        Statistics.label("Differenza").collect(oreCombinate.oreParcheggio - oreCombinate.numeroMinimoOre);

        Parcheggio parcheggio = new Parcheggio(oreCombinate.numeroMinimoOre, 0);

        double expectedCost = 2.0 + ((oreCombinate.oreParcheggio - oreCombinate.numeroMinimoOre) * 0.5);
        assertEquals(expectedCost, parcheggio.calcolaCostoParcheggio(oreCombinate.oreParcheggio));
    }

    @Property
    @Report(Reporting.GENERATED)
    void costoMassimoApplicato(
            @ForAll("oreParcheggioMaggiori") OreCombinate oreCombinate,
            @ForAll @DoubleRange(min = 0.0, max = 14.0) double costoMassimo) {
        Parcheggio parcheggio = new Parcheggio(oreCombinate.numeroMinimoOre, costoMassimo);

        double expectedCost = 2.0 + ((oreCombinate.oreParcheggio - oreCombinate.numeroMinimoOre) * 0.5);
        if (costoMassimo > 0) {
            expectedCost = Math.min(expectedCost, costoMassimo);
        }

        Statistics.label("oreParcheggio").collect(oreCombinate.oreParcheggio);
        Statistics.label("costoMassimo").collect(costoMassimo);
        Statistics.label("Supera?").collect(expectedCost > costoMassimo);

        assertEquals(expectedCost, parcheggio.calcolaCostoParcheggio(oreCombinate.oreParcheggio));
    }

    @Property
    @Report(Reporting.GENERATED)
    void calcoloCostoSenzaCondizioniDati(
            @ForAll @DoubleRange(min = 0, max = 23) int numeroMinimoOre,
            @ForAll @DoubleRange(min = 1, max = 24) int oreParcheggio,
            @ForAll @DoubleRange(min = 0.0, max = 14.0) double costoMassimo) {
        Parcheggio parcheggio = new Parcheggio(numeroMinimoOre, costoMassimo);

        double expectedCost = oreParcheggio > numeroMinimoOre ? 2.0 + ((oreParcheggio - numeroMinimoOre) * 0.5) : 2.0;
        if (costoMassimo > 0) {
            expectedCost = Math.min(expectedCost, costoMassimo);
        }

        assertEquals(expectedCost, parcheggio.calcolaCostoParcheggio(oreParcheggio));
    }

    @Provide
    Arbitrary<OreCombinate> oreParcheggioMaggiori() {
        Arbitrary<Integer> numeroMinimoOre = Arbitraries.integers().between(0, 23);
        Arbitrary<Integer> oreParcheggio = Arbitraries.integers().between(1, 24);

        return Combinators.combine(numeroMinimoOre, oreParcheggio).as(OreCombinate::new).filter(k -> k.oreParcheggio > k.numeroMinimoOre);
    }

    @Provide
    Arbitrary<OreCombinate> oreParcheggioMinoriUguali() {
        Arbitrary<Integer> numeroMinimoOre = Arbitraries.integers().between(0, 23);
        Arbitrary<Integer> oreParcheggio = Arbitraries.integers().between(1, 24);

        return Combinators.combine(numeroMinimoOre, oreParcheggio).as(OreCombinate::new).filter(k -> k.oreParcheggio <= k.numeroMinimoOre);
    }

    // Classe usata per definire la condizione per la generazione dei parametri
    private class OreCombinate {
        public int numeroMinimoOre;
        public int oreParcheggio;

        public OreCombinate(int numeroMinimoOre, int oreParcheggio) {
            this.numeroMinimoOre = numeroMinimoOre;
            this.oreParcheggio = oreParcheggio;
        }
    }
}
