package com.absoluteMinds.UI;

import com.absoluteMinds.ENTITY.librarian;
import com.absoluteMinds.EXCEPTIONS.SomeThingWentWrongException;
import com.absoluteMinds.SERVICE.librarianService;
import com.absoluteMinds.SERVICE.librarianServiceImpl;

import java.sql.SQLOutput;
import java.util.Scanner;

public class librarianUI {
    public static void librarianLogin(Scanner sc) throws SomeThingWentWrongException {
        System.out.println("\u001b[34m--------------------------------\u001b[0m");
        System.out.println("\u001b[44m\u001b[1m\t\t\tLIBRARIAN\t\t\t\u001b[0m");
        System.out.println("\u001b[34m--------------------------------\u001b[0m");
        System.out.println("\u001b[34m1. Register");
        System.out.println("2. Login");
        System.out.println("0. Main Menu\u001b[0m");
        String[] s = new String[]{""};
        int choice = Integer.parseInt(sc.nextLine());
        do {
            switch (choice) {
                case 1 -> registerLibrarian(sc);
                case 2 -> loginLibrarian(sc);
                case 0 -> App.main(s);
//                default:System.out.println("Invalid Selection, try again");
            }
        }while (choice !=0);
    }

    private static void registerLibrarian(Scanner sc) throws SomeThingWentWrongException {

        System.out.println("\u001b[34mEnter the Librarian Name");
        String name = sc.nextLine();
        System.out.println("Enter the Librarian Username");
        String userName = sc.nextLine();
        System.out.println("Enter the Librarian Password\u001b[0m");
        String password = sc.nextLine();

        librarianService service = new librarianServiceImpl();
        librarian librarian = new librarian(name,userName,password);

        try {
            service.addLibrarian(librarian);
            System.out.println("\u001b[33New Librarian Account Created Successfully\u001b[0m");
            librarianLogin(sc);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void loginLibrarian(Scanner sc) throws SomeThingWentWrongException {
        System.out.println("\u001b[34mEnter Librarian User Name");
        String userName = sc.nextLine();
        System.out.println("Enter Librarian Password\u001b[0m");
        String password = sc.nextLine();

        librarianService service = new librarianServiceImpl();
        try {
            service.login(userName,password);
        } catch (SomeThingWentWrongException e) {
            throw new RuntimeException(e);
        }
    }

    public static void resetPassword(Scanner sc) throws SomeThingWentWrongException{
        System.out.println("Enter User Name");
        String userName = sc.nextLine();
        System.out.println("Enter New Password");
        String password = sc.nextLine();

        librarianService service = new librarianServiceImpl();
        try {
            service.resetPassword(userName,password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void changePassword(Scanner sc){

    }
    public static void librarianMenu(Scanner sc) throws SomeThingWentWrongException {
        System.out.println("\u001b[36m--------------------------------\u001b[0m");
        System.out.println("\u001b[46m\u001b[1m\t\tLIBRARY ADMIN MENU\t\t\u001b[0m");
        System.out.println("\u001b[36m--------------------------------\u001b[0m");
        System.out.println("\u001b[36m1. Add New Book");
        System.out.println("2. Update Book Details");
        System.out.println("3. Remove Book");
        System.out.println("4. Student Rental");
        System.out.println("5. View Feedback And Rating");
        System.out.println("6. Change Password");
        System.out.println("0. Log Out From Librarian Account\u001b[0m");

        String[] s = new String[]{""};
        int choice = Integer.parseInt(sc.nextLine());

        do {
            switch (choice) {
                case 1 -> bookUI.addBook(sc);
                case 2 -> bookUI.updateBookDetails(sc);
                case 3 -> bookUI.removeBook(sc);
                case 4 -> bookUI.viewStatus(sc);
                case 5 -> bookUI.viewFeedback(sc);
                case 6 -> changePassword(sc);
                case 7 -> App.main(s);
//                default:System.out.println("Invalid Selection, try again");
            }
        }while (choice !=0);
    }
}
