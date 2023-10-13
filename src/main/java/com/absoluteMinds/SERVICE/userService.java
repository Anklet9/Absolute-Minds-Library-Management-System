package com.absoluteMinds.SERVICE;


import com.absoluteMinds.ENTITY.user;
import com.absoluteMinds.EXCEPTIONS.SomeThingWentWrongException;

public interface userService {

    /**
     * Adds a new user to the data store.
     *
     * @param user The User object representing the user to be added.
     * @throws SomeThingWentWrongException if an unexpected error occurs while adding the user.
     */
    void addUser(user user) throws SomeThingWentWrongException;

    /**
     * Logs in a user using the provided username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @throws SomeThingWentWrongException if an unexpected error occurs during the login process.
     */
    void loginUser(String username, String password) throws SomeThingWentWrongException;

    /**
     * Changes the password of a user.
     *
     * @param userID The unique identifier of the user.
     * @param oldPassword The old password of the user.
     * @param newPassword The new password to be set for the user.
     * @throws SomeThingWentWrongException if an unexpected error occurs while changing the password.
     */
    void changeUserPassword(int userID, String oldPassword, String newPassword) throws SomeThingWentWrongException;

    /**
     * Resets the password of a user.
     *
     * @param username The username of the user.
     * @param password The new password to be set for the user.
     * @throws SomeThingWentWrongException if an unexpected error occurs while resetting the password.
     */
    void resetUserPassword(String username, String password) throws SomeThingWentWrongException;
}
