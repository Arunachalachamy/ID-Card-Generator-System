package com.starprotect.vehicleinsurance.bean;

import java.time.LocalDate;

public class UnderWriter {
    private int underwriterId;
    private String name;
    private LocalDate dob;
    private LocalDate joiningDate;
    private String password;

    // Default constructor
    public UnderWriter() {}

    // Parameterized constructor
    public UnderWriter(int underwriterId, String name, LocalDate dob, LocalDate joiningDate, String password) {
        this.underwriterId = underwriterId;
        this.name = name;
        this.dob = dob;
        this.joiningDate = joiningDate;
        this.password = password;
    }

    // Getters and Setters
    public int getUnderwriterId() {
        return underwriterId;
    }

    public void setUnderwriterId(int underwriterId) {
        this.underwriterId = underwriterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UnderWriter{" +
                "underwriterId=" + underwriterId +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", joiningDate=" + joiningDate +
                ", password='" + password + '\'' +
                '}';
    }
}