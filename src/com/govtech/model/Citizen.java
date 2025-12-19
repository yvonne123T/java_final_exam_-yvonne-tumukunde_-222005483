package com.govtech.model;

import java.time.LocalDateTime;

public class Citizen {
    private String citizenID;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private LocalDateTime createdAt;
    private String email;
    private String password;
    private String fullName;
    private String phoneNumber;

    public Citizen() {}

    public Citizen(String citizenID, String attribute1, String attribute2, String attribute3, 
                  LocalDateTime createdAt, String email, String password, String fullName, String phoneNumber) {
        this.citizenID = citizenID;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
        this.createdAt = createdAt;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public String getCitizenID() { return citizenID; }
    public void setCitizenID(String citizenID) { this.citizenID = citizenID; }

    public String getAttribute1() { return attribute1; }
    public void setAttribute1(String attribute1) { this.attribute1 = attribute1; }

    public String getAttribute2() { return attribute2; }
    public void setAttribute2(String attribute2) { this.attribute2 = attribute2; }

    public String getAttribute3() { return attribute3; }
    public void setAttribute3(String attribute3) { this.attribute3 = attribute3; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}