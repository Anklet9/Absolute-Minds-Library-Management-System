package com.absoluteMinds.ENTITY;

import jakarta.persistence.*;

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
    @Column(nullable = false, columnDefinition = "int default 0")
    private int availability;

    public book() {
        super();
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

    @Override
    public String toString() {
        return "book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", availability=" + availability +
                '}';
    }
}
