package com.absoluteMinds.SERVICE;

import com.absoluteMinds.ENTITY.librarian;
import com.absoluteMinds.EXCEPTIONS.SomeThingWentWrongException;

public interface librarianService {
    void addLibrarian (librarian librarian) throws SomeThingWentWrongException;
    void login(String username,String password) throws SomeThingWentWrongException;
    void changePassword(String oldPassword,String newPassword)throws SomeThingWentWrongException;
    void resetPassword(String username,String password) throws SomeThingWentWrongException;
}
