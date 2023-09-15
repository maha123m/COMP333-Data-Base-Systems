package com.example.maha;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;



public class Connecter {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/drivingschool";
    private static final String USERNAME = "root"; // add your sql user name
    private static final String PASSWORD = "mahamaher1200746??"; // add your sql password

    public static Connection getConnection() throws SQLException {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish the connection to the database
            return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC driver not found.", e);
        }
    }


    public static void main(String[] args) {
        try {
            Connection connection = getConnection();

            // Perform database operations...

            // Close the connection
            connection.close();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }
    
    // to view the trainers table
    
    

}