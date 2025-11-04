package com.example.conversor;

/**
 * Enum para códigos de moneda usados en el menú.
 */
public enum Moneda {
    USD("USD"),
    ARS("ARS"),
    BRL("BRL"),
    COP("COP"),
    CLP("CLP"),
    PHP("PHP");

    private final String code;

    Moneda(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
