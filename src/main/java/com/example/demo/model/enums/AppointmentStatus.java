package com.example.demo.model.enums;

public enum AppointmentStatus {
    ACTIVE("active"),
    CANCELLED("cancelled"),
    POSTPONED("postponed");

    private final String string;

    private AppointmentStatus(final String string)
    {
        this.string = string;
    }

    public String getStatus()
    {
        return string;
    }
}
