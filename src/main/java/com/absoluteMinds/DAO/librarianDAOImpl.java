package com.absoluteMinds.DAO;

import com.absoluteMinds.ENTITY.librarian;
import com.absoluteMinds.EXCEPTIONS.SomeThingWentWrongException;
import com.absoluteMinds.UI.librarianUI;
import com.absoluteMinds.UTILS.utils;
import jakarta.persistence.*;

import java.util.Scanner;

public class librarianDAOImpl implements librarianDAO{
    public static int loggedInUserId = -1;
    @Override
    public void addLibrarian(librarian librarian) throws SomeThingWentWrongException {
        Scanner sc = new Scanner(System.in);
        EntityManager em = null;
        try {
            em = utils.getEntityManager();
            Query query = em.createQuery("SELECT COUNT(l) FROM librarian l WHERE l.userName = :userName");
            query.setParameter("userName", librarian.getUserName());
            long usernameCount = (long) query.getSingleResult();

            if (usernameCount > 0) {
                System.out.println("\t\u001b[33mTHE USERNAME " + librarian.getUserName() + " ALREADY EXISTS.PLEASE RETRY\u001b[1m");
                librarianUI.librarianLogin(sc);
            }

            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(librarian);
            et.commit();
        } catch (PersistenceException e) {
            throw new SomeThingWentWrongException("Unable to process the request, try again later.");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void login(String username, String password) throws SomeThingWentWrongException {
        EntityManager em = null;
        Scanner sc = new Scanner(System.in);
        try {
            em = utils.getEntityManager();
            Query usernameQuery = em.createQuery("SELECT COUNT(l) FROM librarian l WHERE l.userName = :username");
            usernameQuery.setParameter("username",username);
            long usernameCount = (long) usernameQuery.getSingleResult();


            Query passwordQuery = em.createQuery("SELECT COUNT(l) FROM librarian l WHERE l.userName = :username AND l.password = :password");
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
                    librarianUI.resetPassword(sc);
                }else {
                    librarianUI.librarianLogin(sc);
                }
            }else {
                loggedInUserId = getUserIdByUsername(username);
                System.out.println("\t\u001b[33mLogged In Successfully\u001b[1m");
//                System.out.println("\u001b[33m Welcome " + librarianName + " To Admin Panel\u001b[0m");
                librarianUI.librarianMenu(sc);
            }
        } catch (PersistenceException e) {
            throw new SomeThingWentWrongException("Unable to process request, try again later");
        }finally{
            em.close();
        }
    }

    @Override
    public void changePassword(int userID, String oldPassword, String newPassword) throws SomeThingWentWrongException {
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = utils.getEntityManager();
            et = em.getTransaction();
            et.begin();

            librarian librarianToUpdate = em.find(librarian.class, userID);

            if (librarianToUpdate == null) {
                throw new SomeThingWentWrongException("Invalid User ID");
            }

            if (!librarianToUpdate.getPassword().equals(oldPassword)) {
                throw new SomeThingWentWrongException("Incorrect old password");
            }

            librarianToUpdate.setPassword(newPassword);
            em.merge(librarianToUpdate);

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
    public void resetPassword(String username, String password) throws SomeThingWentWrongException {
        EntityManager em = null;
        try {
            em = utils.getEntityManager();
            Query query = em.createQuery("update librarian l set l.password = :password where l.userName =:username");
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
            Query query = em.createQuery("SELECT l.id FROM librarian l WHERE l.userName = :username");
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
