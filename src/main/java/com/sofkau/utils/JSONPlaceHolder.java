package com.sofkau.utils;

public enum JSONPlaceHolder {

    PLACE_HOLDER_BASE_URL("https://jsonplaceholder.typicode.com/"),

    GET_RESOURCE_USUARIO("users/"),
    JSON_PLACE_HOLDER("https://jsonplaceholder.typicode.com/"),
    GET_RESOURCE("posts/"),
    GET_POSTS("posts/"),
    PUT_RESOURCE("comments/");

    private final String  value;

    JSONPlaceHolder(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
