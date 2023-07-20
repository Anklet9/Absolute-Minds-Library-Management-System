package com.absoluteMinds.UI;

import com.absoluteMinds.EXCEPTIONS.SomeThingWentWrongException;

import java.util.Scanner;

import static java.lang.System.exit;

public class App 
{
    public static void main( String[] args ) throws SomeThingWentWrongException {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do{
            System.out.println("\u001b[32m--------------------------------\u001b[0m");
            System.out.println("\u001b[42m\u001b[1m\tWELCOME TO ABSOLUTE MINDS\t\u001b[0m");
            System.out.println("\u001b[32m--------------------------------\u001b[0m");
            System.out.println("\u001b[32m1. Choice Your Role -> Librarian");
            System.out.println("2. Choice Your Role -> User");
            System.out.println("0. Exit\u001B[0m");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> librarianUI.librarianLogin(sc);
                case 2 -> userUI.userLogin(sc);
                case 0 -> System.out.println("\u001b[41mTHANKS FOR VISITING ABSOLUTE MINDS\u001b[0m");
                default -> {
                    exit(0);
                }
            }
        }while (choice!=0);
    }
}
