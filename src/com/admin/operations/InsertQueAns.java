package com.admin.operations;

import com.database.operations.CommonConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class InsertQueAns {

    // Method to insert the question into the database
    public static void insertQuestion(String question, String optionA, String optionB,
                                      String optionC, String optionD, String correctOption) {
        String insertSQL = "INSERT INTO question_answer (question, option_a, option_b, option_c, option_d, correct_option) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = CommonConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, question);
            pstmt.setString(2, optionA);
            pstmt.setString(3, optionB);
            pstmt.setString(4, optionC);
            pstmt.setString(5, optionD);
            pstmt.setString(6, correctOption.toUpperCase());

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Question inserted successfully.");
            } else {
                System.out.println("Insertion failed.");
            }

        } catch (Exception e) {
            e.printStackTrace(); // Use proper logging in real applications
        }
    }

    // Method to get input from the user and then insert the question
    public static void insertQuestionFromInput(Scanner scanner) {
        System.out.print("Enter the question: ");
        String question = scanner.nextLine();

        System.out.print("Enter option A: ");
        String optionA = scanner.nextLine();

        System.out.print("Enter option B: ");
        String optionB = scanner.nextLine();

        System.out.print("Enter option C: ");
        String optionC = scanner.nextLine();

        System.out.print("Enter option D: ");
        String optionD = scanner.nextLine();

        System.out.print("Enter the correct option (A/B/C/D): ");
        String correctOption = scanner.nextLine();

        // Call the insert method with the gathered data
        insertQuestion(question, optionA, optionB, optionC, optionD, correctOption);
    }
}
