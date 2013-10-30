package com.thoughtworks.lirenlab.integration;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.thoughtworks.lirenlab.application.LibraryService;
import com.thoughtworks.lirenlab.domain.model.library.Library;
import com.thoughtworks.lirenlab.domain.model.library.LibraryRepository;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:context-test.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class LibraryIntegrationTest {
    @Autowired
    private LibraryService libraryService;
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private LibraryRepository libraryRepository;

    @Test
    public void should_create_a_library() {
        String address = "Chengdu Sichuan";

        Library library = new Library("江南图书馆", "koly", address, "3000", "13789612345");
        String result = libraryService.add(library);

        List<Library> all = libraryService.findAll();
        assertThat(all.get(0).address(), is(address));
        System.out.println("The returning value is: " + result);
        assertThat(result, notNullValue());
    }

    @Test
    public void should_create_a_library_with_null_id() {
        String address = "Chengdu Sichuan";

        Library library = new Library("江南图书馆", "koly", address, "3000", "13789612345");
        library.setId(null);
        String result = libraryService.add(library);

        List<Library> all = libraryService.findAll();
        assertThat(all.get(0).address(), is(address));
        System.out.println("The returning value is: " + result);
        assertThat(result, notNullValue());
    }

    @Test
    public void should_update_a_library() {
        String address = "Chengdu Sichuan";

        String uid = libraryService.add("江南图书馆", "koly", address, "3000", "13789612345");

        address = "chengdu Sichuan China";
        Library lib = new Library("江南图书馆", "koly", address, "3000", "13789612345");
        libraryService.update(uid, lib);
        List<Library> all = libraryService.findAll();

        assertThat(all.get(0).address(), is(address));
    }

    @Test
    public void should_delete_a_library() {
        String address = "Chengdu Sichuan";
        String uid = libraryService.add("江南图书馆", "koly", address, "3000", "13789612345");

        libraryService.delete(uid);

        List<Library> all = libraryService.findAll();

        assertThat(all.size(), is(0));
    }

    @Ignore
    @Test(expected = ObjectNotFoundException.class)
    public void should_not_delete_an_non_exist_library() {
        libraryService.delete("2l");
    }

    @Test
    public void should_only_find_the_first_20_libraries_prefixed_with_teda() throws Exception {
        for (int i = 0; i < 3; i++) {
            addNewLibraryWithName("not_teda_no" + i);
        }

        for (int i = 0; i < 23; i++) {
            addNewLibraryWithName("teda_no" + i);
        }

        assertThat(libraryService.findAll().size(), is(26));

        final List<Library> tedaLibs = libraryService.findByNamePrefix("teda");

        assertThat(tedaLibs.size(), is(20));

        Lists.transform(tedaLibs, new Function<Library, Void>() {
            @Override
            public Void apply(Library input) {
                assertThat(input.getName().startsWith("teda"), is(true));
                return null;
            }
        });
    }

    @Test
    public void should_return_empty_list_if_found_nothing() throws Exception {
        assertThat(libraryService.findByNamePrefix("i_am_the_mustnt_exist_prefix"), is(empty()));
    }

    private void addNewLibraryWithName(String name) {
        libraryService.add(new Library(name, "fff", "Tanggu Tianjin", "300457", "18612983047"));
    }
}
