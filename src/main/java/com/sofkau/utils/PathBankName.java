package com.sofkau.utils;

public enum PathBankName {
    SOAP_BANK_BASE_URL("http://www.thomas-bayer.com/axis2/"),
    RESOURCE_BANK("services/BLZService"),
    BANK_BODY_PATH("src/test/resources/soap/filebank.xml");
    private String value;

    PathBankName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
