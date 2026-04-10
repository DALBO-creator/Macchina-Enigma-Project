package com.albo.macchinaenigma;

public class Rotore {

    private final char[] mappatura = "EKMFLGDQVZNTOWYHXUSPAIBRCJ".toCharArray();

    private int posizione = 0;  //0 = A..

    public void ruota() {
        posizione = (posizione + 1) % 26; //routa il rotore di una tacca
    }


    public void setPosizione(char c) { //es: 'A' = 0
        posizione = c - 'A';
    }

    public int getPosizione() {
        return posizione;
    }

    /**
     * Cifra una lettera passando da sinistra verso destra (avanti).
     * Tiene conto della posizione attuale del rotore.
     */
    public char cifraAvanti(char c) {
        int indice = (c - 'A' + posizione) % 26;
        char cifrata = mappatura[indice];
        return (char) ((cifrata - 'A' - posizione + 26) % 26 + 'A');
    }

    /**
     * Cifra una lettera passando da destra verso sinistra (indietro).
     * È l'operazione inversa di cifraAvanti.
     */
    public char cifraIndietro(char c) {
        int indice = (c - 'A' + posizione) % 26;
        // Cerca la posizione nella mappatura
        for (int i = 0; i < 26; i++) {
            if (mappatura[i] == (char) (indice + 'A')) {
                return (char) ((i - posizione + 26) % 26 + 'A');
            }
        }
        return c; // non dovrebbe mai arrivare qui
    }
}