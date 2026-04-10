package com.albo.macchinaenigma;

//configuri "AB", ogni A diventa B e ogni B diventa A.

public class PannelloPrese {

    private final char[] scambi = new char[26];

    public PannelloPrese() {

        for (int i = 0; i < 26; i++) {
            scambi[i] = (char) ('A' + i);
        }
    }


     //Configura le coppie di scambio a partire da una stringa, es: "AB CD EF" → scambia A-B, C-D, E-F

    public void configura(String coppie) {
        //Reset prima di configurare
        for (int i = 0; i < 26; i++) {
            scambi[i] = (char) ('A' + i);
        }

        String[] parti = coppie.toUpperCase().split(" ");
        for (String coppia : parti) {
            if (coppia.length() == 2) {
                char a = coppia.charAt(0);
                char b = coppia.charAt(1);
                scambi[a - 'A'] = b;
                scambi[b - 'A'] = a;
            }
        }
    }

    //restituisce la lettera scambiata, o se non ci sono scambi la lettera stessa

    public char scambia(char c) {
        return scambi[c - 'A'];
    }
}