package com.thoughtworks.lirenlab.infrastructure.persistence.hibernate;

import com.thoughtworks.lirenlab.domain.model.library.Library;
import com.thoughtworks.lirenlab.domain.model.library.LibraryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class LibraryRepositoryHibernate extends HibernateRepository implements LibraryRepository {
    public static final int MAX_RESULTS = 20;
    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryRepositoryHibernate.class);

    @Override
    public String save(Library library) {
        library.setUpdatedDate();
        currentSession().saveOrUpdate(library);
        LOGGER.info("save library: {}", library);
        return library.getId();
    }

    @Override
    public List<Library> findAll() {
        final List all = currentSession().createQuery("from Library").list();
        LOGGER.info("find all libraries");
        return all;
    }

    @Override
    public void update(Library library) {
        Library oldLibrary = (Library) currentSession().load(Library.class, library.getId());
        oldLibrary.update(library);
        LOGGER.info("update library: {}", library);
    }

    @Override
    public void delete(String id) {
        currentSession().delete(currentSession().load(Library.class, id));
        LOGGER.info("delete library: {}", id);
    }

    @Override
    public List<Library> findByNamePrefix(String prefix) {
        final List list = currentSession().createQuery("from Library lib where lib.name like :prefix")
                .setString("prefix", prefix + "%").setMaxResults(MAX_RESULTS).list();
        LOGGER.info("find library with prefix: {}, size: {}", prefix, list.size());
        return list;
    }
}
