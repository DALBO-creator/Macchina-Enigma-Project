package com.albo.macchinaenigma;


//Il riflettore è simmetrico: se A→Y allora Y→A, restituisce la lettera riflessa

public class Riflettore {
    private final char[] mappatura = "YRUHQSLDPXNGOKMIEBFZCWVJAT".toCharArray();

    public char rifletti(char c) {
        return mappatura[c - 'A'];
    }
}