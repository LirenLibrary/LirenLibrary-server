package com.thoughtworks.lirenlab.domain.model.donation;

import com.google.common.collect.Lists;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static com.thoughtworks.lirenlab.domain.model.device.DeviceId.deviceId;
import static com.thoughtworks.lirenlab.domain.model.donation.Book.approvedBook;
import static com.thoughtworks.lirenlab.domain.model.donation.Book.newBook;
import static com.thoughtworks.lirenlab.domain.model.donation.Book.rejectedBook;
import static com.thoughtworks.lirenlab.domain.model.donation.Donation.donation;
import static com.thoughtworks.lirenlab.domain.model.donation.DonationStatus.APPROVED;
import static com.thoughtworks.lirenlab.domain.model.donation.DonationStatus.REJECTED;
import static com.thoughtworks.lirenlab.domain.model.donation.PostSpecification.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class DonationTest {

    @Test
    public void donation_status_is_new_by_default() throws Exception {
        Donation donation = donation(deviceId("123"), newArrayList(newBook("isbn1234", "title")));
        assertThat(donation.status(), is(DonationStatus.NEW));
    }


    @Test
    public void book_status_is_approved_by_default() throws Exception {
        assertThat(newBook("isbn", "title").status(), is(BookStatus.APPROVED));
    }

    @Test
    public void book_status_changed_to_rejected_when_reject_a_new_book() throws Exception {
        Donation donation = donation(deviceId("12345"), newArrayList(newBook("isbn1234", "title")));
        donation.reject("isbn1234");
        assertThat(donation.books(), not(hasItem(newBook("isbn1234", "title"))));
        assertThat(donation.books(), hasItem(rejectedBook("isbn1234", "title")));
    }

    @Test
    public void book_status_changed_to_approved_when_approve_a_rejected_book() throws Exception {
        Donation donation = donation(deviceId("12345"), newArrayList(rejectedBook("isbn1234", "title")));
        donation.approve("isbn1234");
        assertThat(donation.books(), not(hasItem(rejectedBook("isbn1234", "title"))));
        assertThat(donation.books(), hasItem(approvedBook("isbn1234", "title")));
    }

    @Test
    public void book_status_changed_to_rejected_when_reject_a_approved_book() throws Exception {
        Donation donation = donation(deviceId("12345"), newArrayList(approvedBook("isbn1234", "title")));
        donation.reject("isbn1234");
        assertThat(donation.books(), not(hasItem(approvedBook("isbn1234", "title"))));
        assertThat(donation.books(), hasItem(rejectedBook("isbn1234", "title")));
    }

    @Test
    public void can_update_post_specification() throws Exception {
        Donation donation = donation(deviceId("12345"), Lists.<Book>newArrayList());
        donation.updatePostSpecification(postSpecification("Phone:1234\nAddress:There\n"));
        assertThat(donation.postSpecification(), is(postSpecification("Phone:1234\nAddress:There\n")));
    }

    @Test
    public void post_specification_is_empty_by_default() throws Exception {
        Donation donation = donation(deviceId("12345"), Lists.<Book>newArrayList());
        assertThat(donation.postSpecification(), is(emptySpecification()));
    }

    @Test
    public void confirm_donation_change_the_status_to_approved_if_contains_at_least_one_approved_book() throws Exception {
        Donation donation = donation(deviceId("12345"), newArrayList(
                approvedBook("isbn", "title"),
                rejectedBook("isbn1", "title2")));
        donation.updatePostSpecification(postSpecification("some address"));
        donation.confirm();
        assertThat(donation.status(), is(APPROVED));
    }

    @Test
    public void confirm_donation_change_the_status_to_rejected_if_contains_no_approved_book() throws Exception {
        Donation donation = donation(deviceId("12345"), Lists.<Book>newArrayList(
                rejectedBook("isbn", "title"),
                rejectedBook("isbn1", "title1")));
        donation.updatePostSpecification(postSpecification("some address"));
        donation.confirm();
        assertThat(donation.status(), is(REJECTED));
    }

    @Test(expected = IllegalStateException.class)
    public void confirm_donation_is_not_allowed_if_post_specification_is_not_specified() throws Exception {
        Donation donation = donation(deviceId("12345"), Lists.<Book>newArrayList(
                rejectedBook("isbn", "title"),
                rejectedBook("isbn1", "title1")));
        donation.confirm();
    }
}
