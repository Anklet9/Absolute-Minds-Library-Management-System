package com.absoluteMinds.DAO;

import com.absoluteMinds.ENTITY.librarian;
import com.absoluteMinds.EXCEPTIONS.SomeThingWentWrongException;

public interface librarianDAO {
    void addLibrarian (librarian librarian) throws SomeThingWentWrongException;
    void login(String username, String password) throws SomeThingWentWrongException;
    void changePassword(int userID,String oldPassword,String newPassword)throws SomeThingWentWrongException;
    void resetPassword(String username,String password) throws SomeThingWentWrongException;

}
