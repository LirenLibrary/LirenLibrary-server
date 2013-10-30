package com.thoughtworks.lirenlab.infrastructure.persistence.hibernate;

import com.thoughtworks.lirenlab.domain.model.library.Library;
import org.hibernate.Query;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
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
        Library library = new Library("江南图书馆", "koly", "chengdu sichuan", "610060", "13789645321");
        libraryRepositoryHibernate.save(library);

        verify(session).saveOrUpdate(library);
    }

    @Test
    public void should_find_all_libraries() {
        Query query = mock(Query.class);
        given(session.createQuery(anyString())).willReturn(query);

        List<Library> all = libraryRepositoryHibernate.findAll();

        assertThat(all, notNullValue());
        verify(query).list();
    }

    @Test
    @Ignore
    public void should_update_a_library() {
        given(session.load(any(Class.class), anyLong())).willReturn(mock(Library.class));

        libraryRepositoryHibernate.update(new Library("江南图书馆", "koly", "chengdu sichuan", "610060", "13789645321"));

        verify(session).flush();
    }

    @Test
    public void should_delete_a_library() {
        libraryRepositoryHibernate.delete("12l");

        verify(session).delete(any());
    }

    @Test
    public void should_find_by_name_prefix() throws Exception {
        final Query query = mock(Query.class);
        given(session.createQuery(anyString())).willReturn(query);
        given(query.setString(anyString(), anyString())).willReturn(query);
        given(query.setMaxResults(anyInt())).willReturn(query);

        final String prefix = "teda";
        final List<Library> byName = libraryRepositoryHibernate.findByNamePrefix(prefix);
        assertThat(byName, notNullValue());

        verify(session).createQuery(contains(":prefix"));
        verify(query).setString(eq("prefix"), eq(prefix + "%"));
        verify(query).setMaxResults(eq(LibraryRepositoryHibernate.MAX_RESULTS));
    }
}
