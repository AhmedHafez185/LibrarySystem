/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.dal.hibernateUtils;


import com.ahmed.library.web.dal.entity.Author;
import com.ahmed.library.web.dal.entity.Book;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.HashSet;
import java.util.Set;
import static com.ahmed.library.web.dal.hibernateUtils.HibernateManager.*;
/**
 *
 * @author Ahmed Hafez
 */
public class DALMain {

    public static void main(String[] args) {
        try(Session session = HibernateManager.getSessionFactory().getCurrentSession()){
            beginTransaction();
            System.out.println("Session Opened");
            Author author3 = new Author("أحمد علاء", "مصر");
        
            session.persist(author3);
            commitTransaction();
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
            if(isAliveTransaction())
                
                rollbackTransaction();
        }
    }

}
