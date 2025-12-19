package com.govtech.dao;

import com.govtech.model.Case;
import com.govtech.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CaseDAO {
    
    public List<Case> getAllCases() {
        List<Case> cases = new ArrayList<>();
        String sql = "SELECT * FROM Case ORDER BY CreatedAt DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Case caseObj = new Case();
                caseObj.setCaseID(rs.getString("CaseID"));
                caseObj.setAttribute1(rs.getString("Attribute1"));
                caseObj.setAttribute2(rs.getString("Attribute2"));
                caseObj.setAttribute3(rs.getString("Attribute3"));
                caseObj.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                caseObj.setOfficerID(rs.getString("OfficerID"));
                caseObj.setServiceRequestID(rs.getString("ServiceRequestID"));
                caseObj.setCaseType(rs.getString("CaseType"));
                caseObj.setPriority(rs.getString("Priority"));
                cases.add(caseObj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cases;
    }
}