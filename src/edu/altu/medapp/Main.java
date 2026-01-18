package edu.altu.medapp;

import edu.altu.medapp.db.DatabaseConnection;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
        try {
            DatabaseConnection db = DatabaseConnection.getInstance();
            Connection conn = db.getConnection();

            System.out.println("Database: " + conn.getMetaData().getDatabaseProductName());

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT NOW()");

            if (rs.next()) {
                System.out.println("Database current time: " + rs.getTimestamp(1));
            }

            rs.close();
            stmt.close();
            conn.close();

            System.out.println("Database is connected, ready to build the medical appointment system.");

        } catch (Exception e) {
            System.out.println("\nERROR: Could not connect to database");
            System.out.println("Details: " + e.getMessage());
        }
    }
}