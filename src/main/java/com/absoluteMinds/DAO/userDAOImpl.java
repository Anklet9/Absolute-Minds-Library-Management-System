package com.absoluteMinds.DAO;

import com.absoluteMinds.ENTITY.librarian;
import com.absoluteMinds.ENTITY.user;
import com.absoluteMinds.EXCEPTIONS.SomeThingWentWrongException;
import com.absoluteMinds.UI.librarianUI;
import com.absoluteMinds.UI.userUI;
import com.absoluteMinds.UTILS.utils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

import java.util.Scanner;

public class userDAOImpl implements userDAO{
    public static int loggedInUserId = -1;
    @Override
    public void addUser(user user) throws SomeThingWentWrongException {
        Scanner sc = new Scanner(System.in);
        EntityManager em = null;
        try {
            em = utils.getEntityManager();
            Query query = em.createQuery("SELECT COUNT(u) FROM user u WHERE u.userName = :userName");
            query.setParameter("userName", user.getUserName());
            long usernameCount = (long) query.getSingleResult();

            if (usernameCount > 0) {
                System.out.println("\t\u001b[33mTHE USERNAME " + user.getUserName() + " ALREADY EXISTS.PLEASE RETRY\u001b[1m");
                userUI.loginUser(sc);
            }

            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(user);
            et.commit();

        } catch (PersistenceException e)
        {
            throw new SomeThingWentWrongException("Unable to process the request, try again later.");
        } finally
        {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void loginUser(String username, String password) throws SomeThingWentWrongException {
            EntityManager em = null;
            Scanner sc = new Scanner(System.in);
            try {
                em = utils.getEntityManager();
                Query usernameQuery = em.createQuery("SELECT COUNT(u) FROM user u WHERE u.userName = :username");
                usernameQuery.setParameter("username",username);
                long usernameCount = (long) usernameQuery.getSingleResult();


                Query passwordQuery = em.createQuery("SELECT COUNT(u) FROM user u WHERE u.userName = :username AND u.password = :password");
                passwordQuery.setParameter("username", username);
                passwordQuery.setParameter("password", password);
                long passwordCount = (long) passwordQuery.getSingleResult();

//            Query nameQuery = em.createQuery("SELECT l.name FROM librarian l WHERE l.userName = :username AND l.password = :password");
//            nameQuery.setParameter("username", username);
//            nameQuery.setParameter("password", password);
//            String librarianName = (String) nameQuery.getSingleResult();

                if (usernameCount == 0) {
                    // Username not found
                    System.out.println("\t\u001b[33mPlease check your username.");
                    return;
                }else if (passwordCount == 0){
                    System.out.println("\t\u001b[33mPassword doesn't match. Do you want to change your password? (yes/no)\u001b[1m");
                    String changePasswordOption = sc.nextLine();

                    if(changePasswordOption.equalsIgnoreCase("yes")){
                        userUI.resetUserPassword(sc);
                    }else {
                        userUI.loginUser(sc);
                    }
                }else {
                    loggedInUserId = getUserIdByUsername(username);
                    System.out.println("\t\u001b[33mLogged In Successfully\u001b[1m");
//                System.out.println("\u001b[33m Welcome " + librarianName + " To Admin Panel\u001b[0m");
                    userUI.userMenu(sc);
                }
            } catch (PersistenceException e) {
                throw new SomeThingWentWrongException("Unable to process request, try again later");
            }finally{
                em.close();
            }

    }

    @Override
    public void changeUserPassword(int userID,String oldPassword, String newPassword) throws SomeThingWentWrongException {
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = utils.getEntityManager();
            et = em.getTransaction();
            et.begin();

            user userToUpdate = em.find(user.class, userID);

            if (userToUpdate == null) {
                throw new SomeThingWentWrongException("Invalid User ID");
            }

            if (!userToUpdate.getPassword().equals(oldPassword)) {
                throw new SomeThingWentWrongException("Incorrect old password");
            }

            userToUpdate.setPassword(newPassword);
            em.merge(userToUpdate);

            et.commit();
        } catch (PersistenceException e) {
            et.rollback();
            throw new SomeThingWentWrongException("Unable to process request, try again later");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void resetUserPassword(String username, String password) throws SomeThingWentWrongException {
        EntityManager em = null;
        try {
            em = utils.getEntityManager();
            Query query = em.createQuery("update user u set u.password = :password where u.userName =:username");
            query.setParameter("password",password);
            query.setParameter("username",username);

            em.getTransaction().begin();
            int updatedRows = query.executeUpdate();
            em.getTransaction().commit();

            if (updatedRows > 0) {
                System.out.println("\t\u001b[33mPassword reset successfully.\u001b[1m");

            } else {
                System.out.println("\t\u001b[33mUsername not found. Password reset failed.\u001b[1m");
            }

        } catch (PersistenceException e) {
            throw new SomeThingWentWrongException("Unable to process request, try again later");
        }finally{
            em.close();
        }
    }
    private int getUserIdByUsername(String username) {
        EntityManager em = null;
        try {
            em = utils.getEntityManager();
            Query query = em.createQuery("SELECT u.userID FROM user u WHERE u.userName = :username");
            query.setParameter("username", username);
            return (int) query.getSingleResult();
        } catch (PersistenceException e) {
            // Handle the exception as per your requirement
            return -1;
        } finally {
            em.close();
        }
    }


}
