package com.liren.service;

import com.liren.domain.About;

import java.util.List;

/**
 * User: zhengli
 * Date: 12/5/12
 */
public interface AboutService {

    public List<About> getAll();

    public void create(About about);

    public void removeAll();
}
