package com.registration.login;

import com.database.operations.CommonConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminLogin {

    public static Integer loginAndReturnId() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Admin Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Admin Password: ");
        String password = sc.nextLine();

        String sql = "SELECT admin_id FROM admin_table WHERE username = ? AND password = ?";

        try (Connection conn = CommonConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int adminId = rs.getInt("admin_id");
                System.out.println("Admin login successful! Welcome, " + username);
                return adminId;
            }
            else {
                System.out.println("Invalid username or password. Please register if you haven’t already.");
                System.out.print("Do you want to register? (yes/no): ");
                String choice = sc.nextLine();

                if (choice.equalsIgnoreCase("yes")) {
                    // Calling the StudentRegistration method to register the student
                    AdminRegistration.registerAdminFromInput();
                }
                return null;  // Return null to indicate the login attempt failed
            }

        } catch (SQLException e) {
            System.out.println("Error during admin login: " + e.getMessage());
            return null;
        }
    }

}
