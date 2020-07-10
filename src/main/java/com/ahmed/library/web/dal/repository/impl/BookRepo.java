/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.dal.repository.impl;

import com.ahmed.library.web.dal.entity.Book;
import com.ahmed.library.web.dal.entity.Book;
import static com.ahmed.library.web.dal.hibernateUtils.HibernateManager.getCurrentSession;
import com.ahmed.library.web.dal.repository.AbstractRepo;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.query.Query;

/**
 *
 * @book Ahmed Hafez
 */
public class BookRepo extends AbstractRepo<Book>{
    
    public BookRepo() {
        super(Book.class);
    }
    public int findByName(String keyword) {
        String pattern = keyword.trim();
         CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Book> entityCriteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> from = entityCriteriaQuery.from(Book.class);
        entityCriteriaQuery.select(from);
        entityCriteriaQuery.where(criteriaBuilder.equal(from.get("name"), pattern));
        Query<Book> bookQuery = getCurrentSession().createQuery(entityCriteriaQuery);
         List<Book> resultBooks = bookQuery.getResultList();
       return resultBooks.size();
    }
}
