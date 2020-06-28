/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.dal.repository.impl;

import com.ahmed.library.web.dal.entity.Author;
import static com.ahmed.library.web.dal.hibernateUtils.HibernateManager.getCurrentSession;
import com.ahmed.library.web.dal.repository.AbstractRepo;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.query.Query;

/**
 *
 * @author Ahmed Hafez
 */
public class AuthorRepo extends AbstractRepo<Author>{
    
    public AuthorRepo() {
        super(Author.class);
    }   
    public int findByName(String keyword) {
        String pattern = keyword.trim();
         CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Author> entityCriteriaQuery = criteriaBuilder.createQuery(Author.class);
        Root<Author> from = entityCriteriaQuery.from(Author.class);
        entityCriteriaQuery.select(from);
        entityCriteriaQuery.where(criteriaBuilder.equal(from.get("name"), pattern));
        Query<Author> authorQuery = getCurrentSession().createQuery(entityCriteriaQuery);
         List<Author> resultAuthors = authorQuery.getResultList();
       return resultAuthors.size();
    }
}
