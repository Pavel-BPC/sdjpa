package com.blinets.spjpa.dao.impl;

import com.blinets.spjpa.dao.BookDao;
import com.blinets.spjpa.domain.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class BookDaoImpl implements BookDao {

    private final EntityManagerFactory entityManagerFactory;

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public BookDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Book saveBook(Book book) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(book);
            entityManager.flush();
            entityManager.getTransaction().commit();
            return book;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteBookById(Long id) {

        EntityManager entityManager = getEntityManager();
        try {

            entityManager.getTransaction().begin();
            Book book = entityManager.find(Book.class, id);
            entityManager.remove(book);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Book updateBook(Book book) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(book);
            entityManager.flush();
            entityManager.getTransaction().commit();
            return book;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Book> findByTitle(String title) {
        EntityManager entityManager = getEntityManager();
        try {
            TypedQuery<Book> query = entityManager.createNamedQuery("find_book_by_title", Book.class);
            query.setParameter("title", title);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Book findById(Long id) {
        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.find(Book.class, id);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Book findByISBN(String isbn) {
        EntityManager entityManager = getEntityManager();
        try {
            TypedQuery<Book> query = entityManager.createQuery("select b from Book b where b.isbn = :isbn", Book.class);
            query.setParameter("isbn", isbn);
            return query.getSingleResult();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Book> findAll() {
        EntityManager entityManager = getEntityManager();
        try {
            TypedQuery<Book> books = entityManager.createNamedQuery("find_all_book", Book.class);
            return books.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Book findBookByTitleCriteria(String title) {
        EntityManager entityManager = getEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
            Root<Book> from = criteriaQuery.from(Book.class);
            ParameterExpression<String> parameterTitle = criteriaBuilder.parameter(String.class);
            criteriaQuery.select(from).where(criteriaBuilder.equal(from.get("title"), parameterTitle));
            TypedQuery<Book> query = entityManager.createQuery(criteriaQuery);
            query.setParameter(parameterTitle, title);
            return query.getSingleResult();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Book findBookByTitleNative(String title) {
        EntityManager entityManager = getEntityManager();
        try {
            Query nativeQuery = entityManager.createNativeQuery("select * from book b where b.title = :title", Book.class);
            nativeQuery.setParameter("title", title);
            return (Book) nativeQuery.getSingleResult();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Book> findAllBooks(int pageSize, int offset) {
        EntityManager entityManager = getEntityManager();
        try {
            TypedQuery<Book> query = entityManager.createQuery("select b from Book b", Book.class);
            query.setMaxResults(pageSize);
            query.setFirstResult(offset);
            return  query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Book> findAllBooks(Pageable pageable) {
        EntityManager entityManager = getEntityManager();
        try {
            TypedQuery<Book> query = entityManager.createQuery("select b from Book b", Book.class);
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            query.setMaxResults(pageable.getPageSize());
            return  query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Book> findAllBooksSortByTitle(Pageable pageable) {
        EntityManager entityManager = getEntityManager();
        try {
            String sql = "select b from Book b order by b.title " + pageable.getSort().getOrderFor("title").getDirection().name();
            TypedQuery<Book> query = entityManager.createQuery(sql, Book.class);
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            query.setMaxResults(pageable.getPageSize());
            return  query.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
