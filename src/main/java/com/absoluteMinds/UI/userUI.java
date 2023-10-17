package com.absoluteMinds.UI;

import com.absoluteMinds.DAO.librarianDAO;
import com.absoluteMinds.DAO.librarianDAOImpl;
import com.absoluteMinds.DAO.userDAO;
import com.absoluteMinds.DAO.userDAOImpl;
import com.absoluteMinds.ENTITY.book;
import com.absoluteMinds.ENTITY.rental;
import com.absoluteMinds.ENTITY.user;
import com.absoluteMinds.EXCEPTIONS.NoRecordFoundException;
import com.absoluteMinds.EXCEPTIONS.SomeThingWentWrongException;
import com.absoluteMinds.SERVICE.*;

import java.util.List;
import java.util.Scanner;

public class userUI {

    static void userLogin(Scanner sc) throws SomeThingWentWrongException {
        System.out.println("\t\u001b[35m================================\u001b[0m");
        System.out.println("\t\u001b[45m\u001b[1m\t\t\t USER\t\t\t\t\u001b[0m");
        System.out.println("\t\u001b[35m================================\u001b[0m");
        System.out.println("\t\u001b[35m1. Register");
        System.out.println("\t2. Login");
        System.out.println("\t0. Main Menu\u001b[0m");
        String[] s = new String[]{""};
        System.out.print("\t\u001b[36mEnter Your Choice\u001b[0m : ");
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
        System.out.print("\t\u001b[35mEnter the Name      ");
        String name = sc.nextLine();
                  System.out.print("\tEnter the Username  ");
        String userName = sc.nextLine();
                  System.out.print("\tEnter the Password\u001b[0m  ");
        String password = sc.nextLine();

        userService service = new userServiceImpl();
        user user = new user(name,userName,password);
        try {
            service.addUser(user);
            System.out.println("\t\u001b[33mNew User Account Created Successfully\u001b[0m");
            userLogin(sc);
        } catch (SomeThingWentWrongException e) {
            System.out.println("\t"+e.getMessage());
        }
    }

    public static void loginUser(Scanner sc) {
        System.out.print("\t\u001b[35mEnter User Name  ");
        String userName = sc.nextLine();
                  System.out.print("\tEnter Password\u001b[0m   ");
        String password = sc.nextLine();

        userService service = new userServiceImpl();

        try {
            service.loginUser(userName,password);

//            System.out.println("\u001b[33m New User Account Created Successfully\u001b[0m");
        } catch (SomeThingWentWrongException e) {
            System.out.println("\t"+e.getMessage());
        }
    }

    public static void resetUserPassword(Scanner sc) throws SomeThingWentWrongException{
        System.out.print("\tEnter User Name  ");
        String userName = sc.nextLine();
        System.out.print("\tEnter New Password  ");
        String password = sc.nextLine();

        userService service = new userServiceImpl();
        try {
            service.resetUserPassword(userName,password);

        } catch (Exception e) {
            System.out.println("\t"+e.getMessage());
        }
    }

    public static void changeUserPassword(Scanner sc){
        userDAO da0 = new userDAOImpl();
        int userId = userDAOImpl.loggedInUserId;
        System.out.print("\tEnter Old Password  ");
        String oldpassword = sc.nextLine();
        System.out.print("\tEnter New Password  ");
        String newpassword = sc.nextLine();
        userService service = new userServiceImpl();
        try {
            service.changeUserPassword(userId, oldpassword, newpassword);
            System.out.println("\t\u001b[33mPassword changed successfully.\u001b[0m");
            userMenu(sc);
        } catch (SomeThingWentWrongException e) {
            System.out.println("\t" + e.getMessage());
        }
    }
    public static void userMenu(Scanner sc) throws SomeThingWentWrongException {
        int choice;
        do {
            System.out.println("\t\u001b[35m================================\u001b[0m");
            System.out.println("\t\u001b[45m\u001b[1m\t\t\t USER MENU\t\t\t\u001b[0m");
            System.out.println("\t\u001b[35m================================\u001b[0m");
            System.out.println("\t\u001b[35m1. View Available Books");
//            System.out.println("2. View Feedback");
            System.out.println("\t2. Rent a Book");
            System.out.println("\t3. View All Rented Book");
            System.out.println("\t4. Return a Book");
            System.out.println("\t5. Change Password");
            System.out.println("\t0. Log Out from the Student Account\u001b[0m");
            System.out.print("\t\u001b[36mEnter Your Choice\u001b[0m : ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    viewAvailableBooks();
                    break;
//                case 2:
//                    applyFiltersAndSorting();
//                    break;
                case 2:
                    rentBook();
                    break;
                case 3:
                    rentedBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    changeUserPassword(sc);
                    break;
                case 0:
                    userLogin(sc);
                    break;
                default:
                    System.out.println("\tInvalid Selection, try again.");
                    break;
            }
        } while (choice != 0);
    }

    private static void viewAvailableBooks() {
        bookService service = new bookServiceImpl();
        try {
            List<book> availableBooks = service.getAvailableBooks();
            for (book bk : availableBooks) {
                System.out.println(bk.toString());
            }
        } catch (Exception e) {
            System.out.println("\t"+e.getMessage());
        }
    }

    private static void rentedBook() {
        userDAO da0 = new userDAOImpl();
        int userId = userDAOImpl.loggedInUserId;
        int count =1;
        try{
            bookService service = new bookServiceImpl();
//            System.out.println(userId);
            List<rental> rentedBooks = service.rentedBooks(userId);

            System.out.println("\u001B[35m\tLIST OF RENTED BOOKS FOR USERID " + userId);
            for (rental rental : rentedBooks) {
                System.out.println("\t" + "\u001B[35m┌───────────── BOOK "+count+" ───────────" +"\n"+
                                    "\t"+ "\u001B[35m│ "+"Book ID     : " + rental.getBook().getBookId() +"\n"+
                                    "\t"+ "\u001B[35m│ "+"Book Name   : " + rental.getBook().getBookName() +"\n"+
                                    "\t"+ "\u001B[35m│ "+"Author      : " + rental.getBook().getAuthor() +"\n"+
                                    "\t"+ "\u001B[35m│ "+"Genre       : " + rental.getBook().getGenre() +"\n"+
                                    "\t"+ "\u001B[35m│ "+"Rented Date : " + rental.getRentedDate() +"\n"+
                                    "\t"+ "\u001B[35m│ "+"Return Date : " + rental.getReturnDate()+"\n"+
                                    "\t" +"\u001B[35m└───────────────────────────────────────\u001B[0m");
                count++;
            }
        } catch (SomeThingWentWrongException | NoRecordFoundException e) {
            System.out.println("\t"+e.getMessage());
        }
    }

    public static void rentBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\tEnter the Book ID to rent  ");
        int bookId = Integer.parseInt(sc.nextLine());

        userDAO da0 = new userDAOImpl();
        int userId = userDAOImpl.loggedInUserId;
        bookService service = new bookServiceImpl();

        try {
            service.rentBook(bookId, userId);
            System.out.println("\t\u001b[33mBook rented successfully.\u001b[0m");
        } catch (SomeThingWentWrongException e) {
            System.out.println("\t"+e.getMessage());
        } catch (NoRecordFoundException e) {
            System.out.println("\tNo book found with ID: " + bookId);
        }
    }

    private static void returnBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\t\u001B[35mEnter the Book ID to be return  \u001b[0m");
        int bookId = Integer.parseInt(sc.nextLine());
        bookService service = new bookServiceImpl();
        try {
            boolean val = service.returnBook(bookId);
            System.out.println(val ?"\t\u001b[33mBook Returned successfully.\u001b[0m":"\t\u001b[33mNo active rental found for this book.\u001b[0m");
        } catch (SomeThingWentWrongException e) {
            System.out.println("\t"+e.getMessage());
        } catch (NoRecordFoundException e) {
            System.out.println("\tNo book found with ID: " + bookId);
        }
    }

//    private static void applyFiltersAndSorting() {
//    }
//    private static void provideFeedbackAndRating() {
//    }


}
