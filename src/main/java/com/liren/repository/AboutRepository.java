package com.liren.repository;

import com.liren.domain.About;

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
