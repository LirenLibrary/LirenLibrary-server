package com.thoughtworks.lirenlab.integration;

import com.thoughtworks.lirenlab.application.LibraryService;
import com.thoughtworks.lirenlab.domain.model.library.Library;
import com.thoughtworks.lirenlab.domain.model.library.LibraryRepository;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
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
        libraryService.add(1l, address);
        List<Library> all = libraryService.findAll();

        assertThat(all.get(0).address(), is(address));
    }

    @Test(expected = NonUniqueObjectException.class)
    public void should_not_create_an_already_existing_library(){
        String address = "Chengdu Sichuan";
        libraryService.add(1l, address);

        libraryService.add(1l, address);
    }

    @Test
    public void should_update_a_library() {
        libraryService.add(1l, "Chengdu Sichuan");
        String newAddress = "chengdu Sichuan China";
        libraryService.update(1l, newAddress);
        List<Library> all = libraryService.findAll();

        assertThat(all.get(0).address(), is(newAddress));
    }

    @Test(expected = ObjectNotFoundException.class)
    public void should_not_update_an_non_exist_library(){
        libraryService.update(1l, "Chongqing");
    }

    @Test
    public void should_delete_a_library() {
        libraryService.add(1l, "Chengdu Sichuan");
        libraryService.delete(1l);

        List<Library> all = libraryService.findAll();

        assertThat(all.size(), is(0));
    }

    @Test(expected = ObjectNotFoundException.class)
    public void should_not_delete_an_non_exist_library(){
        libraryService.delete(2l);
    }
}
