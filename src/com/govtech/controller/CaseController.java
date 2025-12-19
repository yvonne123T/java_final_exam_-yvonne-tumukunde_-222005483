package com.govtech.controller;

import com.govtech.model.Case;
import com.govtech.service.CaseService;

import java.util.List;

public class CaseController {
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    
    private CaseService caseService;
    
    public CaseController() {
        this.caseService = new CaseService();
    }
    
    public void displayAllCases() {
        System.out.println();
        System.out.println(CYAN + "📁 ACTIVE CASES" + RESET);
        System.out.println();
        
        List<Case> cases = caseService.getAllCases();
        
        if (cases.isEmpty()) {
            System.out.println(YELLOW + "📋 No active cases found." + RESET);
        } else {
            System.out.println(GREEN + "Active Cases (" + cases.size() + "):" + RESET);
            System.out.println();
            
            for (Case caseObj : cases) {
                String priorityColor = getPriorityColor(caseObj.getPriority());
                
                System.out.println(CYAN + "╔═ Case: " + caseObj.getCaseID() + " ═══════════════════════╗" + RESET);
                System.out.println(YELLOW + "║ 🏷️  Type: " + caseObj.getCaseType() + RESET);
                System.out.println(YELLOW + "║ " + priorityColor + "🚨 Priority: " + caseObj.getPriority() + RESET);
                System.out.println(YELLOW + "║ 📅 Created: " + caseObj.getCreatedAt().toLocalDate() + RESET);
                System.out.println(YELLOW + "║ 📊 Status: " + caseObj.getAttribute1() + RESET);
                System.out.println(CYAN + "╚" + "═".repeat(45) + "╝" + RESET);
                System.out.println();
            }
        }
        
        pressEnterToContinue();
    }
    
    public int getCaseCount() {
        return caseService.getAllCases().size();
    }
    
    private String getPriorityColor(String priority) {
        switch (priority.toLowerCase()) {
            case "high": return RED;
            case "medium": return YELLOW;
            case "low": return GREEN;
            default: return RESET;
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