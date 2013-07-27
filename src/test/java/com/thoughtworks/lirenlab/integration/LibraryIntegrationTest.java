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

        String result = libraryService.add("江南图书馆", "koly", address, "3000", "13789612345");

        List<Library> all = libraryService.findAll();
        assertThat(all.get(0).address(), is(address));
        System.out.println("The returning value is: " + result);
        assertThat(result, notNullValue());
    }

    @Ignore(value = "for not deciding which is same")
    @Test(expected = NonUniqueObjectException.class)
    public void should_not_create_an_already_existing_library(){
        String address = "Chengdu Sichuan";
        libraryService.add("sdgdf23f", address);

        libraryService.add("sdgdf23f", address);
    }

    @Ignore
    @Test
    public void should_update_a_library() {
        libraryService.add("sdgdf23f", "Chengdu Sichuan");
        String newAddress = "chengdu Sichuan China";
        libraryService.update("sdgdf23f", newAddress);
        List<Library> all = libraryService.findAll();

        assertThat(all.get(0).address(), is(newAddress));
    }

    @Ignore
    @Test(expected = ObjectNotFoundException.class)
    public void should_not_update_an_non_exist_library(){
        libraryService.update("sdgdf23f", "Chongqing");
    }

    @Ignore
    @Test
    public void should_delete_a_library() {
        libraryService.add("sdgdf23f", "Chengdu Sichuan");
        libraryService.delete("sdgdf23f");

        List<Library> all = libraryService.findAll();

        assertThat(all.size(), is(0));
    }

    @Ignore
    @Test(expected = ObjectNotFoundException.class)
    public void should_not_delete_an_non_exist_library(){
        libraryService.delete("2l");
    }
}
