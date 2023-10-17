package com.absoluteMinds.ENTITY;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rentalId;

    @ManyToOne(fetch = FetchType.EAGER) // Lazy fetching for Book entity
    @JoinColumn(name = "book_id", nullable = false)
//    @JsonIgnore
    private book book;

    @ManyToOne(fetch = FetchType.EAGER) // Lazy fetching for User entity
    @JoinColumn(name = "user_id", nullable = false)
//    @JsonIgnore
    private user user;

    @Column(nullable = false)
    private LocalDate rentedDate;

    @Column(nullable = false)
    private LocalDate returnDate;

    public rental(book book,user user) {
        this.book = book;
        this.user = user;
    }

    public rental() {

    }

    public com.absoluteMinds.ENTITY.book getBook() {
        return book;
    }

    public void setBook(com.absoluteMinds.ENTITY.book book) {
        this.book = book;
    }

    public com.absoluteMinds.ENTITY.user getUser() {
        return user;
    }

    public void setUser(com.absoluteMinds.ENTITY.user user) {
        this.user = user;
    }

    public LocalDate getRentedDate() {
        return rentedDate;
    }

    public void setRentedDate(LocalDate rentedDate) {
        this.rentedDate = rentedDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "\t" + "\u001b[36m┌───────────── ALL RENTED BOOK ──────────────────────\u001b[0m" + "\n" +
                "\t"+ "\u001b[36m│ " + "Rental ID      : " + rentalId + "\u001b[0m\n" +
                "\t"+ "\u001b[36m│ " + "Rented Date    : " + rentedDate + "\u001b[0m\n" +
                "\t"+ "\u001b[36m│ " + "Return Date    : " + returnDate + "\u001b[0m\n" +
                "\t"+ "\u001b[36m│ " + "Book ID        : " + (book != null ? book.getBookId() : "N/A" )+ "\u001b[0m\n" +
                "\t"+ "\u001b[36m│ " + "User ID        : " + (user != null ? user.getUserId() : "N/A" )+ "\u001b[0m\n" +
                "\t" +"\u001b[36m└─────────────────────────────────────────\u001b[0m";
    }

}
