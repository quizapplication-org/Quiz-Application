package com.quizsystem.portal;

import com.user.operations.DisplayScoreWithAnswers;
import com.registration.login.StudentLogin;
import com.registration.login.StudentRegistration;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentPortal {

    public static void studentOperationsMenu() {
        Scanner scanner = new Scanner(System.in);
        Integer loggedInStudentId = null;

        // --------- Stage 1: Registration & Login ----------
        while (loggedInStudentId == null) {
            System.out.println("------   Student Access Required   ------");
            System.out.println("1. Student Registration");
            System.out.println("2. Student Login");
            System.out.println("0. Exit");
            System.out.println("-----------------------------------------");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        StudentRegistration.registerStudentFromInput();
                        break;
                    case 2:
                        loggedInStudentId = StudentLogin.loginAndReturnId();
                        break;
                    case 0:
                        System.out.println("Exiting student portal... Thank you!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number from the menu.");
                scanner.nextLine(); // clear invalid input
            }

            System.out.println();
        }

        // --------- Stage 2: Post-Login Menu ----------
        int choice;
        do {
            System.out.println("------   Welcome Student   ------");
            System.out.println("1. Start Quiz");
            System.out.println("2. View your score and correct answers");
            System.out.println("0. Logout");
            System.out.println("----------------------------------");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        DisplayScoreWithAnswers.fetchAndSubmitAnswers(loggedInStudentId);
                        break;
                    case 2:
                        DisplayScoreWithAnswers.displayStudentScore(loggedInStudentId);
                        break;
                    case 0:
                        System.out.println("Logging out...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number from the menu.");
                scanner.nextLine(); // clear invalid input
                choice = -1; // continue loop
            }

            System.out.println();

        } while (choice != 0);
    }
}
