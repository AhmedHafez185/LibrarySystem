/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.dal.repository.impl;

import com.ahmed.library.web.dal.entity.User;
import com.ahmed.library.web.dal.entity.User;
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
public class UserRepo extends AbstractRepo<User>{
    public UserRepo() {
        super(User.class);
    }
    public User loginAdmin(String username,String password){
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> from = userCriteriaQuery.from(User.class);
       userCriteriaQuery.select(from);
       userCriteriaQuery.where(criteriaBuilder.and(
                criteriaBuilder.equal(from.get("username"), username),
                criteriaBuilder.equal(from.get("password"), password))
        );
        Query<User> cityQuery = getCurrentSession().createQuery(userCriteriaQuery);
        List<User> resultUsers = cityQuery.getResultList();
        if(resultUsers.size() > 0){
           return resultUsers.get(0); 
        }else{
            return null;
        }
    }
    public Integer findByName(String username) {
         CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<User> entityCriteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> from = entityCriteriaQuery.from(User.class);
        entityCriteriaQuery.select(from);
        entityCriteriaQuery.where(criteriaBuilder.equal(from.get("username"), username));
        Query<User> userQuery = getCurrentSession().createQuery(entityCriteriaQuery);
        List<User> resultUsers = userQuery.getResultList();
        if(resultUsers.size() > 0){
           return resultUsers.get(0).getId(); 
        }else{
            return 0;
        }
        
    }
}
