package com.govtech.model;

import java.time.LocalDateTime;

public class Document {
    private String documentID;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private LocalDateTime createdAt;
    private String caseID;
    private String documentType;
    private String filePath;
    private String uploaderID;

    public Document() {}

    public Document(String documentID, String attribute1, String attribute2, String attribute3, 
                   LocalDateTime createdAt, String caseID, String documentType, String filePath, String uploaderID) {
        this.documentID = documentID;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
        this.createdAt = createdAt;
        this.caseID = caseID;
        this.documentType = documentType;
        this.filePath = filePath;
        this.uploaderID = uploaderID;
    }

    public String getDocumentID() { return documentID; }
    public void setDocumentID(String documentID) { this.documentID = documentID; }

    public String getAttribute1() { return attribute1; }
    public void setAttribute1(String attribute1) { this.attribute1 = attribute1; }

    public String getAttribute2() { return attribute2; }
    public void setAttribute2(String attribute2) { this.attribute2 = attribute2; }

    public String getAttribute3() { return attribute3; }
    public void setAttribute3(String attribute3) { this.attribute3 = attribute3; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getCaseID() { return caseID; }
    public void setCaseID(String caseID) { this.caseID = caseID; }

    public String getDocumentType() { return documentType; }
    public void setDocumentType(String documentType) { this.documentType = documentType; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getUploaderID() { return uploaderID; }
    public void setUploaderID(String uploaderID) { this.uploaderID = uploaderID; }
}