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

    public static void main(String[] args) {
        // Esempio di utilizzo della classe Parcheggio
        Parcheggio parcheggio = new Parcheggio(3, 10, true); // Numero minimo di ore: 3, Costo massimo: 10 euro, Massimo 24 ore? Si

        // Esempio di calcolo del costo del parcheggio per 8 ore
        int oreParcheggio = 28;
        double costo = parcheggio.calcolaCostoParcheggio(oreParcheggio);
        System.out.println("Il costo del parcheggio per " + oreParcheggio + " ore è di: " + costo + " euro");
    }
}
