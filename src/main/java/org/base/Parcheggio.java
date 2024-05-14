package org.base;

public class Parcheggio {
    private int numeroMinimoOre;
    private double costoMassimo = 0;
    private boolean maxUnGiorno;

    // Costruttore
    public Parcheggio(int numeroMinimoOre, double costoMassimo, boolean maxUnGiorno) {
        this.numeroMinimoOre = numeroMinimoOre;
        this.costoMassimo = costoMassimo;
        this.maxUnGiorno = maxUnGiorno;
    }

    // Metodo per calcolare il costo del parcheggio
    public double calcolaCostoParcheggio(int oreParcheggio) {
        double costoParcheggio;

        if (oreParcheggio <= numeroMinimoOre) {
            costoParcheggio = 2.0; // Costo minimo di 2 euro
        } else {
            if (maxUnGiorno)
                costoParcheggio = 2.0 + ((Math.min(oreParcheggio, 24) - numeroMinimoOre) * 0.5); // 50 centesimi per ogni ora in più fino ad un max di 24 ore
            else
                costoParcheggio = 2.0 + ((oreParcheggio - numeroMinimoOre) * 0.5); // 50 centesimi per ogni ora in più
        }

        // Se il costo massimo è stato assegnato o no
        if (costoMassimo > 0)
        // Se il costo supera il costo massimo, restituisci il costo massimo
            return Math.min(costoParcheggio, costoMassimo);
        else
            return costoParcheggio;
    }
}
