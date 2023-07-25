package com.absoluteMinds.UI;

import com.absoluteMinds.ENTITY.book;
import com.absoluteMinds.EXCEPTIONS.NoRecordFoundException;
import com.absoluteMinds.EXCEPTIONS.SomeThingWentWrongException;
import com.absoluteMinds.SERVICE.bookService;
import com.absoluteMinds.SERVICE.bookServiceImpl;
import jakarta.persistence.PersistenceException;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class bookUI {

    public static void addBook(Scanner sc){
        System.out.println("\tEnter Book Name");
        String bookName = sc.nextLine();
        System.out.println("\tEnter Author Name");
        String authorName = sc.nextLine();
        System.out.println("\tEnter Genre");
        String genre = sc.nextLine();

        bookService service = new bookServiceImpl();
        book book = new book(bookName,authorName,genre);
        try {
            service.addBook(book);
            System.out.println("\tBook Added Successfully");
            librarianUI.librarianMenu(sc);
        }catch(SomeThingWentWrongException e) {
            System.out.println("\t"+e.getMessage());
        }
    }

    public static void updateBookDetails(Scanner sc){
        System.out.println("\tEnter the bookID for updating the book details ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("\tEnter the Updated Book Name");
        String bookName = sc.nextLine();
        System.out.println("\tEnter the Updated Author Name");
        String authorName = sc.nextLine();
        System.out.println("\tEnter the Updated Genre");
        String genre = sc.nextLine();
        bookService service = new bookServiceImpl();
        book book = new book(bookName,authorName,genre);
        try {
            service.updateBookDetails(id,book);
            System.out.println("\tBook Details Updated Successfully for "+id);
        } catch (SomeThingWentWrongException | NoRecordFoundException e) {
            System.out.println("\t"+e.getMessage());
        }

    }

    public static void removeBook(Scanner sc){
        System.out.println("\tEnter the bookID needs to be Removed ");
        int id = Integer.parseInt(sc.nextLine());

        bookService service = new bookServiceImpl();
        try {
            service.removeBook(id);
            System.out.println("\tBook Deleted Successfully");
        } catch (SomeThingWentWrongException |NoRecordFoundException e) {
            System.out.println("\t"+e.getMessage());
        }
    }

    public static void viewStatus(Scanner sc){

    }
    public static void viewFeedback(Scanner sc){

    }
}
