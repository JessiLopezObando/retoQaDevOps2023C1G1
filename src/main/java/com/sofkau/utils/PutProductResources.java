package com.sofkau.utils;

public enum PutProductResources {


    PUS_PRODUCT_RESOURCES_BASE_URL("https://fakestoreapi.com/"),

    PUT_PRODUCT_SUCCESSFUL_RESOURCES("products/");


    private final String value;

    PutProductResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
