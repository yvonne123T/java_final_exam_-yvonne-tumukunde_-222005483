package com.govtech.service;

import com.govtech.dao.DocumentDAO;
import com.govtech.model.Document;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentService {
    private DocumentDAO documentDAO;
    
    public DocumentService() {
        this.documentDAO = new DocumentDAO();
    }
    
    public boolean createDocument(Document document) {
        return documentDAO.createDocument(document);
    }
    
    public List<Document> getAllDocuments() {
        return documentDAO.getAllDocuments();
    }
    
    public List<Document> getDocumentsByCase(String caseID) {
        return documentDAO.getDocumentsByCase(caseID);
    }
    
    public Document getDocumentById(String documentID) {
        return documentDAO.getDocumentById(documentID);
    }
    
    public boolean updateDocument(Document document) {
        return documentDAO.updateDocument(document);
    }
    
    public boolean deleteDocument(String documentID) {
        return documentDAO.deleteDocument(documentID);
    }
    
    public int getDocumentCount() {
        return documentDAO.getDocumentCount();
    }
    
    public int getDocumentCountByCase(String caseID) {
        return documentDAO.getDocumentCountByCase(caseID);
    }
    
    public List<Document> searchDocumentsByType(String documentType) {
        return documentDAO.getAllDocuments().stream()
                .filter(doc -> doc.getDocumentType().toLowerCase().contains(documentType.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public List<Document> searchDocumentsByUploader(String uploaderID) {
        return documentDAO.getAllDocuments().stream()
                .filter(doc -> doc.getUploaderID().toLowerCase().contains(uploaderID.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public List<Document> searchDocumentsByAttribute(String attribute) {
        return documentDAO.getAllDocuments().stream()
                .filter(doc -> 
                    (doc.getAttribute1() != null && doc.getAttribute1().toLowerCase().contains(attribute.toLowerCase())) ||
                    (doc.getAttribute2() != null && doc.getAttribute2().toLowerCase().contains(attribute.toLowerCase())) ||
                    (doc.getAttribute3() != null && doc.getAttribute3().toLowerCase().contains(attribute.toLowerCase())))
                .collect(Collectors.toList());
    }
    
    public List<String> getAllDocumentTypes() {
        return documentDAO.getAllDocuments().stream()
                .map(Document::getDocumentType)
                .distinct()
                .collect(Collectors.toList());
    }
    
    public String getMostCommonDocumentType() {
        return documentDAO.getAllDocuments().stream()
                .collect(Collectors.groupingBy(Document::getDocumentType, Collectors.counting()))
                .entrySet().stream()
                .max((e1, e2) -> Long.compare(e1.getValue(), e2.getValue()))
                .map(entry -> entry.getKey())
                .orElse("No documents");
    }
    
    public int getDocumentCountByType(String documentType) {
        return (int) documentDAO.getAllDocuments().stream()
                .filter(doc -> doc.getDocumentType().equalsIgnoreCase(documentType))
                .count();
    }
    
    public int getDocumentsThisMonthCount() {
        // Simplified implementation - in real scenario, you'd query by date
        return documentDAO.getAllDocuments().size() / 3; // Mock data
    }
    
    public boolean validateDocumentData(Document document) {
        if (document == null) {
            return false;
        }
        if (document.getDocumentType() == null || document.getDocumentType().trim().isEmpty()) {
            return false;
        }
        if (document.getCaseID() == null || document.getCaseID().trim().isEmpty()) {
            return false;
        }
        return true;
    }
}