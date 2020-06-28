/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.dal.repository;

import static com.ahmed.library.web.dal.hibernateUtils.HibernateManager.getCurrentSession;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.query.Query;

/**
 *
 * @author Ahmed Hafez
 * @param <E>
 */
public abstract class AbstractRepo<E> implements CommonRepo<E>{
    private Class<E> entityType; 
    public AbstractRepo(Class<E> entityType){
        this.entityType = entityType;
    }
    @Override
    public E add(E entity) {
        getCurrentSession().persist(entity);
        return entity;
    }

    @Override
    public E update(E entity) {
        getCurrentSession().merge(entity);
        return entity;
      }

    @Override
    public void remove(Integer id) throws Exception{
        E foundEntity = getCurrentSession().load(entityType, id);
        getCurrentSession().delete(foundEntity);
    }

    @Override
    public E findById(Integer id) {
        return (E) getCurrentSession().get(entityType, id);
    }

    @Override
    public List<E> findList() {
         CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<E> entityCriteriaQuery = criteriaBuilder.createQuery(entityType);
        Root<E> from = entityCriteriaQuery.from(entityType);
        entityCriteriaQuery.select(from);
        Query<E> cityQuery = getCurrentSession().createQuery(entityCriteriaQuery);
        List<E> entityList = cityQuery.getResultList();
        return entityList;
    }

  
    
}
