package com.govtech.controller;

import com.govtech.model.Citizen;
import com.govtech.service.AuthService;
import com.govtech.util.PasswordHasher;

import java.time.LocalDateTime;
import java.util.Scanner;

public class AuthController {
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";
    
    private AuthService authService;
    
    public AuthController() {
        this.authService = new AuthService();
    }
    
    public Citizen login(String email, String password) {
        try {
            // Hash the password before authentication
            String hashedPassword = PasswordHasher.hashPassword(password);
            Citizen citizen = authService.authenticate(email, hashedPassword);
            
            if (citizen != null) {
                System.out.println(GREEN + "🔐 Authentication successful!" + RESET);
                return citizen;
            } else {
                System.out.println(RED + "❌ Invalid credentials!" + RESET);
                return null;
            }
        } catch (Exception e) {
            System.out.println(RED + "❌ Login error: " + e.getMessage() + RESET);
            return null;
        }
    }
    
    public Citizen registerUser(Scanner scanner) {
        try {
            System.out.print(CYAN + "👤 Full Name: " + RESET);
            String fullName = scanner.nextLine();
            
            System.out.print(CYAN + "📧 Email: " + RESET);
            String email = scanner.nextLine();
            
            System.out.print(CYAN + "🔒 Password: " + RESET);
            String password = scanner.nextLine();
            
            System.out.print(CYAN + "📞 Phone Number: " + RESET);
            String phone = scanner.nextLine();
            
            // Validate inputs
            if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                System.out.println(RED + "❌ All fields are required!" + RESET);
                return null;
            }
            
            // Generate Citizen ID
            String citizenID = "CIT" + System.currentTimeMillis();
            
            Citizen newCitizen = new Citizen(
                citizenID,
                "Active",
                "General",
                "Verified",
                LocalDateTime.now(),
                email,
                password, // Will be hashed in service
                fullName,
                phone
            );
            
            boolean success = authService.register(newCitizen);
            if (success) {
                System.out.println(GREEN + "✅ Registration completed successfully!" + RESET);
                return newCitizen;
            } else {
                System.out.println(RED + "❌ Registration failed! Email might already exist." + RESET);
                return null;
            }
        } catch (Exception e) {
            System.out.println(RED + "❌ Registration error: " + e.getMessage() + RESET);
            return null;
        }
    }
}