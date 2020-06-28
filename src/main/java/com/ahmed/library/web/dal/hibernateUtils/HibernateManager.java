/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahmed.library.web.dal.hibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author Ahmed Hafez
 */
public class HibernateManager {
    private static SessionFactory sessionFactory;
    private static String dbConfigFileName;
    private HibernateManager(){
        
    }
    public static void setDbConfigFileName(String dbConfigFileName) {
        HibernateManager.dbConfigFileName = dbConfigFileName;
    }

    public static SessionFactory getSessionFactory() 
	{
            if(sessionFactory == null){
                    buildSessionFactory();
            }
               
		return sessionFactory;
	}

    public static void buildSessionFactory()  {
        try {
            if (sessionFactory != null) {
                return;
            }
            
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            MetadataSources sources = new MetadataSources(registry);
            Metadata metadata = sources.getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
          
            
        } catch (HibernateException e) {
            sessionFactory = null;
            dbConfigFileName = null;
            throw e;
        }
    }
    public static Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	public static void beginTransaction()
	{
		sessionFactory.getCurrentSession().beginTransaction();
	}

	public static void commitTransaction()
	{
		sessionFactory.getCurrentSession().getTransaction().commit();
	}

	public static void rollbackTransaction()
	{
		if (sessionFactory.getCurrentSession().getTransaction().isActive())
			sessionFactory.getCurrentSession().getTransaction().rollback();
	}
        public static Session openSession(){
            return getSessionFactory().openSession();
        }
        public static boolean isAliveTransaction(){
            return getCurrentSession().getTransaction().isActive();
        }
        
        
}
