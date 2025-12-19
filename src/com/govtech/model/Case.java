package com.govtech.model;

import java.time.LocalDateTime;

public class Case {
    private String caseID;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private LocalDateTime createdAt;
    private String officerID;
    private String serviceRequestID;
    private String caseType;
    private String priority;

    public Case() {}

    public Case(String caseID, String attribute1, String attribute2, String attribute3, 
                LocalDateTime createdAt, String officerID, String serviceRequestID, String caseType, String priority) {
        this.caseID = caseID;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
        this.createdAt = createdAt;
        this.officerID = officerID;
        this.serviceRequestID = serviceRequestID;
        this.caseType = caseType;
        this.priority = priority;
    }

    public String getCaseID() { return caseID; }
    public void setCaseID(String caseID) { this.caseID = caseID; }

    public String getAttribute1() { return attribute1; }
    public void setAttribute1(String attribute1) { this.attribute1 = attribute1; }

    public String getAttribute2() { return attribute2; }
    public void setAttribute2(String attribute2) { this.attribute2 = attribute2; }

    public String getAttribute3() { return attribute3; }
    public void setAttribute3(String attribute3) { this.attribute3 = attribute3; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getOfficerID() { return officerID; }
    public void setOfficerID(String officerID) { this.officerID = officerID; }

    public String getServiceRequestID() { return serviceRequestID; }
    public void setServiceRequestID(String serviceRequestID) { this.serviceRequestID = serviceRequestID; }

    public String getCaseType() { return caseType; }
    public void setCaseType(String caseType) { this.caseType = caseType; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
}