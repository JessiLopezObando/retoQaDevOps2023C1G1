package com.sofkau.utils;

public enum PathCapital {
    SOAP_CAPITAL_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_CAPITAL("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH("src/test/resources/soap/filecapital.xml");

    private String value;

    PathCapital(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
