package com.main;

import com.quizsystem.portal.AdminPortal;
import com.quizsystem.portal.StudentPortal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        System.out.println("*** Welcome to the Quiz System ***");

        while (true) {
            System.out.println("\nPlease select your role : ");
            System.out.println("1. Admin");
            System.out.println("2. Student");
            System.out.println("0. Exit");
            System.out.print("\nEnter your choice : \n");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                System.out.println();

                switch (choice) {
                    case 1:
                        // Call Admin Portal
                        AdminPortal.adminOperationsMenu();
                        break;
                    case 2:
                        // Call Student Portal
                        StudentPortal.studentOperationsMenu();
                        break;
                    case 0:
                        System.out.println("Thank you for using the Quiz System. Bye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter correct choice (0-2");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number (0–2).");
            }
        }
    }
}
