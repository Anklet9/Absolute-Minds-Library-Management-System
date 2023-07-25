package com.absoluteMinds.SERVICE;

import com.absoluteMinds.DAO.userDAO;
import com.absoluteMinds.DAO.userDAOImpl;
import com.absoluteMinds.ENTITY.librarian;
import com.absoluteMinds.ENTITY.user;
import com.absoluteMinds.EXCEPTIONS.SomeThingWentWrongException;

public class userServiceImpl implements userService {
    @Override
    public void addUser(user user) throws SomeThingWentWrongException {
        userDAO dao = new userDAOImpl();
        dao.addUser(user);
    }

    @Override
    public void loginUser(String username, String password) throws SomeThingWentWrongException {
        userDAO dao = new userDAOImpl();
        dao.loginUser(username,password);
    }

    @Override
    public void changeUserPassword(int userID,String oldPassword, String newPassword) throws SomeThingWentWrongException {
        userDAO dao = new userDAOImpl();
        dao.changeUserPassword(userID,oldPassword,newPassword);
    }


    @Override
    public void resetUserPassword(String username, String password) throws SomeThingWentWrongException {
        userDAO dao = new userDAOImpl();
        dao.resetUserPassword(username,password);
    }
}
