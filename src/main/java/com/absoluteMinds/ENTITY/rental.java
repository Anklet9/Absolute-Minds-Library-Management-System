package com.absoluteMinds.ENTITY;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rentalId;

    @ManyToOne(fetch = FetchType.EAGER) // Lazy fetching for Book entity
    @JoinColumn(name = "book_id", nullable = false)
    private book book;

    @ManyToOne(fetch = FetchType.EAGER) // Lazy fetching for User entity
    @JoinColumn(name = "user_id", nullable = false)
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
}
