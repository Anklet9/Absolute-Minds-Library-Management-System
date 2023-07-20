package com.absoluteMinds.DAO;

import com.absoluteMinds.ENTITY.librarian;
import com.absoluteMinds.EXCEPTIONS.SomeThingWentWrongException;
import com.absoluteMinds.UI.librarianUI;
import com.absoluteMinds.UTILS.utils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

import java.util.Scanner;

public class librarianDAOImpl implements librarianDAO{
    @Override
    public void addLibrarian(librarian librarian) throws SomeThingWentWrongException {
        EntityManager em = null;
        try {
            em = utils.getEntityManager();
            Query query = em.createQuery("SELECT COUNT(l) FROM librarian l WHERE l.userName = :userName");
            query.setParameter("userName", librarian.getUserName());
            long usernameCount = (long) query.getSingleResult();

            if (usernameCount > 0) {
                throw new SomeThingWentWrongException("The username " + librarian.getUserName() + " already exists.");
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


            if (usernameCount == 0) {
                // Username not found
                System.out.println("Please check your username.");
                return;
            }else if (usernameCount !=0 && passwordCount==0){
                System.out.println("Password doesn't match. Do you want to change your password? (yes/no)");
                String changePasswordOption = sc.nextLine();

                if(changePasswordOption.equalsIgnoreCase("yes")){
                    librarianUI.resetPassword(sc);
                }else {
                    librarianUI.loginLibrarian(sc);
                }
            }else {


            }
        } catch (PersistenceException e) {
            throw new SomeThingWentWrongException("Unable to process request, try again later");
        }finally{
            em.close();
        }
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) throws SomeThingWentWrongException {

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
                System.out.println("Password reset successfully.");

            } else {
                System.out.println("Username not found. Password reset failed.");
            }

        } catch (PersistenceException e) {
            throw new SomeThingWentWrongException("Unable to process request, try again later");
        }finally{
            em.close();
        }
    }
}
