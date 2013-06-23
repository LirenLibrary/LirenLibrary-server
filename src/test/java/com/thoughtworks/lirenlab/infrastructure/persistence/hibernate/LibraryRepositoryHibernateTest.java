package com.thoughtworks.lirenlab.infrastructure.persistence.hibernate;

import com.thoughtworks.lirenlab.domain.model.library.Library;
import org.junit.Test;

import static org.mockito.Mockito.verify;

public class LibraryRepositoryHibernateTest extends RepositoryTestBase {

    private LibraryRepositoryHibernate libraryRepositoryHibernate;

    @Override
    public void moreSetUp() {
        libraryRepositoryHibernate = new LibraryRepositoryHibernate();
        libraryRepositoryHibernate.setSessionFactory(sessionFactory);
    }

    @Test
    public void should_save_a_library() {
        Library library = new Library("ChengDu, Sichuan");
        libraryRepositoryHibernate.save(library);

        verify(session).saveOrUpdate(library);
    }
}
