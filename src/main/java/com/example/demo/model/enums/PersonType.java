package com.example.demo.model.enums;

public enum PersonType {
    EMPLOYEE("employee"),
    CUSTOMER("customer"),
    VET("vet");

    private final String string;

    private PersonType(final String string)
    {
        this.string = string;
    }

    public String getString()
    {
        return string;
    }
}
