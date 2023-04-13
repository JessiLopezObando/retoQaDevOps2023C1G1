package com.sofkau.utils;

public enum ProductResources {


    PRODUCT_RESOURCES_BASE_URL("https://fakestoreapi.com/"),
    PRODUCT_SUCCESSFUL_RESOURCES("products/");


    private final String value;

    ProductResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


