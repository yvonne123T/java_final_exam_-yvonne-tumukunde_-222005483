package com.govtech.model;

import java.time.LocalDateTime;

public class Officer {
    private String officerID;
    private String name;
    private String identifier;
    private String status;
    private String location;
    private String contact;
    private LocalDateTime assignedSince;
    private String departmentID;

    public Officer() {}

    public Officer(String officerID, String name, String identifier, String status, String location, 
                  String contact, LocalDateTime assignedSince, String departmentID) {
        this.officerID = officerID;
        this.name = name;
        this.identifier = identifier;
        this.status = status;
        this.location = location;
        this.contact = contact;
        this.assignedSince = assignedSince;
        this.departmentID = departmentID;
    }

    public String getOfficerID() { return officerID; }
    public void setOfficerID(String officerID) { this.officerID = officerID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIdentifier() { return identifier; }
    public void setIdentifier(String identifier) { this.identifier = identifier; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public LocalDateTime getAssignedSince() { return assignedSince; }
    public void setAssignedSince(LocalDateTime assignedSince) { this.assignedSince = assignedSince; }

    public String getDepartmentID() { return departmentID; }
    public void setDepartmentID(String departmentID) { this.departmentID = departmentID; }
}