package com.registration.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import com.database.operations.CommonConnection;

public class AdminRegistration {

    public static void registerAdmin(String username, String password) {
        String insertSQL = "INSERT INTO admin_table (username, password) VALUES (?, ?)";

        try (Connection conn = CommonConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Admin registered successfully!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error registering admin", e);
        }
    }

    public static void registerAdminFromInput()
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the admin username: ");
        String username = sc.nextLine();

        System.out.print("Enter the admin password: ");
        String password = sc.nextLine();

        // Fixed the method call
        registerAdmin(username, password);
    }

//    public static void main(String[] args) {
//        registerAdminFromInput();
//    }
}
