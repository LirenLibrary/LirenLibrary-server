package com.thoughtworks.lirenlab.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * User: zhengli
 * Date: 12/5/12
 */
public class HibernateRepository {


    private SessionFactory sessionFactory;

    protected Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
