package com.absoluteMinds.DAO;

import com.absoluteMinds.ENTITY.book;
import com.absoluteMinds.ENTITY.rental;
import com.absoluteMinds.ENTITY.user;
import com.absoluteMinds.EXCEPTIONS.NoRecordFoundException;
import com.absoluteMinds.EXCEPTIONS.SomeThingWentWrongException;
import com.absoluteMinds.UTILS.utils;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class bookDAOImpl implements bookDAO{

    @Override
    public void addBook(book book) throws SomeThingWentWrongException {
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            book.setAvailability(1);
            em = utils.getEntityManager();
            et = em.getTransaction();
            et.begin();
            em.persist(book);
            et.commit();
        }catch (PersistenceException e){
            et.rollback();
            throw new SomeThingWentWrongException("Unable to add book");
        }finally {
            em.close();
        }
    }

    @Override
    public void updateBookDetails(int id,book book) throws SomeThingWentWrongException, NoRecordFoundException {
        book bk = getByBookById(id);
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = utils.getEntityManager();
            et = em.getTransaction();

            et.begin();
            bk.setBookName(book.getBookName());
            bk.setAuthor(book.getAuthor());
            bk.setGenre(book.getGenre());
            em.merge(book);
            et.commit();

        }catch (PersistenceException e){
            et.rollback();
            throw new SomeThingWentWrongException("Unable to add book");
        }finally {
            em.close();
        }
    }

    @Override
    public void removeBook(int id) throws SomeThingWentWrongException, NoRecordFoundException {
        book bk = getByBookById(id);
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = utils.getEntityManager();
            et = em.getTransaction();

            et.begin();
            bk = em.merge(bk);
            em.remove(bk);
            et.commit();
        }catch (PersistenceException e) {
            et.rollback();
            throw new SomeThingWentWrongException("Unable to  delete book");
        }finally {
            em.close();
        }
    }

    @Override
    public List<book> getAvailableBooks() throws SomeThingWentWrongException, NoRecordFoundException {
        EntityManager em = null;
        List<book> availableBooks = null;

        try {
            em = utils.getEntityManager();
            // Query the database to get all the books where the availability is 0 (available)
            TypedQuery<book> query = em.createQuery(
                    "SELECT b FROM book b WHERE b.availability = 1",
                    book.class
            );
            availableBooks = query.getResultList();
            if (availableBooks.isEmpty()) {
                throw new NoRecordFoundException("No books are available");
            }
        } catch (PersistenceException e) {
            throw new SomeThingWentWrongException("Unable to process request, try again later");
        } finally {
            em.close();
        }
        return availableBooks;
    }

    @Override
    public List<rental> rentedBooks(int userId) throws SomeThingWentWrongException, NoRecordFoundException {
        EntityManager em = null;
        List<rental> rentals = null;

        try {
            em = utils.getEntityManager();

            // Get the user entity
            user usr = em.find(user.class, userId);
            if (usr == null) {
                throw new NoRecordFoundException("Invalid User ID");
            }

            // Use JOIN query to retrieve rented books for the user
            Query query = em.createQuery("SELECT r FROM rental r JOIN r.user u WHERE u.userID = :userId ");
            query.setParameter("userId", userId);
            rentals = query.getResultList();

            if (rentals.isEmpty()) {
                throw new NoRecordFoundException("No rented books found for this user.");
            }
        } catch (PersistenceException e) {
            throw new SomeThingWentWrongException("Unable to retrieve rented books, try again later.");
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return rentals;
    }


    @Override
    public void rentBook(int bookId, int userId) throws SomeThingWentWrongException, NoRecordFoundException {
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = utils.getEntityManager();
            et = em.getTransaction();
            et.begin();

            book bk = em.find(book.class, bookId);
            if (bk == null) {
                throw new NoRecordFoundException("Invalid Book ID");
            }

            // Check if the book is available for rent
            if (bk.getAvailability() == 1) {

                bk.setAvailability(0);
                rental rental = new rental();
                rental.setBook(bk);

                user usr = em.find(user.class, userId);
                if (usr == null) {
                    throw new NoRecordFoundException("Invalid User ID");
                }
                rental.setUser(usr);
                rental.setRentedDate(LocalDate.now());
                rental.setReturnDate(LocalDate.now().plusDays(7));
                em.persist(rental);
            } else
            {
                throw new SomeThingWentWrongException("Book is not available for rent.");
            }

            et.commit();
        } catch (PersistenceException e) {
            et.rollback();
            throw new SomeThingWentWrongException("Unable to process request, try again later");
        } finally {
            em.close();
        }
    }

    @Override
    public boolean returnBook(int bookId) throws SomeThingWentWrongException, NoRecordFoundException {
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = utils.getEntityManager();
            et = em.getTransaction();
            et.begin();

            book bk = em.find(book.class, bookId);
            if (bk == null) {
                throw new NoRecordFoundException("Invalid Book ID");
            }

            bk.setAvailability(1);

            Query query = em.createQuery("SELECT r FROM rental r WHERE r.book = :book");
            query.setParameter("book", bk);
            List<rental> rentals = query.getResultList();

            if (rentals.isEmpty()) {
                et.rollback();
                return false;
            }

            rental rentalEntry = rentals.get(0);
            em.remove(rentalEntry);

            et.commit();
            return true;
        } catch (PersistenceException e) {
            et.rollback();
            System.out.println(e.getMessage());
        } catch (Exception e) {
            // Log the unexpected exception
            e.printStackTrace();
            throw new SomeThingWentWrongException("An unexpected error occurred, try again later");
        } finally {
            em.close();
        }
        return false; // Return false if an exception occurred and book return was not successful
    }




    public book getByBookById(int id) throws SomeThingWentWrongException,NoRecordFoundException{
        book bk = null;
        EntityManager em = null;
        try{
            em = utils.getEntityManager();
            bk = em.find(book.class,id);
            if(bk == null){
                throw new NoRecordFoundException("Invalid Book ID");
            }
        }catch(PersistenceException e){
            throw new SomeThingWentWrongException("Unable to get Book Details,try again later");
        }finally {
            em.close();
        }
        return bk;
    }
}
