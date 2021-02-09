package com.example.demo.model.enums;

public enum AppointmentStatus {
    ACTIVE("active"),
    CANCELLED("cancelled"),
    POSTPONED("postponed");

    private final String appointmentStatus;

    private AppointmentStatus(final String appointmentStatus)
    {
        this.appointmentStatus = appointmentStatus;
    }

    public String getStatus()
    {
        return appointmentStatus;
    }
}
