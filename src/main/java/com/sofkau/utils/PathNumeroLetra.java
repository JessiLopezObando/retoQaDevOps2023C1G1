package com.sofkau.utils;

public enum PathNumeroLetra {

    SOAP_NUMERO_BASE_URL("https://www.dataaccess.com/"),
    RESOURCE_NUMERO("webservicesserver/NumberConversion.wso"),
    BODY_PATH_NUMERO("src/test/resources/soap/filenumerosLetras.xml");

    private String value;

    PathNumeroLetra(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
