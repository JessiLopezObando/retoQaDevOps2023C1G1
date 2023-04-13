package com.sofkau.models.soap;

import java.util.HashMap;
import java.util.Map;

public class HeadersCalculadora {
    private final Map<String, Object> headersCollection = new HashMap<>();

    public Map<String, Object> getHeadersCollection() {
        this.headersCollection.put("Content-Type", "text/xml;charset=UTF-8");
        this.headersCollection.put("SOAPAction", "http://tempuri.org/Add");
        return headersCollection;
    }

    public static HeadersCalculadora headersCalculadora() {
        return new HeadersCalculadora();
    }
}
