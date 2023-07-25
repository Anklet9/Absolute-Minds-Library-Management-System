package com.absoluteMinds.UTILS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class utils {
    static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("dbConnect");
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
