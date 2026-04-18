package com.albo.macchinaenigma;

public class Riflettore {
    private final char[] mappatura = "YRUHQSLDPXNGOKMIEBFZCWVJAT".toCharArray();

    public char rifletti(char c) {
        return mappatura[c - 'A'];
    }
}