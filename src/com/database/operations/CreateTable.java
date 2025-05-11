package com.database.operations;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

    // Method to create necessary tables
    public static void createQuestionAnswerTable() {

        String createTableSQL = "CREATE TABLE IF NOT EXISTS admin_table ("
                + "admin_id INT AUTO_INCREMENT PRIMARY KEY, "
                + "username VARCHAR(50) NOT NULL, "
                + "password VARCHAR(255) NOT NULL"
                + ")";

        String createQuestionTableSQL = "CREATE TABLE IF NOT EXISTS question_answer (" +
                "queId INT PRIMARY KEY AUTO_INCREMENT, " +
                "question TEXT NOT NULL, " +
                "option_a VARCHAR(255) NOT NULL, " +
                "option_b VARCHAR(255) NOT NULL, " +
                "option_c VARCHAR(255) NOT NULL, " +
                "option_d VARCHAR(255) NOT NULL, " +
                "correct_option CHAR(1) NOT NULL" +
                ");";

        String createStudentDataTableSQL = "CREATE TABLE IF NOT EXISTS student_data (" +
                "student_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "first_name VARCHAR(50) NOT NULL, " +
                "last_name VARCHAR(50) NOT NULL, " +
                "username VARCHAR(50) NOT NULL UNIQUE, " +
                "password VARCHAR(255) NOT NULL, " +
                "city VARCHAR(50), " +
                "mail_id VARCHAR(100), " +
                "mobile_number BIGINT" +
                ");";

        // Adding the creation of student_answers and student_scores tables
        String createStudentAnswersTableSQL = "CREATE TABLE IF NOT EXISTS student_answers (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "student_id INT, " +
                "question_id INT, " +
                "user_answer CHAR(1), " +
                "correct_answer CHAR(1), " +
                "is_correct BOOLEAN" +
                ");";

        String createStudentScoresTableSQL = "CREATE TABLE IF NOT EXISTS student_scores (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "student_id INT, " +
                "score INT" +
                ");";

        try (Connection conn = CommonConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Create the tables

            stmt.executeUpdate(createTableSQL);
            System.out.println("Table admin_table created successfully.");

            stmt.executeUpdate(createQuestionTableSQL);
            System.out.println("Table 'question_answer' created successfully.");

            stmt.executeUpdate(createStudentDataTableSQL);
            System.out.println("Table 'student_data' created successfully.");

            stmt.executeUpdate(createStudentAnswersTableSQL);
            System.out.println("Table 'student_answers' created successfully.");

            stmt.executeUpdate(createStudentScoresTableSQL);
            System.out.println("Table 'student_scores' created successfully.");

        } catch (SQLException e) {
            throw new RuntimeException("Failed to create tables", e);
        }
    }

    public static void main(String[] args) {
        // Create all the necessary tables
        createQuestionAnswerTable();
    }
}

