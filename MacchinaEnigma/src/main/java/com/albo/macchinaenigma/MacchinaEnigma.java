package com.albo.macchinaenigma;

public class MacchinaEnigma {

    // I 3 rotori (sinistro, medio, destro)
    private Rotore rotoreSinistro = new Rotore(1);
    private Rotore rotoreMedio    = new Rotore(2);
    private Rotore rotoreDestro   = new Rotore(3);

    private Riflettore riflettore       = new Riflettore();
    private PannelloPrese pannelloPrese = new PannelloPrese();

    //  CONFIGURAZIONE

    public void setPosizioniIniziali(char sin, char med, char des) {
        rotoreSinistro.setPosizione(sin);
        rotoreMedio.setPosizione(med);
        rotoreDestro.setPosizione(des);
    }

    public void configuraPannello(String coppie) { //es: "AB CD EF"
        pannelloPrese.configura(coppie);
    }

    public void reset() { //riporta tutto alla posizione iniziale
        rotoreSinistro.setPosizione('A');
        rotoreMedio.setPosizione('A');
        rotoreDestro.setPosizione('A');
    }

    //  ROTAZIONE

    private void ruotaRotori() {
        rotoreDestro.ruota(); //ruota sempre il destro

        if (rotoreDestro.getPosizione() == 0) { //completa giro
            rotoreMedio.ruota();

            if (rotoreMedio.getPosizione() == 0) { //completa giro
                rotoreSinistro.ruota();
            }
        }
    }

    // CIFRATURA

    public char cifra(char c) {
        ruotaRotori(); //prima ruotano i rotori

        // 1. Pannello prese (ingresso)
        c = pannelloPrese.scambia(c);

        // 2. Rotori avanti: destro > medio > sinistro
        c = rotoreDestro.cifraAvanti(c);
        c = rotoreMedio.cifraAvanti(c);
        c = rotoreSinistro.cifraAvanti(c);

        // 3. Riflettore
        c = riflettore.rifletti(c);

        // 4. Rotori indietro: sinistro > medio > destro
        c = rotoreSinistro.cifraIndietro(c);
        c = rotoreMedio.cifraIndietro(c);
        c = rotoreDestro.cifraIndietro(c);

        // 5. Pannello prese (uscita)
        c = pannelloPrese.scambia(c);

        return c;
    }

    public Rotore getRotoreSinistro() {
        return this.rotoreSinistro;
    }

    public Rotore getRotoreDestro(){
        return this.rotoreDestro;
    }

    public Rotore getRotoreMedio() {
        return this.rotoreMedio;
    }
}