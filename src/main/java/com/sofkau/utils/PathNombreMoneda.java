package com.sofkau.utils;

public enum PathNombreMoneda {
    SOAP_MONEDA_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_MONEDAD("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH_MONEDA("src/test/resources/soap/filenombremoneda.xml");

    private String value;

    PathNombreMoneda(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
