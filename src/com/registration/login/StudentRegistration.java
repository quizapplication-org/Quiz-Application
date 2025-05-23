//package com.registration.login;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.Scanner;
//import com.database.operations.CommonConnection;
//
//public class StudentRegistration  {
//
//    public static void registerStudent(String firstName, String lastName, String username, String password,
//                                       String city, String mailId, long mob) {
//        String insertSQL = "INSERT INTO Student_Data (first_name, last_name, username, password, city, mail_id, mobile_number) " +
//                "VALUES (?, ?, ?, ?, ?, ?, ?)";
//
//        try (Connection conn = CommonConnection.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
//
//            pstmt.setString(1, firstName);
//            pstmt.setString(2, lastName);
//            pstmt.setString(3, username);
//            pstmt.setString(4, password);
//            pstmt.setString(5, city);
//            pstmt.setString(6, mailId);
//            pstmt.setLong(7, mob);
//
//            int rowsInserted = pstmt.executeUpdate();
//            if (rowsInserted > 0) {
//                System.out.println("Student registered successfully!");
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException("Error registering student", e);
//        }
//    }
//
////    public static void main(String[] args)
//
//    public static void registerStudentFromInput() {
//        Scanner sc = new Scanner(System.in);
//
//        System.out.print("Enter the first name: ");
//        String firstName = sc.nextLine();
//
//        System.out.print("Enter the last name: ");
//        String lastName = sc.nextLine();
//
//        System.out.print("Enter the username: ");
//        String username = sc.nextLine();
//
//        System.out.print("Enter the password: ");
//        String password = sc.nextLine();
//
//        System.out.print("Enter the city: ");
//        String city = sc.nextLine();
//
//        System.out.print("Enter the mail ID: ");
//        String mailId = sc.nextLine();
//
//        System.out.print("Enter the mobile number: ");
//        long mob = sc.nextLong();
//
//        registerStudent(firstName, lastName, username, password, city, mailId, mob);
//    }
//
//}



package com.registration.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import com.database.operations.CommonConnection;

public class StudentRegistration {

    // Private fields
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String city;
    private String mailId;
    private long mobileNumber;

    // Constructor
    public StudentRegistration(String firstName, String lastName, String username, String password,
                               String city, String mailId, long mobileNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.city = city;
        this.mailId = mailId;
        this.mobileNumber = mobileNumber;
    }

    // Method to register student in database
    public void registerStudent() {
        String insertSQL = "INSERT INTO Student_Data (first_name, last_name, username, password, city, mail_id, mobile_number) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = CommonConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, username);
            pstmt.setString(4, password);
            pstmt.setString(5, city);
            pstmt.setString(6, mailId);
            pstmt.setLong(7, mobileNumber);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Student registered successfully!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error registering student", e);
        }
    }

    // Static method to read input and register student
    public static void registerStudentFromInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the first name: ");
        String firstName = sc.nextLine();

        System.out.print("Enter the last name: ");
        String lastName = sc.nextLine();

        System.out.print("Enter the username: ");
        String username = sc.nextLine();

        System.out.print("Enter the password: ");
        String password = sc.nextLine();

        System.out.print("Enter the city: ");
        String city = sc.nextLine();

        System.out.print("Enter the mail ID: ");
        String mailId = sc.nextLine();

        System.out.print("Enter the mobile number: ");
        long mobileNumber = sc.nextLong();

        StudentRegistration student = new StudentRegistration(firstName, lastName, username, password, city, mailId, mobileNumber);
        student.registerStudent();
    }
}
