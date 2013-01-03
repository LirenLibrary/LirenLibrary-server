package com.thoughtworks.lirenlab.domain.model.donation;

import org.junit.Test;

import static com.thoughtworks.lirenlab.domain.model.donation.Book.approvedBook;
import static com.thoughtworks.lirenlab.domain.model.donation.Book.newBook;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class BookTest {

    @Test
    public void book_equals_by_isbn_and_book_status() throws Exception {
        assertThat(newBook("ISBN1"), is(newBook(("ISBN1"))));
    }

    @Test
    public void book_not_equal_when_isbn_not_equal() throws Exception {
        assertThat(newBook(("ISBN2")), not(newBook(("ISBN1"))));
    }

    @Test
    public void book_not_equal_when_status_not_equal() throws Exception {
        assertThat(newBook(("ISBN1")), not(approvedBook(("ISBN1"))));
    }
}
