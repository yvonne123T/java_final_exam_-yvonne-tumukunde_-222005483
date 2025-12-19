package com.govtech.model;

import java.time.LocalDateTime;

public class ServiceRequest {
    private String serviceRequestID;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private LocalDateTime createdAt;
    private String citizenID;
    private String requestType;
    private String description;
    private String status;

    public ServiceRequest() {}

    public ServiceRequest(String serviceRequestID, String attribute1, String attribute2, String attribute3, 
                         LocalDateTime createdAt, String citizenID, String requestType, String description, String status) {
        this.serviceRequestID = serviceRequestID;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
        this.createdAt = createdAt;
        this.citizenID = citizenID;
        this.requestType = requestType;
        this.description = description;
        this.status = status;
    }

    public String getServiceRequestID() { return serviceRequestID; }
    public void setServiceRequestID(String serviceRequestID) { this.serviceRequestID = serviceRequestID; }

    public String getAttribute1() { return attribute1; }
    public void setAttribute1(String attribute1) { this.attribute1 = attribute1; }

    public String getAttribute2() { return attribute2; }
    public void setAttribute2(String attribute2) { this.attribute2 = attribute2; }

    public String getAttribute3() { return attribute3; }
    public void setAttribute3(String attribute3) { this.attribute3 = attribute3; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getCitizenID() { return citizenID; }
    public void setCitizenID(String citizenID) { this.citizenID = citizenID; }

    public String getRequestType() { return requestType; }
    public void setRequestType(String requestType) { this.requestType = requestType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}