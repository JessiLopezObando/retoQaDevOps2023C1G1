package com.sofkau.models.rest;

public class ResponseExchange {

    private String name;
    private String yearEstablished;
    private String country;
    private String trustScore;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearEstablished() {
        return yearEstablished;
    }

    public void setYearEstablished(String yearEstablished) {
        this.yearEstablished = yearEstablished;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTrustScore() {
        return trustScore;
    }

    public void setTrustScore(String trustScore) {
        this.trustScore = trustScore;
    }
}
