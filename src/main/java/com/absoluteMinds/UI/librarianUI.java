package com.absoluteMinds.UI;

import com.absoluteMinds.DAO.librarianDAO;
import com.absoluteMinds.DAO.librarianDAOImpl;
import com.absoluteMinds.DAO.userDAO;
import com.absoluteMinds.DAO.userDAOImpl;
import com.absoluteMinds.ENTITY.librarian;
import com.absoluteMinds.EXCEPTIONS.SomeThingWentWrongException;
import com.absoluteMinds.SERVICE.librarianService;
import com.absoluteMinds.SERVICE.librarianServiceImpl;

import java.sql.SQLOutput;
import java.util.Scanner;

public class librarianUI {
    public static void librarianLogin(Scanner sc) throws SomeThingWentWrongException {
        System.out.println("\t\u001b[34m================================\u001b[0m");
        System.out.println("\t\u001b[44m\u001b[1m\t\t\tLIBRARIAN\t\t\t\u001b[0m");
        System.out.println("\t\u001b[34m================================\u001b[0m");
        System.out.println("\t\u001b[34m1. Register");
        System.out.println("\t2. Login");
        System.out.println("\t0. Main Menu\u001b[0m");
        String[] s = new String[]{""};
        System.out.print("\t\u001b[36mEnter Your Choice\u001b[0m : ");
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
        System.out.print("\t\u001b[34mEnter the Librarian Name      ");
        String name = sc.nextLine();
        System.out.print("\tEnter the Librarian Username  ");
        String userName = sc.nextLine();
        System.out.print("\tEnter the Librarian Password\u001b[0m  ");
        String password = sc.nextLine();

        librarianService service = new librarianServiceImpl();
        librarian librarian = new librarian(name,userName,password);

        try {
            service.addLibrarian(librarian);
            System.out.println("\t\u001b[33mNew Librarian Account Created Successfully\u001b[0m");
            librarianLogin(sc);
        } catch (SomeThingWentWrongException e) {
            System.out.println("\t"+e.getMessage());
        }
    }

    public static void loginLibrarian(Scanner sc) throws SomeThingWentWrongException {
        System.out.print("\t\u001b[34mEnter Librarian User Name  ");
        String userName = sc.nextLine();
                  System.out.print("\tEnter Librarian Password \u001b[0m  ");
        String password = sc.nextLine();

        librarianService service = new librarianServiceImpl();
        try {
            service.login(userName,password);

        } catch (SomeThingWentWrongException e) {
            System.out.println("\t"+e.getMessage());
        }
    }

    public static void resetPassword(Scanner sc) throws SomeThingWentWrongException{
        System.out.print("\tEnter User Name  ");
        String userName = sc.nextLine();
        System.out.print("\tEnter New Password  ");
        String password = sc.nextLine();

        librarianService service = new librarianServiceImpl();
        try {
            service.resetPassword(userName,password);

        } catch (Exception e) {
            System.out.println("\t"+ e.getMessage());
        }
    }

    public static void changePassword(Scanner sc) {
        librarianDAO dao = new librarianDAOImpl();
        int userID = librarianDAOImpl.loggedInUserId;
        System.out.print("\tEnter Old Password  ");
        String oldpassword = sc.nextLine();
        System.out.print("\tEnter New Password  ");
        String newpassword = sc.nextLine();
        librarianService service = new librarianServiceImpl();
        try {
            service.changePassword(userID, oldpassword, newpassword);
            System.out.println("\t\u001b[33mPassword changed successfully.\u001b[0m");
            librarianMenu(sc);
        } catch (SomeThingWentWrongException e) {
            System.out.println("\t" + e.getMessage());
        }
    }

    public static void librarianMenu(Scanner sc) throws SomeThingWentWrongException {
        System.out.println("\t\u001b[36m================================\u001b[0m");
        System.out.println("\t\u001b[46m\u001b[1m\t\tLIBRARY ADMIN MENU\t\t\u001b[0m");
        System.out.println("\t\u001b[36m================================\u001b[0m");
        System.out.println("\t\u001b[36m1. Add New Book");
        System.out.println("\t2. Update Book Details");
        System.out.println("\t3. Remove Book");
        System.out.println("\t4. Student Rental");
//        System.out.println("\t5. View Feedback And Rating");
        System.out.println("\t5. Change Password");
        System.out.println("\t0. Log Out From Librarian Account\u001b[0m");

        String[] s = new String[]{""};
        int choice = 0;

        do {
            System.out.print("\t\u001b[36mEnter Your Choice\u001b[0m : ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> bookUI.addBook(sc);
                case 2 -> bookUI.updateBookDetails(sc);
                case 3 -> bookUI.removeBook(sc);
                case 4 -> bookUI.viewStatus(sc);
//                case 5 -> bookUI.viewFeedback(sc);
                case 5 -> changePassword(sc);
                case 0 -> {
                    System.out.println("\t\u001b[36mLogged Out.\u001b[0m");
                    librarianLogin(sc);
                }
            }
        }while (choice !=0);
    }
}
