package com.sofkau.models.soap;

import java.util.HashMap;
import java.util.Map;

public class HeadersCard {
    private final Map<String, Object> headersCollection = new HashMap<>();

    public Map<String, Object> getHeadersCollection(){
        this.headersCollection.put("Content-Type", "application/soap+xml;charset=utf-8;action=\"http://localhost/SmartPayments/GetCardType");
        return headersCollection;
    }

    public static HeadersCard headersCard(){
        return new HeadersCard();
    }
}
