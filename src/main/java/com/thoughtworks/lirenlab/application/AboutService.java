package com.thoughtworks.lirenlab.application;

import com.thoughtworks.lirenlab.domain.model.about.About;

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
