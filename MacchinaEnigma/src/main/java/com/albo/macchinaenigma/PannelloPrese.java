package com.albo.macchinaenigma;

public class PannelloPrese {

    private final char[] scambi = new char[26];

    public PannelloPrese() {

        for (int i = 0; i < 26; i++) {
            scambi[i] = (char) ('A' + i);
        }
    }

    public void configura(String coppie) {
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

    public char scambia(char c) {
        return scambi[c - 'A'];
    }
}