package com.sofkau.utils;

public enum PathNumberToDollars {
    SOAP_CAPITAL_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_CAPITAL("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH("src/test/resources/soap/filecapital.xml"),

    SOAP_NUMBER_TO_WORDS_BASE_URL("https://www.dataaccess.com/"),
    RESOURCE_NUMBER_TO_WORDS("webservicesserver/numberconversion.wso?op=NumberToWords"),
    BODY_PATH_NUMBER_TO_WORDS("src/test/resources/soap/filenumbertowords.xml"),
    RESOURCE_NUMBER_TO_DOLLARS("webservicesserver/numberconversion.wso?op=NumberToDollars"),
    BODY_PATH_NUMBER_TO_DOLLARS("src/test/resources/soap/filenumbertodollars.xml"),
    SOAP_CURRENCY_OF_A_COUNTRY_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_CURRENCY_OF_A_COUNTRY("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH_CURRENCY_OF_A_COUNTRY("src/test/resources/soap/filecurrencyofacountry.xml");

    private String value;

    PathNumberToDollars(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
