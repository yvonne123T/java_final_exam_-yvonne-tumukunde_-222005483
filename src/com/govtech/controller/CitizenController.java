package com.govtech.controller;

import com.govtech.model.Citizen;
import com.govtech.service.CitizenService;

public class CitizenController {
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String BLUE = "\u001B[34m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    
    private CitizenService citizenService;
    
    public CitizenController() {
        this.citizenService = new CitizenService();
    }
    
    public void displayCitizenProfile(String citizenID) {
        System.out.println();
        System.out.println(CYAN + "📋 PROFILE INFORMATION" + RESET);
        System.out.println();
        
        Citizen citizen = citizenService.getCitizenById(citizenID);
        if (citizen != null) {
            System.out.println(BLUE + "╔══════════════════════════════════════╗" + RESET);
            System.out.println(BLUE + "║              👤 PROFILE              ║" + RESET);
            System.out.println(BLUE + "╠══════════════════════════════════════╣" + RESET);
            System.out.println(BLUE + "║ 🆔 Citizen ID: " + citizen.getCitizenID() + RESET);
            System.out.println(BLUE + "║ 👤 Full Name: " + citizen.getFullName() + RESET);
            System.out.println(BLUE + "║ 📧 Email: " + citizen.getEmail() + RESET);
            System.out.println(BLUE + "║ 📞 Phone: " + citizen.getPhoneNumber() + RESET);
            System.out.println(BLUE + "║ 🏷️  Status: " + citizen.getAttribute3() + RESET);
            System.out.println(BLUE + "║ 📅 Member Since: " + citizen.getCreatedAt().toLocalDate() + RESET);
            System.out.println(BLUE + "╚══════════════════════════════════════╝" + RESET);
        } else {
            System.out.println(YELLOW + "❓ Profile not found!" + RESET);
        }
        
        pressEnterToContinue();
    }
    
    public int getCitizenCount() {
        return citizenService.getAllCitizens().size();
    }
    
    private void pressEnterToContinue() {
        System.out.println();
        System.out.print(BLUE + "⏎ Press Enter to continue..." + RESET);
        try {
            System.in.read();
        } catch (Exception e) {
            // Ignore
        }
    }
}