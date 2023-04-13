package com.sofkau.utils;

public enum Path {


    SOAP_CAPITAL_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_CAPITAL("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH("src/test/resources/soap/filecapital.xml"),

    BODY_PATH_PAIS("src/test/resources/soap/filepais.xml"),


    SOAP_PERSONA_BASE_URL("http://www.crcind.com/"),
    RESOURCE_PERSONA("csp/samples/SOAP.Demo.cls"),
    BODY_PATH_PERSONA("src/test/resources/soap/filepersona.xml"),



    SOAP_NUMERO_BASE_URL("https://www.dataaccess.com/"),
    RESOURCE_NUMERO("webservicesserver/numberconversion.wso"),
    BODY_PATH_NUMERO("src/test/resources/soap/fileNumeroALetra.xml"),


    //Rutas Jessica
    SOAP_FIND_BY_NAME_BASE_URL("http://www.crcind.com/"),
    RESOURCE_FIND_BY_NAME("csp/samples/SOAP.Demo.cls"),
    BODY_FIND_BY_NAME_PATH("src/test/resources/soap/findByName.xml"),

    SOAP_LOOK_UP_CITY_BASE_URL("http://www.crcind.com/"),
    RESOURCE_LOOK_UP_CITY("csp/samples/SOAP.Demo.cls"),
    BODY_LOOK_UP_CITY_PATH("src/test/resources/soap/lookUpCity.xml");




    private String value;

    Path(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
