package com.thoughtworks.lirenlab.application.impl;

import com.thoughtworks.lirenlab.domain.model.about.About;
import com.thoughtworks.lirenlab.domain.model.about.AboutRepository;
import com.thoughtworks.lirenlab.application.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: zhengli
 * Date: 12/5/12
 */
@Service
@Transactional
public class AboutServiceImpl implements AboutService {

    private AboutRepository aboutRepository;

    @Override
    public List<About> getAll() {
        return aboutRepository.list();
    }

    @Override
    public void create(About about) {
        aboutRepository.save(about);
    }

    @Override
    public void removeAll() {
        aboutRepository.deleteAll();
    }

    @Autowired
    public void setAboutRepository(AboutRepository aboutRepository) {
        this.aboutRepository = aboutRepository;
    }
}
