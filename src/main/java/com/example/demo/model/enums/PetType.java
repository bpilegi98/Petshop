package com.example.demo.model.enums;

public enum PetType {
    CAT("cat"),
    DOG("dog"),
    EXOTIC("exotic");

    private final String string;

    PetType(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
