package com.govtech.dao;

import com.govtech.model.Document;
import com.govtech.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentDAO {
    
    public boolean createDocument(Document document) {
        String sql = "INSERT INTO Document (DocumentID, Attribute1, Attribute2, Attribute3, CaseID, DocumentType, FilePath, UploaderID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, document.getDocumentID());
            pstmt.setString(2, document.getAttribute1());
            pstmt.setString(3, document.getAttribute2());
            pstmt.setString(4, document.getAttribute3());
            pstmt.setString(5, document.getCaseID());
            pstmt.setString(6, document.getDocumentType());
            pstmt.setString(7, document.getFilePath());
            pstmt.setString(8, document.getUploaderID());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error creating document: " + e.getMessage());
            return false;
        }
    }
    
    public List<Document> getAllDocuments() {
        List<Document> documents = new ArrayList<>();
        String sql = "SELECT * FROM Document ORDER BY CreatedAt DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Document document = new Document();
                document.setDocumentID(rs.getString("DocumentID"));
                document.setAttribute1(rs.getString("Attribute1"));
                document.setAttribute2(rs.getString("Attribute2"));
                document.setAttribute3(rs.getString("Attribute3"));
                document.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                document.setCaseID(rs.getString("CaseID"));
                document.setDocumentType(rs.getString("DocumentType"));
                document.setFilePath(rs.getString("FilePath"));
                document.setUploaderID(rs.getString("UploaderID"));
                documents.add(document);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving documents: " + e.getMessage());
        }
        return documents;
    }
    
    public List<Document> getDocumentsByCase(String caseID) {
        List<Document> documents = new ArrayList<>();
        String sql = "SELECT * FROM Document WHERE CaseID = ? ORDER BY CreatedAt DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, caseID);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Document document = new Document();
                document.setDocumentID(rs.getString("DocumentID"));
                document.setAttribute1(rs.getString("Attribute1"));
                document.setAttribute2(rs.getString("Attribute2"));
                document.setAttribute3(rs.getString("Attribute3"));
                document.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                document.setCaseID(rs.getString("CaseID"));
                document.setDocumentType(rs.getString("DocumentType"));
                document.setFilePath(rs.getString("FilePath"));
                document.setUploaderID(rs.getString("UploaderID"));
                documents.add(document);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving documents by case: " + e.getMessage());
        }
        return documents;
    }
    
    public Document getDocumentById(String documentID) {
        String sql = "SELECT * FROM Document WHERE DocumentID = ?";
        Document document = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, documentID);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                document = new Document();
                document.setDocumentID(rs.getString("DocumentID"));
                document.setAttribute1(rs.getString("Attribute1"));
                document.setAttribute2(rs.getString("Attribute2"));
                document.setAttribute3(rs.getString("Attribute3"));
                document.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                document.setCaseID(rs.getString("CaseID"));
                document.setDocumentType(rs.getString("DocumentType"));
                document.setFilePath(rs.getString("FilePath"));
                document.setUploaderID(rs.getString("UploaderID"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving document by ID: " + e.getMessage());
        }
        return document;
    }
    
    public boolean updateDocument(Document document) {
        String sql = "UPDATE Document SET Attribute1 = ?, Attribute2 = ?, Attribute3 = ?, DocumentType = ?, FilePath = ? WHERE DocumentID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, document.getAttribute1());
            pstmt.setString(2, document.getAttribute2());
            pstmt.setString(3, document.getAttribute3());
            pstmt.setString(4, document.getDocumentType());
            pstmt.setString(5, document.getFilePath());
            pstmt.setString(6, document.getDocumentID());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating document: " + e.getMessage());
            return false;
        }
    }
    
    public boolean deleteDocument(String documentID) {
        String sql = "DELETE FROM Document WHERE DocumentID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, documentID);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting document: " + e.getMessage());
            return false;
        }
    }
    
    public int getDocumentCount() {
        String sql = "SELECT COUNT(*) as count FROM Document";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            System.err.println("Error counting documents: " + e.getMessage());
        }
        return 0;
    }
    
    public int getDocumentCountByCase(String caseID) {
        String sql = "SELECT COUNT(*) as count FROM Document WHERE CaseID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, caseID);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            System.err.println("Error counting documents by case: " + e.getMessage());
        }
        return 0;
    }
}