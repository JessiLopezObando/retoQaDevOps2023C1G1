package com.sofkau.utils;

public enum PathCity {
    SOAP_CITY_NAME_URL("https://graphical.weather.gov:443/"),
    RESORUCE_CITY("xml/SOAP_server/ndfdXMLserver.php"),
    BODY_PATH_CITY("src/test/resources/soap/cityname.xml");

    private String value;

    PathCity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
