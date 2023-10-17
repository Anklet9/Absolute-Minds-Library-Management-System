package com.absoluteMinds.SERVICE;

import com.absoluteMinds.DAO.bookDAO;
import com.absoluteMinds.DAO.bookDAOImpl;
import com.absoluteMinds.ENTITY.book;
import com.absoluteMinds.ENTITY.rental;
import com.absoluteMinds.EXCEPTIONS.NoRecordFoundException;
import com.absoluteMinds.EXCEPTIONS.SomeThingWentWrongException;

import java.util.List;

public class bookServiceImpl implements bookService{

    @Override
    public void addBook(book book) throws SomeThingWentWrongException {
        bookDAO dao = new bookDAOImpl();
        dao.addBook(book);
    }

    @Override
    public void updateBookDetails(int id,book book) throws SomeThingWentWrongException, NoRecordFoundException {
        bookDAO dao = new bookDAOImpl();
        dao.updateBookDetails(id,book);
    }

    @Override
    public void removeBook(int id) throws SomeThingWentWrongException, NoRecordFoundException {
        bookDAO dao = new bookDAOImpl();
        dao.removeBook(id);
    }

    @Override
    public List<book> getAvailableBooks() throws SomeThingWentWrongException, NoRecordFoundException {
        bookDAO dao = new bookDAOImpl();
        return dao.getAvailableBooks();
    }

    @Override
    public void rentBook(int bookId, int userId) throws SomeThingWentWrongException, NoRecordFoundException {
        bookDAO dao = new bookDAOImpl();
        dao.rentBook(bookId, userId);
    }

    @Override
    public List<rental> rentedBooks(int userId) throws SomeThingWentWrongException, NoRecordFoundException {
        bookDAO dao = new bookDAOImpl();
        return dao.rentedBooks(userId);
    }

    @Override
    public boolean returnBook(int bookId) throws SomeThingWentWrongException, NoRecordFoundException {
        bookDAO dao = new bookDAOImpl();
        return dao.returnBook(bookId);
    }

    @Override
    public List<rental> getAllRentedBooks() throws SomeThingWentWrongException, NoRecordFoundException {
        bookDAO dao = new bookDAOImpl();
        return dao.getAllRentedBooks();
    }

}
