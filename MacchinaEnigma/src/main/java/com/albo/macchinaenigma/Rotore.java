package com.albo.macchinaenigma;

public class Rotore {

    // Mappature storiche dei 3 rotori
    private static final char[] ROTORE_I   = "EKMFLGDQVZNTOWYHXUSPAIBRCJ".toCharArray();
    private static final char[] ROTORE_II  = "AJDKSIRUXBLHWTMCQGZNPYFVOE".toCharArray();
    private static final char[] ROTORE_III = "BDFHJLCPRTXVZNYEIWGAKMUSQO".toCharArray();

    private final char[] mappatura;

    private int posizione = 0;  //0 = A..

    public Rotore(int numero) { //1, 2 o 3
        switch (numero) {
            case 2:  mappatura = ROTORE_II;  break;
            case 3:  mappatura = ROTORE_III; break;
            default: mappatura = ROTORE_I;   break;
        }
    }

    public void ruota() {
        posizione = (posizione + 1) % 26; //ruota il rotore di una tacca
    }

    public void setPosizione(char c) { //es: 'A' = 0
        posizione = c - 'A';
    }

    public int getPosizione() {
        return posizione;
    }

    public char cifraAvanti(char c) { //pos. attuale, va verso dx
        int indice = (c - 'A' + posizione) % 26;
        char cifrata = mappatura[indice];
        return (char) ((cifrata - 'A' - posizione + 26) % 26 + 'A');
    }

    public char cifraIndietro(char c) { //dopo il riflettore va verso sx
        int indice = (c - 'A' + posizione) % 26;
        for (int i = 0; i < 26; i++) {
            if (mappatura[i] == (char) (indice + 'A')) {
                return (char) ((i - posizione + 26) % 26 + 'A');
            }
        }
        return c; // non dovrebbe arrivare qui
    }

}