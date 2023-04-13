package com.sofkau.utils;

public enum PhoneCode {
    SOAP_PHONE_CODE_URL("http://webservices.oorsprong.org/"),
    RESORUCE_PHONE_CODE("websamples.countryinfo/CountryInfoService.wso?WSDL"),
    BODY_PATH_PHONE_CODE("src/test/resources/soap/phonecode.xml");

    private String value;

    PhoneCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
