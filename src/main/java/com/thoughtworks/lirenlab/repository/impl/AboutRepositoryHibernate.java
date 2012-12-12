package com.thoughtworks.lirenlab.repository.impl;

import com.thoughtworks.lirenlab.domain.About;
import com.thoughtworks.lirenlab.repository.AboutRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: zhengli
 * Date: 12/5/12
 */
@Repository
public class AboutRepositoryHibernate extends HibernateRepository implements AboutRepository {

    @Override
    public List<About> list() {
        return currentSession().createCriteria(About.class).list();
    }

    @Override
    public void save(About about) {
        currentSession().saveOrUpdate(about);
    }

    @Override
    public void deleteAll() {
        currentSession().createQuery("DELETE FROM About a").executeUpdate();
    }
}
