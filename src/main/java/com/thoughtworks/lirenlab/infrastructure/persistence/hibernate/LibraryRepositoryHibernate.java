package com.thoughtworks.lirenlab.infrastructure.persistence.hibernate;

import com.thoughtworks.lirenlab.domain.model.library.Library;
import com.thoughtworks.lirenlab.domain.model.library.LibraryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LibraryRepositoryHibernate extends HibernateRepository implements LibraryRepository {

    @Override
    public void save(Library library) {
        currentSession().saveOrUpdate(library);
    }
}
