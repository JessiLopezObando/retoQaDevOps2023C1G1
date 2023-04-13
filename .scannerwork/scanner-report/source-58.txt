package com.sofkau.utils;

public enum FreetoGameResources {

    FREETOGAME_RESOURCES_BASE_URL("https://www.freetogame.com"),

    FREETOGAME_GET_RESOURCE("/api/game?id=");


    private final String value;

    FreetoGameResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
