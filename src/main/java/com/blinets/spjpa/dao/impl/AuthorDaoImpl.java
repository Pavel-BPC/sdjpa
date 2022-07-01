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

    private final EntityManager entityManager;

    @Override
    public List<Author> getListAuthorByLastNameLike(String lastName){
        EntityManager entityManager = getEntityManager();
        try{
            Query query = entityManager.createQuery("SELECT a from Author a where a.lastName like :last_name");
            query.setParameter("last_name", lastName + "%");
            List<Author> resultList = query.getResultList();
            return resultList;
        }finally {
//            entityManager.close();
        }
    }

    public AuthorDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Author findById(Long id) {
        return getEntityManager().find(Author.class, id);
    }

    @Override
    public Author findByName(String firstName, String lastName) {
        TypedQuery<Author> query = getEntityManager().createQuery("select a from Author a" +
                        " where a.firstName = :first_name and a.lastName = :last_name",
                Author.class);
        query.setParameter("first_name", firstName);
        query.setParameter("last_name", lastName);

        return query.getSingleResult();
    }

    @Override
    public Author saveNewAuthor(Author author) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(author);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return author;
    }

    @Override
    public Author updateAuthor(Author author) {
        EntityManager em = getEntityManager();
//        em.joinTransaction();
//        em.merge(author);
//        em.flush();
//        em.clear();
        em.getTransaction().begin();
        em.merge(author);
        em.flush();
        em.getTransaction().commit();
        return em.find(Author.class, author.getId());
    }

    @Override
    public void deleteAuthorById(Long id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, id);
        entityManager.remove(author);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    private EntityManager getEntityManager() {
        return entityManager;
    }
}
