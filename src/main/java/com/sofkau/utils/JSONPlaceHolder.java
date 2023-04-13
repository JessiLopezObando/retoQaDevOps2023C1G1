package com.sofkau.utils;

public enum JSONPlaceHolder {

    PLACE_HOLDER_BASE_URL("https://jsonplaceholder.typicode.com/"),
    GET_POSTS("posts/");

    private final String  value;

    JSONPlaceHolder(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
