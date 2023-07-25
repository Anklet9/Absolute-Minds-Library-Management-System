package com.absoluteMinds.ENTITY;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    @Column(nullable = false)
    private String bookName;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String genre;
    @Column(nullable = false, columnDefinition = "int default 1")
    private int availability;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<rental> rentals = new ArrayList<>();


    public book() {
        this.availability = 1; // Set the default value to 1
    }
    public book(String bookName, String author, String genre) {
        this.bookName = bookName;
        this.author = author;
        this.genre = genre;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public List<rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<rental> rentals) {
        this.rentals = rentals;
    }

    @Override
    public String toString() {
        return "\t" + "\u001b[35m┌───────────── BOOK ──────────────────────\u001b[0m" + "\n" +
                "\t"+ "\u001b[35m│ " + "Book ID      : " + bookId + "\u001b[0m\n" +
                "\t"+ "\u001b[35m│ " + "Book Name    : " + bookName + "\u001b[0m\n" +
                "\t"+ "\u001b[35m│ " + "Author       : " + author + "\u001b[0m\n" +
                "\t"+ "\u001b[35m│ " + "Genre        : " + genre + "\u001b[0m\n" +
                "\t"+ "\u001b[35m│ " + "Availability : " + availability + "\u001b[0m\n" +
                "\t" +"\u001b[35m└─────────────────────────────────────────";
    }

}
