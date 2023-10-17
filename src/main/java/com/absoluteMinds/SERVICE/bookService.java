package com.absoluteMinds.SERVICE;

import com.absoluteMinds.ENTITY.book;
import com.absoluteMinds.ENTITY.librarian;
import com.absoluteMinds.ENTITY.rental;
import com.absoluteMinds.EXCEPTIONS.NoRecordFoundException;
import com.absoluteMinds.EXCEPTIONS.SomeThingWentWrongException;

import java.util.List;
public interface bookService {

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
     * Removes a book record from the data store.
     *
     * @param id The unique identifier of the book to be deleted.
     * @throws SomeThingWentWrongException if an unexpected error occurs while deleting the book.
     * @throws NoRecordFoundException if no book record is found with the provided id.
     */
    void removeBook(int id) throws SomeThingWentWrongException, NoRecordFoundException;

    /**
     * Retrieves a list of available books from the data store.
     *
     * @return List of Book objects representing available books.
     * @throws SomeThingWentWrongException if an unexpected error occurs while retrieving the available books.
     * @throws NoRecordFoundException if no available books are found.
     */
    List<book> getAvailableBooks() throws SomeThingWentWrongException, NoRecordFoundException;

    /**
     * Rents a book to a user.
     *
     * @param bookId The unique identifier of the book to be rented.
     * @param userId The unique identifier of the user renting the book.
     * @throws SomeThingWentWrongException if an unexpected error occurs while renting the book.
     * @throws NoRecordFoundException if no book record is found with the provided bookId.
     */
    void rentBook(int bookId, int userId) throws SomeThingWentWrongException, NoRecordFoundException;

    /**
     * Retrieves a list of rented books for a user.
     *
     * @param userId The unique identifier of the user.
     * @return List of Rental objects representing rented books.
     * @throws SomeThingWentWrongException if an unexpected error occurs while retrieving the rented books.
     * @throws NoRecordFoundException if no rented books are found for the user.
     */
    List<rental> rentedBooks(int userId) throws SomeThingWentWrongException, NoRecordFoundException;

    /**
     * Returns a rented book to the inventory.
     *
     * @param bookId The unique identifier of the book to be returned.
     * @return True if the book is successfully returned, false otherwise.
     * @throws SomeThingWentWrongException if an unexpected error occurs while returning the book.
     * @throws NoRecordFoundException if no book record is found with the provided bookId.
     */
    boolean returnBook(int bookId) throws SomeThingWentWrongException, NoRecordFoundException;

    List<rental> getAllRentedBooks() throws SomeThingWentWrongException, NoRecordFoundException;
}
