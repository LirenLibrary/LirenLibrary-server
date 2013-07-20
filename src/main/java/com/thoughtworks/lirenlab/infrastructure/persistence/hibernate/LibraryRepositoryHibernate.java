package com.thoughtworks.lirenlab.infrastructure.persistence.hibernate;

import com.thoughtworks.lirenlab.domain.model.library.Library;
import com.thoughtworks.lirenlab.domain.model.library.LibraryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class LibraryRepositoryHibernate extends HibernateRepository implements LibraryRepository {

    @Override
    public String save(Library library) {
        currentSession().saveOrUpdate(library);
        return library.id();
    }

    @Override
    public List<Library> findAll() {
        return currentSession().createQuery("from Library").list();
    }

    @Override
    public void update(Library library) {
        Library oldLibrary = (Library) currentSession().load(Library.class, library.id());
        oldLibrary.setId(library.id());
        oldLibrary.setAddress(library.address());
        oldLibrary.setUpdatedDate();
        currentSession().flush();
//        currentSession().update(oldLibrary);
    }

    @Override
    public void delete(String id) {
        currentSession().delete(currentSession().load(Library.class, id));
    }
}
