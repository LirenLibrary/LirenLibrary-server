package com.thoughtworks.lirenlab.repository;

import com.thoughtworks.lirenlab.domain.About;

import java.util.List;

/**
 * User: zhengli
 * Date: 12/5/12
 */
public interface AboutRepository {

    public List<About> list();

    public void save(About about);

    void deleteAll();
}
