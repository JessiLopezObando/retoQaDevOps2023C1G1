package com.sofkau.utils;

public enum MarvelResources {
    MARVEL_BASE_URL("https://gateway.marvel.com:443/v1/public/"),
    MARVEL_GET_SINGLE_CHARACTER_RESOURCE("characters/%s?ts=1&apikey=%s&hash=%s"),
    MARVEL_TOKEN("f0a55c0526a2a7495cce01466a651fa6"),
    MARVEL_HASH("b7026387801d6606abe480e0dd879b2e"),
    ATTRIBUTE_NAME("name"),
    ATTRIBUTE_DATA("data"),
    ATTRIBUTE_RESULTS("results"),
    ATTRIBUTE_ID("id");


    private final String value;

    MarvelResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
