package com.sofkau.utils;

public enum PathCreditCard {
    SOAP_CREDIT_CARD_VALIDATOR("https://secure.ftipgw.com/ArgoFire/"),
    RESOURCE_CREDIT_CARD("validate.asmx?WSDL"),

    CARD_BODY_PATH("src/test/resources/soap/filecredit.xml");



    private String value;

    PathCreditCard(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }




}
