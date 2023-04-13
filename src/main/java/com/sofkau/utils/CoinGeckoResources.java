package com.sofkau.utils;

public enum CoinGeckoResources {

    COIN_GECKO_BASE_URL("https://api.coingecko.com/"),
    GET_EXCHANGES_RESOURCE("api/v3/exchanges/binance"),
    GET_PUBLIC_COMPANIES_RESOURCE("api/v3/companies/public_treasury/bitcoin"),
    PUBLIC_COMPANIES_NAME("companies.name"),
    PUBLIC_COMPANIES_SYMBOL("companies.symbol"),
    PUBLIC_COMPANIES_COUNTRY("companies.country");

    private final String value;

    CoinGeckoResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
