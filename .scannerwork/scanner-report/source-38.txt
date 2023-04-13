package com.sofkau.models.rest;

public class Screenshot {


    private int id;
    private String image;

    public Screenshot(int id, String image) {
        this.id = id;
        this.image = image;
    }

    public Screenshot() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
