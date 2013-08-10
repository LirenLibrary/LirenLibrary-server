package com.thoughtworks.lirenlab.infrastructure.persistence.hibernate;

import com.thoughtworks.lirenlab.domain.model.library.Library;
import com.thoughtworks.lirenlab.domain.model.library.LibraryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class LibraryRepositoryHibernate extends HibernateRepository implements LibraryRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryRepositoryHibernate.class);


    @Override
    public String save(Library library) {
        library.setUpdatedDate();
        currentSession().saveOrUpdate(library);
        LOGGER.info("save into database: " + library.getAddress());
        return library.id();
    }

    @Override
    public List<Library> findAll() {
        return currentSession().createQuery("from Library").list();
    }

    @Override
    public void update(Library library) {
        Library oldLibrary = (Library) currentSession().load(Library.class, library.id());
        oldLibrary.update(library);
        LOGGER.info("update library: " + library.getAddress());
    }

    @Override
    public void delete(String id) {
        currentSession().delete(currentSession().load(Library.class, id));
        LOGGER.info("delete library: " + id);
    }
}
