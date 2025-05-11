//package com.quizsystem.portal;
//
//import com.admin.operations.FetchStudentScore;
//import com.admin.operations.InsertQueAns;
//import com.registration.login.AdminLogin;
//import com.registration.login.AdminRegistration;
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//public class AdminPortal {
//
//    public static void adminOperationsMenu() {
//        Scanner scanner = new Scanner(System.in);
//        int choice = -1;
//        Integer loggedInAdminId = null;
//
//        do {
//            System.out.println("------   Welcome to Admin Portal   ------");
//            System.out.println("1. Admin Registration");
//            System.out.println("2. Admin Login");
//            System.out.println("3. View All Student Scores");
//            System.out.println("4. View Individual Student Score");
//            System.out.println("5. Insert New Quiz Question");
//            System.out.println("0. Exit");
//            System.out.println("----------------------------------------------");
//            System.out.print("Enter your choice: ");
//            System.out.println();
//
//            try {
//                choice = scanner.nextInt();
//                scanner.nextLine(); // consume newline
//
//                switch (choice) {
//                    case 1:
//                        AdminRegistration.registerAdminFromInput();
//                        break;
//                    case 2:
//                        loggedInAdminId = AdminLogin.loginAndReturnId();
//                        break;
//                    case 3:
//                        if (loggedInAdminId != null) {
//                            FetchStudentScore.getAllStudentScores();
//                        } else {
//                            System.out.println("Please login first to view all student scores.");
//                            loggedInAdminId = promptAdminLogin(scanner);  // <-- Must store the result
//                            if (loggedInAdminId != null) {
//                                FetchStudentScore.getAllStudentScores();  // Retry after login
//                            }
//                        }
//                        break;
//
//                    case 4:
//                        if (loggedInAdminId != null) {
//                            System.out.print("Enter student ID to view score: ");
//                            int studentId = scanner.nextInt();
//                            FetchStudentScore.getStudentScore(studentId);
//                        } else {
//                            System.out.println("Please login first to view student scores.");
//                            promptAdminLogin(scanner);
//                        }
//                        break;
//                    case 5:
//                        if (loggedInAdminId != null) {
//                            // Call the method from InsertQueAns to handle question insertion
//                            InsertQueAns.insertQuestionFromInput(scanner);
//                        } else {
//                            System.out.println("Please login first to insert quiz questions.");
//                            promptAdminLogin(scanner);
//                        }
//                        break;
//                    case 0:
//                        System.out.println("Exiting admin portal... Thank you!");
//                        break;
//                    default:
//                        System.out.println("Invalid choice. Please try again.");
//                }
//
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input! Please enter a number from the menu.");
//                scanner.nextLine(); // clear invalid input
//            }
//
//            System.out.println();
//
//        } while (choice != 0);
//
////        scanner.close();
//    }
//
//    private static Integer promptAdminLogin(Scanner scanner) {
//        System.out.print("Do you want to log in to your admin account? (yes/no): ");
//        String ch = scanner.next();
//        scanner.nextLine(); // consume newline
//        if (ch.equalsIgnoreCase("yes")) {
//            return AdminLogin.loginAndReturnId(); // return ID
//        } else {
//            System.out.println("Okay, thank you!");
//            return null;
//        }
//    }
//}



package com.quizsystem.portal;

import com.admin.operations.FetchStudentScore;
import com.admin.operations.InsertQueAns;
import com.registration.login.AdminLogin;
import com.registration.login.AdminRegistration;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminPortal {

    public static void adminOperationsMenu() {
        Scanner scanner = new Scanner(System.in);
        Integer loggedInAdminId = null;

        // ---------- Stage 1: Registration & Login ----------
        while (loggedInAdminId == null) {
            System.out.println("------   Admin Access Required   ------");
            System.out.println("1. Admin Registration");
            System.out.println("2. Admin Login");
            System.out.println("0. Exit");
            System.out.println("---------------------------------------");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        AdminRegistration.registerAdminFromInput();
                        break;
                    case 2:
                        loggedInAdminId = AdminLogin.loginAndReturnId();
                        break;
                    case 0:
                        System.out.println("Exiting admin portal... Thank you!");
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

        // ---------- Stage 2: Admin Operations ----------
        int choice;
        do {
            System.out.println("------   Welcome Admin   ------");
            System.out.println("3. View All Student Scores");
            System.out.println("4. View Individual Student Score");
            System.out.println("5. Insert New Quiz Question");
            System.out.println("0. Logout");
            System.out.println("-------------------------------");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 3:
                        FetchStudentScore.getAllStudentScores();
                        break;
                    case 4:
                        System.out.print("Enter student ID to view score: ");
                        int studentId = scanner.nextInt();
                        FetchStudentScore.getStudentScore(studentId);
                        break;
                    case 5:
                        InsertQueAns.insertQuestionFromInput(scanner);
                        break;
                    case 0:
                        System.out.println("Logging out...");
                        break;
                    default:
                        System.out.println("Invalid choice.");
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
