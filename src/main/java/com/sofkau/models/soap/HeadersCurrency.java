package com.sofkau.models.soap;

import java.util.HashMap;
import java.util.Map;

public class HeadersCurrency {
    private final Map<String, Object> headersCollection = new HashMap<>();

    public Map<String, Object> getHeadersCollection(){
        this.headersCollection.put("Content-Type","text/xml;charset=UTF-8");
        this.headersCollection.put("SOAPAction","http://webservices.cloanto.com/currencyserver/ConvertToNum");
        return headersCollection;
    }

    public static HeadersCurrency headersCurrency(){
        return new HeadersCurrency();
    }
}
