package com.sofkau.utils;

public enum PathNombreMoneda {
    SOAP_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_SOAP("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH_MONEDA("src/test/resources/soap/filenombremoneda.xml"),
    BODY_PATH_LENGUAJE("src/test/resources/soap/filelenguaje.xml");

    private String value;

    PathNombreMoneda(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
