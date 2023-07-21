package com.absoluteMinds.UI;

import com.absoluteMinds.EXCEPTIONS.SomeThingWentWrongException;

import java.util.Scanner;

public class userUI {
    static void userLogin(Scanner sc) throws SomeThingWentWrongException {
        System.out.println("\u001b[35m--------------------------------\u001b[0m");
        System.out.println("\u001b[45m\u001b[1m\t\t\t USER\t\t\t\t\u001b[0m");
        System.out.println("\u001b[35m--------------------------------\u001b[0m");
        System.out.println("\u001b[35m1. Register");
        System.out.println("2. Login");
        System.out.println("0. Main Menu\u001b[0m");
        String[] s = new String[]{""};
        int choice = Integer.parseInt(sc.nextLine());
        do {
            switch (choice) {
                case 1 -> registerUser(sc);
                case 2 -> loginUser(sc);
                case 0 -> App.main(s);
//                default:System.out.println("Invalid Selection, try again");
            }
        }while (choice !=0);
    }

    private static void registerUser(Scanner sc) {
        System.out.println("Enter Name");
        String name = sc.nextLine();
        System.out.println("Enter UserName");
        String userName = sc.nextLine();
        System.out.println("Enter Password");
        String password = sc.nextLine();
    }

    private static void loginUser(Scanner sc) {

    }
}
