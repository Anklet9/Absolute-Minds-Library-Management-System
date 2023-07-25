package com.absoluteMinds.SERVICE;

import com.absoluteMinds.ENTITY.book;
import com.absoluteMinds.ENTITY.librarian;
import com.absoluteMinds.ENTITY.rental;
import com.absoluteMinds.EXCEPTIONS.NoRecordFoundException;
import com.absoluteMinds.EXCEPTIONS.SomeThingWentWrongException;

import java.util.List;

public interface bookService {
    void addBook (book book) throws SomeThingWentWrongException;
    void updateBookDetails (int id,book book) throws SomeThingWentWrongException, NoRecordFoundException;
    void removeBook (int id) throws SomeThingWentWrongException, NoRecordFoundException;

    List<book> getAvailableBooks()throws SomeThingWentWrongException, NoRecordFoundException;

    void rentBook(int bookId, int userId) throws SomeThingWentWrongException,NoRecordFoundException;

    List<rental> rentedBooks(int userId) throws SomeThingWentWrongException,NoRecordFoundException;

    boolean returnBook(int bookId) throws SomeThingWentWrongException,NoRecordFoundException;

}
