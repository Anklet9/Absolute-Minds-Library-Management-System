package com.absoluteMinds.SERVICE;

import com.absoluteMinds.DAO.librarianDAO;
import com.absoluteMinds.DAO.librarianDAOImpl;
import com.absoluteMinds.ENTITY.librarian;
import com.absoluteMinds.EXCEPTIONS.SomeThingWentWrongException;

public class librarianServiceImpl implements librarianService {
    @Override
    public void addLibrarian(librarian librarian) throws SomeThingWentWrongException {
        librarianDAO dao = new librarianDAOImpl();
        dao.addLibrarian(librarian);
    }

    @Override
    public void login(String username, String password) throws SomeThingWentWrongException {
        librarianDAO dao = new librarianDAOImpl();
        dao.login(username, password);
    }

    @Override
    public void changePassword(int userID,String oldPassword, String newPassword) throws SomeThingWentWrongException {
        librarianDAO dao = new librarianDAOImpl();
        dao.changePassword(userID,oldPassword,newPassword);
    }

    @Override
    public void resetPassword(String username, String password) throws SomeThingWentWrongException {
        librarianDAO dao = new librarianDAOImpl();
        dao.resetPassword(username,password);
    }
}
