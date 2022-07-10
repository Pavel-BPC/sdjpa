package com.blinets.spjpa.dao.impl;

import com.blinets.spjpa.dao.AuthorDao;
import com.blinets.spjpa.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final EntityManagerFactory entityManagerFactory;


    @Override
    public List<Author> getListAuthorByLastNameLike(String lastName) {
        EntityManager entityManager = getEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT a from Author a where a.lastName like :last_name");
            query.setParameter("last_name", lastName + "%");
            List<Author> resultList = query.getResultList();
            return resultList;
        } finally {
            entityManager.close();
        }
    }

    public AuthorDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<Author> findAll() {
        EntityManager entityManager = getEntityManager();
        try {
            Query author_find_all = entityManager.createNamedQuery("author_find_all");
            return author_find_all.getResultList();

        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public Author findById(Long id) {
        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.find(Author.class, id);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Author findByName(String firstName, String lastName) {
        EntityManager entityManager = getEntityManager();
        try {
            TypedQuery<Author> query = entityManager.createQuery("select a from Author a" +
                            " where a.firstName = :first_name and a.lastName = :last_name",
                    Author.class);
            query.setParameter("first_name", firstName);
            query.setParameter("last_name", lastName);
            return query.getSingleResult();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Author saveNewAuthor(Author author) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(author);
            entityManager.flush();
            entityManager.getTransaction().commit();
            return author;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Author updateAuthor(Author author) {

        EntityManager em = getEntityManager();
        try {
//            em.joinTransaction();
//            em.merge(author);
//            em.flush();
//            em.clear();
            em.getTransaction().begin();
            em.merge(author);
            em.flush();
            em.getTransaction().commit();
            return em.find(Author.class, author.getId());
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteAuthorById(Long id) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Author author = entityManager.find(Author.class, id);
            entityManager.remove(author);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
