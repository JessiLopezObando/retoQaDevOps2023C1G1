package com.sofkau.utils;

public enum SubtractionCalculadora {
    SOAP_RESTA_CALCULADORA_URL("http://www.dneonline.com/"),
    RESORUCE_RESTA_CALCULADORA("calculator.asmx"),
    BODY_PATH_RESTA_CALCULADORA("src/test/resources/soap/subtractioncalculadora.xml");

    private String value;

    SubtractionCalculadora(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
