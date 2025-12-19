package com.govtech.service;

import com.govtech.dao.CitizenDAO;
import com.govtech.model.Citizen;

import java.util.List;

public class CitizenService {
    private CitizenDAO citizenDAO;
    
    public CitizenService() {
        this.citizenDAO = new CitizenDAO();
    }
    
    public Citizen getCitizenById(String citizenID) {
        return citizenDAO.getCitizenById(citizenID);
    }
    
    public List<Citizen> getAllCitizens() {
        return citizenDAO.getAllCitizens();
    }
}