package com.sofkau.utils;

public enum FakeStoreResources {

    FAKE_STORE_URL("https://fakestoreapi.com"),
    PRODUCTS_RESOURCE("/products/"),

    USERS_RESOURCE("/users/");

    private final String value;

    FakeStoreResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
