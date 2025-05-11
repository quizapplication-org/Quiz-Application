package com.user.operations;

import com.database.operations.CommonConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DisplayQuiz {

    public static void fetchQuestions() {

        Scanner sc = new Scanner(System.in);

        String selectSQL = "SELECT queId, question, option_a, option_b, option_c, option_d, correct_option FROM question_answer";

        try (Connection conn = CommonConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectSQL);
             ResultSet rs = pstmt.executeQuery()) {

            // Iterate over the result set and display the questions and options
            while (rs.next()) {
                int queId = rs.getInt("queId");
                String question = rs.getString("question");
                String optionA = rs.getString("option_a");
                String optionB = rs.getString("option_b");
                String optionC = rs.getString("option_c");
                String optionD = rs.getString("option_d");

                System.out.println("Question: " + queId);
                System.out.println(question);
                System.out.println("A: " + optionA);
                System.out.println("B: " + optionB);
                System.out.println("C: " + optionC);
                System.out.println("D: " + optionD);

                System.out.print("Enter your answer (A/B/C/D): ");
                sc.next();  // Read and discard user input, since score isn't calculated

                System.out.println("------------------------------------------------");
            }

            System.out.println("Test completed!");  // Thank you message

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching questions", e);
        }
    }

    public static void main(String[] args) {
        fetchQuestions();
    }
}
