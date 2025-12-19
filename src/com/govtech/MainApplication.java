package com.govtech;

import com.govtech.dao.*;
import com.govtech.model.*;
import com.govtech.service.AuthService;
import com.govtech.util.DatabaseConnection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class MainApplication {
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";
    private static final String BOLD = "\u001B[1m";
    
    private static Scanner scanner = new Scanner(System.in);
    private static AuthService authService = new AuthService();
    private static Citizen currentUser = null;

    public static void main(String[] args) {
        // Test database connection
        DatabaseConnection.getConnection();
        
        displayWelcomeScreen();
        mainMenu();
    }
    
    private static void displayWelcomeScreen() {
        clearScreen();
        System.out.println(CYAN + "╔══════════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(CYAN + "║" + BOLD + YELLOW + "                  GOVTECH ASSISTANT SYSTEM                    " + RESET + CYAN + "║" + RESET);
        System.out.println(CYAN + "║" + GREEN + "            Smart Government Services Portal              " + RESET + CYAN + "║" + RESET);
        System.out.println(CYAN + "╚══════════════════════════════════════════════════════════════╝" + RESET);
        System.out.println();
        System.out.println(BLUE + "🚀 Initializing system..." + RESET);
        System.out.println(GREEN + "✓ Database connection established" + RESET);
        System.out.println(GREEN + "✓ Services initialized" + RESET);
        System.out.println();
        pressEnterToContinue();
    }
    
    private static void mainMenu() {
        while (true) {
            clearScreen();
            displayHeader("MAIN MENU");
            
            if (currentUser == null) {
                System.out.println(YELLOW + "🔹 1. " + BOLD + "Login" + RESET);
                System.out.println(YELLOW + "🔹 2. " + BOLD + "Register" + RESET);
                System.out.println(RED + "🔹 3. " + BOLD + "Exit" + RESET);
            } else {
                System.out.println(GREEN + "👤 Welcome, " + BOLD + currentUser.getFullName() + RESET + GREEN + "!" + RESET);
                System.out.println();
                System.out.println(CYAN + "📊 " + BOLD + "DASHBOARD OPTIONS:" + RESET);
                System.out.println(YELLOW + "🔹 1. View My Service Requests" + RESET);
                System.out.println(YELLOW + "🔹 2. Create New Service Request" + RESET);
                System.out.println(YELLOW + "🔹 3. View Departments" + RESET);
                System.out.println(YELLOW + "🔹 4. View Officers" + RESET);
                System.out.println(YELLOW + "🔹 5. View Cases" + RESET);
                System.out.println(YELLOW + "🔹 6. System Statistics" + RESET);
                System.out.println(RED + "🔹 7. Logout" + RESET);
            }
            
            System.out.println();
            System.out.print(BLUE + "💡 Enter your choice (1-7): " + RESET);
            
            int choice = getIntInput();
            
            if (currentUser == null) {
                switch (choice) {
                    case 1 -> login();
                    case 2 -> register();
                    case 3 -> exitSystem();
                    default -> showError("Invalid choice! Please try again.");
                }
            } else {
                switch (choice) {
                    case 1 -> viewMyServiceRequests();
                    case 2 -> createServiceRequest();
                    case 3 -> viewDepartments();
                    case 4 -> viewOfficers();
                    case 5 -> viewCases();
                    case 6 -> showStatistics();
                    case 7 -> logout();
                    default -> showError("Invalid choice! Please try again.");
                }
            }
        }
    }
    
    private static void login() {
        clearScreen();
        displayHeader("USER LOGIN");
        
        System.out.print(CYAN + "📧 Email: " + RESET);
        String email = scanner.nextLine();
        
        System.out.print(CYAN + "🔒 Password: " + RESET);
        String password = scanner.nextLine();
        
        currentUser = authService.login(email, password);
        
        if (currentUser != null) {
            showSuccess("Login successful! Welcome back, " + currentUser.getFullName() + "!");
            pressEnterToContinue();
        } else {
            showError("Login failed! Invalid email or password.");
            pressEnterToContinue();
        }
    }
    
    private static void register() {
        clearScreen();
        displayHeader("NEW USER REGISTRATION");
        
        try {
            System.out.print(CYAN + "👤 Full Name: " + RESET);
            String fullName = scanner.nextLine();
            
            System.out.print(CYAN + "📧 Email: " + RESET);
            String email = scanner.nextLine();
            
            System.out.print(CYAN + "🔒 Password: " + RESET);
            String password = scanner.nextLine();
            
            System.out.print(CYAN + "📞 Phone Number: " + RESET);
            String phone = scanner.nextLine();
            
            // Generate Citizen ID
            String citizenID = "CIT" + String.format("%03d", (int)(Math.random() * 1000));
            
            Citizen newCitizen = new Citizen(
                citizenID,
                "New User",
                "General",
                "Pending Verification",
                LocalDateTime.now(),
                email,
                password,
                fullName,
                phone
            );
            
            if (authService.register(newCitizen)) {
                showSuccess("Registration successful! Your Citizen ID: " + citizenID);
            } else {
                showError("Registration failed! Please try again.");
            }
        } catch (Exception e) {
            showError("Error during registration: " + e.getMessage());
        }
        
        pressEnterToContinue();
    }
    
    private static void viewMyServiceRequests() {
        clearScreen();
        displayHeader("MY SERVICE REQUESTS");
        
        if (currentUser == null) {
            showError("Please login first!");
            return;
        }
        
        ServiceRequestDAO srDAO = new ServiceRequestDAO();
        List<ServiceRequest> requests = srDAO.getServiceRequestsByCitizen(currentUser.getCitizenID());
        
        if (requests.isEmpty()) {
            System.out.println(YELLOW + "📭 No service requests found." + RESET);
        } else {
            System.out.println(BOLD + GREEN + "Found " + requests.size() + " service request(s):" + RESET);
            System.out.println();
            
            for (int i = 0; i < requests.size(); i++) {
                ServiceRequest req = requests.get(i);
                String statusColor = getStatusColor(req.getStatus());
                
                System.out.println(PURPLE + "┌─ Request #" + (i + 1) + " " + "─".repeat(50) + "┐" + RESET);
                System.out.println(BLUE + "│ 📋 ID: " + req.getServiceRequestID() + RESET);
                System.out.println(BLUE + "│ 🏷️  Type: " + req.getRequestType() + RESET);
                System.out.println(BLUE + "│ 📝 Description: " + req.getDescription() + RESET);
                System.out.println(BLUE + "│ 📅 Created: " + req.getCreatedAt().toLocalDate() + RESET);
                System.out.println(BLUE + "│ " + statusColor + "🔄 Status: " + req.getStatus() + RESET);
                System.out.println(PURPLE + "└" + "─".repeat(58) + "┘" + RESET);
                System.out.println();
            }
        }
        
        pressEnterToContinue();
    }
    
    private static void createServiceRequest() {
        clearScreen();
        displayHeader("CREATE SERVICE REQUEST");
        
        if (currentUser == null) {
            showError("Please login first!");
            return;
        }
        
        try {
            System.out.print(CYAN + "🏷️  Request Type (e.g., Road Repair, Health Inspection): " + RESET);
            String type = scanner.nextLine();
            
            System.out.print(CYAN + "📝 Description: " + RESET);
            String description = scanner.nextLine();
            
            System.out.print(CYAN + "🚨 Priority (High/Medium/Low): " + RESET);
            String priority = scanner.nextLine();
            
            String requestID = "SR" + System.currentTimeMillis();
            
            ServiceRequest newRequest = new ServiceRequest(
                requestID,
                priority,
                "New",
                "Active",
                LocalDateTime.now(),
                currentUser.getCitizenID(),
                type,
                description,
                "Pending"
            );
            
            ServiceRequestDAO srDAO = new ServiceRequestDAO();
            if (srDAO.createServiceRequest(newRequest)) {
                showSuccess("Service request created successfully! ID: " + requestID);
            } else {
                showError("Failed to create service request.");
            }
        } catch (Exception e) {
            showError("Error creating request: " + e.getMessage());
        }
        
        pressEnterToContinue();
    }
    
    private static void viewDepartments() {
        clearScreen();
        displayHeader("GOVERNMENT DEPARTMENTS");
        
        DepartmentDAO deptDAO = new DepartmentDAO();
        List<Department> departments = deptDAO.getAllDepartments();
        
        System.out.println(BOLD + GREEN + "Available Departments (" + departments.size() + "):" + RESET);
        System.out.println();
        
        for (Department dept : departments) {
            System.out.println(CYAN + "╔════ " + dept.getName() + " ═════" + RESET);
            System.out.println(YELLOW + "║ 🆔 ID: " + dept.getDepartmentID() + RESET);
            System.out.println(YELLOW + "║ 🏢 Address: " + dept.getAddress() + RESET);
            System.out.println(YELLOW + "║ 👨‍💼 Manager: " + dept.getManager() + RESET);
            System.out.println(YELLOW + "║ 📞 Contact: " + dept.getContact() + RESET);
            System.out.println(YELLOW + "║ 🪑 Capacity: " + dept.getCapacity() + " officers" + RESET);
            System.out.println(CYAN + "╚" + "═".repeat(40) + RESET);
            System.out.println();
        }
        
        pressEnterToContinue();
    }
    
    private static void viewOfficers() {
        clearScreen();
        displayHeader("GOVERNMENT OFFICERS");
        
        OfficerDAO officerDAO = new OfficerDAO();
        List<Officer> officers = officerDAO.getAllOfficers();
        
        System.out.println(BOLD + GREEN + "Available Officers (" + officers.size() + "):" + RESET);
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
    
    private static void viewCases() {
        clearScreen();
        displayHeader("ACTIVE CASES");
        
        CaseDAO caseDAO = new CaseDAO();
        List<Case> cases = caseDAO.getAllCases();
        
        if (cases.isEmpty()) {
            System.out.println(YELLOW + "📋 No active cases found." + RESET);
        } else {
            System.out.println(BOLD + GREEN + "Active Cases (" + cases.size() + "):" + RESET);
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
    
    private static void showStatistics() {
        clearScreen();
        displayHeader("SYSTEM STATISTICS");
        
        CitizenDAO citizenDAO = new CitizenDAO();
        ServiceRequestDAO srDAO = new ServiceRequestDAO();
        DepartmentDAO deptDAO = new DepartmentDAO();
        OfficerDAO officerDAO = new OfficerDAO();
        CaseDAO caseDAO = new CaseDAO();
        
        List<Citizen> citizens = citizenDAO.getAllCitizens();
        List<ServiceRequest> requests = srDAO.getAllServiceRequests();
        List<Department> departments = deptDAO.getAllDepartments();
        List<Officer> officers = officerDAO.getAllOfficers();
        List<Case> cases = caseDAO.getAllCases();
        
        System.out.println(BOLD + CYAN + "📊 REAL-TIME SYSTEM OVERVIEW" + RESET);
        System.out.println();
        
        // Statistics in boxes
        System.out.println(GREEN + "╔══════════════════════════════════════╗" + RESET);
        System.out.println(GREEN + "║           📈 SYSTEM STATS            ║" + RESET);
        System.out.println(GREEN + "╠══════════════════════════════════════╣" + RESET);
        System.out.println(GREEN + "║ 👥 Citizens: " + String.format("%-20d", citizens.size()) + " ║" + RESET);
        System.out.println(GREEN + "║ 📋 Service Requests: " + String.format("%-13d", requests.size()) + " ║" + RESET);
        System.out.println(GREEN + "║ 🏢 Departments: " + String.format("%-19d", departments.size()) + " ║" + RESET);
        System.out.println(GREEN + "║ 👮 Officers: " + String.format("%-22d", officers.size()) + " ║" + RESET);
        System.out.println(GREEN + "║ 📁 Active Cases: " + String.format("%-18d", cases.size()) + " ║" + RESET);
        System.out.println(GREEN + "╚══════════════════════════════════════╝" + RESET);
        System.out.println();
        
        // Service Request Status Breakdown
        long pending = requests.stream().filter(r -> "Pending".equals(r.getStatus())).count();
        long inProgress = requests.stream().filter(r -> "In Progress".equals(r.getStatus())).count();
        long completed = requests.stream().filter(r -> "Completed".equals(r.getStatus())).count();
        
        System.out.println(BOLD + YELLOW + "📋 SERVICE REQUEST STATUS:" + RESET);
        System.out.println("🟡 Pending: " + pending + " requests");
        System.out.println("🔵 In Progress: " + inProgress + " requests");
        System.out.println("🟢 Completed: " + completed + " requests");
        
        pressEnterToContinue();
    }
    
    private static void logout() {
        currentUser = null;
        showSuccess("Logged out successfully!");
        pressEnterToContinue();
    }
    
    private static void exitSystem() {
        clearScreen();
        System.out.println(CYAN + "╔══════════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(CYAN + "║" + BOLD + YELLOW + "                     THANK YOU FOR USING                       " + RESET + CYAN + "║" + RESET);
        System.out.println(CYAN + "║" + GREEN + "                   GOVTECH ASSISTANT SYSTEM                  " + RESET + CYAN + "║" + RESET);
        System.out.println(CYAN + "╚══════════════════════════════════════════════════════════════╝" + RESET);
        System.out.println();
        System.out.println(BLUE + "👋 Goodbye! Have a great day! 🌟" + RESET);
        System.exit(0);
    }
    
    // Utility methods
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    private static void displayHeader(String title) {
        int width = 60;
        String border = "═".repeat(width);
        System.out.println(CYAN + "╔" + border + "╗" + RESET);
        System.out.println(CYAN + "║" + BOLD + YELLOW + centerString(title, width) + RESET + CYAN + "║" + RESET);
        System.out.println(CYAN + "╚" + border + "╝" + RESET);
        System.out.println();
    }
    
    private static String centerString(String s, int width) {
        int padSize = width - s.length();
        int padStart = s.length() + padSize / 2;
        return String.format("%" + padStart + "s", s).replace(' ', ' ');
    }
    
    private static void showSuccess(String message) {
        System.out.println();
        System.out.println(GREEN + "✅ " + message + RESET);
        System.out.println();
    }
    
    private static void showError(String message) {
        System.out.println();
        System.out.println(RED + "❌ " + message + RESET);
        System.out.println();
    }
    
    private static void pressEnterToContinue() {
        System.out.println();
        System.out.print(BLUE + "⏎ Press Enter to continue..." + RESET);
        scanner.nextLine();
    }
    
    private static int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static String getStatusColor(String status) {
        return switch (status.toLowerCase()) {
            case "active", "completed", "approved" -> GREEN;
            case "in progress", "under review" -> YELLOW;
            case "pending" -> BLUE;
            case "on leave", "training" -> PURPLE;
            default -> RED;
        };
    }
    
    private static String getPriorityColor(String priority) {
        return switch (priority.toLowerCase()) {
            case "high", "critical" -> RED;
            case "medium" -> YELLOW;
            case "low" -> GREEN;
            default -> BLUE;
        };
    }
    
    private static String getStatusIcon(String status) {
        return switch (status.toLowerCase()) {
            case "active" -> "🟢";
            case "on leave" -> "🟡";
            case "training" -> "🔵";
            default -> "⚪";
        };
    }
}