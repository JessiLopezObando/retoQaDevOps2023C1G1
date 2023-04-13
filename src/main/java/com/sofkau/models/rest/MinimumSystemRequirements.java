package com.sofkau.models.rest;

public class MinimumSystemRequirements {

    private String os;
    private String processor;
    private String memory;
    private String graphics;
    private String storage;

    public MinimumSystemRequirements(String os, String processor, String memory, String graphics, String storage) {
        this.os = os;
        this.processor = processor;
        this.memory = memory;
        this.graphics = graphics;
        this.storage = storage;
    }

    public MinimumSystemRequirements() {
    }


    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getGraphics() {
        return graphics;
    }

    public void setGraphics(String graphics) {
        this.graphics = graphics;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }
}
