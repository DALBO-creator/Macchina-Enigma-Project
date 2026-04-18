package com.albo.macchinaenigma;

public class MacchinaEnigma {

    private Rotore rotoreSinistro = new Rotore(1);
    private Rotore rotoreMedio    = new Rotore(2);
    private Rotore rotoreDestro   = new Rotore(3);

    private Riflettore riflettore       = new Riflettore();
    private PannelloPrese pannelloPrese = new PannelloPrese();

    public void setPosizioniIniziali(char sin, char med, char des) {
        rotoreSinistro.setPosizione(sin);
        rotoreMedio.setPosizione(med);
        rotoreDestro.setPosizione(des);
    }

    public void configuraPannello(String coppie) {
        pannelloPrese.configura(coppie);
    }

    public void reset() {
        rotoreSinistro.setPosizione('A');
        rotoreMedio.setPosizione('A');
        rotoreDestro.setPosizione('A');
    }

    private void ruotaRotori() {
        rotoreDestro.ruota();

        if (rotoreDestro.getPosizione() == 0) {
            rotoreMedio.ruota();

            if (rotoreMedio.getPosizione() == 0) {
                rotoreSinistro.ruota();
            }
        }
    }

    public char cifra(char c) {
        ruotaRotori();

        c = pannelloPrese.scambia(c);

        c = rotoreDestro.cifraAvanti(c);
        c = rotoreMedio.cifraAvanti(c);
        c = rotoreSinistro.cifraAvanti(c);

        c = riflettore.rifletti(c);

        c = rotoreSinistro.cifraIndietro(c);
        c = rotoreMedio.cifraIndietro(c);
        c = rotoreDestro.cifraIndietro(c);

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