package com.sofkau.utils;

public enum RestfulBookerResources {
    RESTFUL_BOOKER_BASE_URL("https://restful-booker.herokuapp.com"),
    AUTHENTICATION_SUCCESSFUL_RESOURSE("/auth");

    private final String  value;

    RestfulBookerResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
