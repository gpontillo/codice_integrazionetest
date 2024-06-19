package org.base;

public class Parcheggio {
    private int numeroMinimoOre;
    private double costoMassimo = 0;

    // Costruttore
    public Parcheggio(int numeroMinimoOre, double costoMassimo) {
        this.numeroMinimoOre = numeroMinimoOre;
        this.costoMassimo = costoMassimo;
    }

    // Metodo per calcolare il costo del parcheggio
    public double calcolaCostoParcheggio(int oreParcheggio) {
        double costoParcheggio;

        if (oreParcheggio <= numeroMinimoOre) {
            costoParcheggio = 2.0; // Costo minimo di 2 euro
        } else {
            // Limita il quantitativo ore a un giorno o illimitato
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
