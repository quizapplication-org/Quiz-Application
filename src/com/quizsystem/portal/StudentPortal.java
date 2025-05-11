//package com.quizsystem.portal;
//
//import com.user.operations.DisplayQuiz;
//import com.registration.login.StudentLogin;
//import com.registration.login.StudentRegistration;
//
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//public class StudentPortal {
//
//    public static void studentOperationsMenu() {
//        Scanner scanner = new Scanner(System.in);
//        int choice = -1;
//        Integer loggedInStudentId = null;
//
//        do {
//            System.out.println("------   Student Portal  ------");
//            System.out.println("1. Student Registration");
//            System.out.println("2. Student Login");
//            System.out.println("3. Start Quiz");
//            System.out.println("4. View your score and correct answers");
//            System.out.println("0. Exit");
//            System.out.println("--------------------------------------------");
//            System.out.print("Enter your choice: ");
//
//            try {
//                choice = scanner.nextInt();
//                scanner.nextLine(); // consume newline
//
//                switch (choice) {
//                    case 1:
//                        StudentRegistration.registerStudentFromInput();
//                        break;
//                    case 2:
//                        loggedInStudentId = StudentLogin.loginAndReturnId();
//                        break;
//                    case 3:
//                        if (loggedInStudentId != null) {
//                            DisplayQuiz.fetchAndSubmitAnswers(loggedInStudentId);
//                        } else {
//                            System.out.println("Please login first to start the quiz.");
////                            promptLogin(scanner);
//                        }
//                        break;
//                    case 4:
//                        if (loggedInStudentId != null) {
//                            DisplayQuiz.displayStudentScore(loggedInStudentId);
//                        } else {
//                            System.out.println("Please login first to view your score.");
//                            promptLogin(scanner);
//                        }
//                        break;
//                    case 0:
//                        System.out.println("Exiting student portal... Thank you!");
//                        break;
//                    default:
//                        System.out.println("Invalid choice. Please try again.");
//                }
//
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input! Please enter a number from the menu.");
//                scanner.nextLine(); // clear the invalid input
//            }
//
//            System.out.println();
//
//        } while (choice != 0);
//
////        scanner.close();
//    }
//
//    private static void promptLogin(Scanner scanner) {
//        System.out.println("Do you want to log in to your account? (yes/no)");
//        String ch = scanner.next();
//        scanner.nextLine(); // consume newline
//        if (ch.equalsIgnoreCase("yes")) {
//            StudentLogin.loginAndReturnId();
//        }
//    }
//}



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
            System.out.println("3. Start Quiz");
            System.out.println("4. View your score and correct answers");
            System.out.println("0. Logout");
            System.out.println("----------------------------------");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 3:
                        DisplayScoreWithAnswers.fetchAndSubmitAnswers(loggedInStudentId);
                        break;
                    case 4:
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
