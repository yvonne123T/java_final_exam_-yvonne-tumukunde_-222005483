package com.govtech.controller;

import com.govtech.model.Officer;
import com.govtech.service.OfficerService;

import java.util.List;

public class OfficerController {
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String YELLOW = "\u001B[33m";
    
    private OfficerService officerService;
    
    public OfficerController() {
        this.officerService = new OfficerService();
    }
    
    public void displayAllOfficers() {
        System.out.println();
        System.out.println(BLUE + "👮 GOVERNMENT OFFICERS" + RESET);
        System.out.println();
        
        List<Officer> officers = officerService.getAllOfficers();
        
        System.out.println(GREEN + "Available Officers (" + officers.size() + "):" + RESET);
        System.out.println();
        
        for (Officer officer : officers) {
            String statusIcon = getStatusIcon(officer.getStatus());
            String statusColor = getStatusColor(officer.getStatus());
            
            System.out.println(PURPLE + "┌─ " + officer.getName() + " ─┐" + RESET);
            System.out.println(BLUE + "│ 🆔 Officer ID: " + officer.getOfficerID() + RESET);
            System.out.println(BLUE + "│ 🏷️  Identifier: " + officer.getIdentifier() + RESET);
            System.out.println(BLUE + "│ " + statusColor + statusIcon + " Status: " + officer.getStatus() + RESET);
            System.out.println(BLUE + "│ 📍 Location: " + officer.getLocation() + RESET);
            System.out.println(BLUE + "│ 📞 Contact: " + officer.getContact() + RESET);
            System.out.println(BLUE + "│ 📅 Assigned Since: " + officer.getAssignedSince().toLocalDate() + RESET);
            System.out.println(PURPLE + "└" + "─".repeat(30) + "┘" + RESET);
            System.out.println();
        }
        
        pressEnterToContinue();
    }
    
    public int getOfficerCount() {
        return officerService.getAllOfficers().size();
    }
    
    private String getStatusColor(String status) {
        switch (status.toLowerCase()) {
            case "active": return GREEN;
            case "on leave": return YELLOW;
            case "training": return BLUE;
            default: return RESET;
        }
    }
    
    private String getStatusIcon(String status) {
        switch (status.toLowerCase()) {
            case "active": return "🟢";
            case "on leave": return "🟡";
            case "training": return "🔵";
            default: return "⚪";
        }
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