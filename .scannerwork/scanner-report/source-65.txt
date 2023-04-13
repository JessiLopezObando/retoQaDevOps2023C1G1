package com.sofkau.utils;

public enum Path_ZipCode {
    SOAP_lAT_LONG_LIST_BASE_URL("https://graphical.weather.gov:443/"),
    RESOURCE_LAT_LONG("xml/SOAP_server/ndfdXMLserver.php"),
    BODY_PATH_ZIP_CODE("src/test/resources/soap/filezipcode.xml");

    private String value;

    Path_ZipCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
