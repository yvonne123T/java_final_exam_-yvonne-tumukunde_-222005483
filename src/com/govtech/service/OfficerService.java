package com.govtech.service;

import com.govtech.dao.OfficerDAO;
import com.govtech.model.Officer;

import java.util.List;

public class OfficerService {
    private OfficerDAO officerDAO;
    
    public OfficerService() {
        this.officerDAO = new OfficerDAO();
    }
    
    public List<Officer> getAllOfficers() {
        return officerDAO.getAllOfficers();
    }
}