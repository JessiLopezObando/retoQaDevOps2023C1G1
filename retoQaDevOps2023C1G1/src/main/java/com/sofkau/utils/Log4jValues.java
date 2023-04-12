package com.sofkau.utils;

public enum Log4jValues {
    LOG4J_PROPERTY_PATH("src/test/resources/properties/log4j.properties");

    private final String value;

    Log4jValues(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
