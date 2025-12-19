package com.govtech.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DatabaseConnection {
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";
    
    private static Connection connection = null;
    
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Properties props = new Properties();
                InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("database.properties");
                
                if (input == null) {
                    System.out.println(RED + "❌ Unable to find database.properties" + RESET);
                    return null;
                }
                
                props.load(input);
                
                String url = props.getProperty("db.url");
                String user = props.getProperty("db.username");
                String password = props.getProperty("db.password");
                
                connection = DriverManager.getConnection(url, user, password);
                System.out.println(GREEN + "✅ Database connected successfully!" + RESET);
                
            } catch (Exception e) {
                System.out.println(RED + "❌ Database connection failed: " + e.getMessage() + RESET);
                e.printStackTrace();
            }
        }
        return connection;
    }
    
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println(GREEN + "✅ Database connection closed!" + RESET);
            } catch (SQLException e) {
                System.out.println(RED + "❌ Error closing connection: " + e.getMessage() + RESET);
            }
        }
    }

	public static void getConnection1() {
		// TODO Auto-generated method stub
		
	}
}