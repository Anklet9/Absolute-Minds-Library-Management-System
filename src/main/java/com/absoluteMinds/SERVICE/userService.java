package com.absoluteMinds.SERVICE;

import com.absoluteMinds.ENTITY.librarian;
import com.absoluteMinds.ENTITY.user;
import com.absoluteMinds.EXCEPTIONS.SomeThingWentWrongException;

public interface userService {
    void addUser (user user) throws SomeThingWentWrongException;
    void loginUser(String username,String password) throws SomeThingWentWrongException;
    void changeUserPassword(int userID,String oldPassword,String newPassword)throws SomeThingWentWrongException;
    void resetUserPassword(String username,String password) throws SomeThingWentWrongException;
}
