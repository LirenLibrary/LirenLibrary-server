package com.thoughtworks.lirenlab.integration;

import com.thoughtworks.lirenlab.application.LibraryService;
import com.thoughtworks.lirenlab.domain.model.library.Library;
import com.thoughtworks.lirenlab.domain.model.library.LibraryRepository;
import org.hibernate.NonUniqueObjectException;
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
    public void should_not_delete_an_non_exist_library(){
        libraryService.delete("2l");
    }
}
