package com.govtech.service;

import com.govtech.dao.CaseDAO;
import com.govtech.model.Case;

import java.util.List;

public class CaseService {
    private CaseDAO caseDAO;
    
    public CaseService() {
        this.caseDAO = new CaseDAO();
    }
    
    public List<Case> getAllCases() {
        return caseDAO.getAllCases();
    }
}