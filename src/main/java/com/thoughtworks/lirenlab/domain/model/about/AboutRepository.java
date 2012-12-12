package com.thoughtworks.lirenlab.domain.model.about;

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
