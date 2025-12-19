package com.govtech.service;

import com.govtech.dao.CitizenDAO;
import com.govtech.model.Citizen;
import com.govtech.util.PasswordHasher;

public class AuthService {
    private CitizenDAO citizenDAO;
    
    public AuthService() {
        this.citizenDAO = new CitizenDAO();
    }
    
    public Citizen authenticate(String email, String hashedPassword) {
        return citizenDAO.authenticate(email, hashedPassword);
    }
    
    public boolean register(Citizen citizen) {
        // Hash password before storing
        String hashedPassword = PasswordHasher.hashPassword(citizen.getPassword());
        citizen.setPassword(hashedPassword);
        return citizenDAO.createCitizen(citizen);
    }
}