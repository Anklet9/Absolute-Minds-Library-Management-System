package com.absoluteMinds.DAO;

import com.absoluteMinds.ENTITY.book;
import com.absoluteMinds.ENTITY.rental;
import com.absoluteMinds.EXCEPTIONS.NoRecordFoundException;
import com.absoluteMinds.EXCEPTIONS.SomeThingWentWrongException;

import java.util.List;


public interface bookDAO {
    /**
     * Adds a new book to the data store.
     *
     * @param book The Book object representing the book to be added.
     * @throws SomeThingWentWrongException if an unexpected error occurs while adding the book.
     */
    void addBook(book book) throws SomeThingWentWrongException;

    /**
     * Updates the details of an existing book in the data store.
     *
     * @param id The unique identifier of the book to be updated.
     * @param book The Book object containing the updated book details.
     * @throws SomeThingWentWrongException if an unexpected error occurs while updating the book.
     * @throws NoRecordFoundException if no book record is found with the provided id.
     */
    void updateBookDetails(int id, book book) throws SomeThingWentWrongException, NoRecordFoundException;

    /**
     * Removes a book from the data store.
     *
     * @param id The unique identifier of the book to be removed.
     * @throws SomeThingWentWrongException if an unexpected error occurs while deleting the book.
     * @throws NoRecordFoundException if no book record is found with the provided id.
     */
    void removeBook(int id) throws SomeThingWentWrongException, NoRecordFoundException;

    /**
     * Retrieves a list of available books from the data store.
     *
     * @return List of Book objects representing available books.
     * @throws SomeThingWentWrongException if an unexpected error occurs while retrieving available books.
     * @throws NoRecordFoundException if no available books are found.
     */
    List<book> getAvailableBooks() throws SomeThingWentWrongException, NoRecordFoundException;

    /**
     * Retrieves a list of books rented by a specific user from the data store.
     *
     * @param userId The unique identifier of the user.
     * @return List of Rental objects representing rented books for the user.
     * @throws SomeThingWentWrongException if an unexpected error occurs while retrieving rented books.
     * @throws NoRecordFoundException if no rented books are found for the user.
     */
    List<rental> rentedBooks(int userId) throws SomeThingWentWrongException, NoRecordFoundException;

    /**
     * Rents a book for a specific user.
     *
     * @param bookId The unique identifier of the book to be rented.
     * @param userId The unique identifier of the user.
     * @throws SomeThingWentWrongException if an unexpected error occurs while renting the book.
     * @throws NoRecordFoundException if no book record is found with the provided bookId.
     */
    void rentBook(int bookId, int userId) throws SomeThingWentWrongException, NoRecordFoundException;

    /**
     * Returns a rented book, making it available for rent again.
     *
     * @param bookId The unique identifier of the rented book to be returned.
     * @return True if the book was successfully returned, false otherwise.
     * @throws SomeThingWentWrongException if an unexpected error occurs while returning the book.
     * @throws NoRecordFoundException if no rented book record is found with the provided bookId.
     */
    boolean returnBook(int bookId) throws SomeThingWentWrongException, NoRecordFoundException;
}