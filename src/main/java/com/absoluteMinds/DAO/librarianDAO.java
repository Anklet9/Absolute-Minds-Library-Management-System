package com.absoluteMinds.DAO;

import com.absoluteMinds.ENTITY.librarian;
import com.absoluteMinds.EXCEPTIONS.SomeThingWentWrongException;

public interface librarianDAO {

    /**
     * Adds a new librarian to the data store.
     *
     * @param librarian The Librarian object representing the librarian to be added.
     * @throws SomeThingWentWrongException if an unexpected error occurs while adding the librarian.
     */
    void addLibrarian(librarian librarian) throws SomeThingWentWrongException;

    /**
     * Logs in a librarian using the provided username and password.
     *
     * @param username The username of the librarian.
     * @param password The password of the librarian.
     * @throws SomeThingWentWrongException if an unexpected error occurs during the login process.
     */
    void login(String username, String password) throws SomeThingWentWrongException;

    /**
     * Changes the password of a librarian.
     *
     * @param userID The unique identifier of the librarian.
     * @param oldPassword The old password of the librarian.
     * @param newPassword The new password to be set for the librarian.
     * @throws SomeThingWentWrongException if an unexpected error occurs while changing the password.
     */
    void changePassword(int userID, String oldPassword, String newPassword) throws SomeThingWentWrongException;

    /**
     * Resets the password of a librarian.
     *
     * @param username The username of the librarian.
     * @param password The new password to be set for the librarian.
     * @throws SomeThingWentWrongException if an unexpected error occurs while resetting the password.
     */
    void resetPassword(String username, String password) throws SomeThingWentWrongException;
}