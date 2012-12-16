package com.thoughtworks.lirenlab.domain.model.donation;

import java.util.List;

public class Donation {
    private String donationId;
    private String donationStatus;
    private String postAddress;
    private String postReceiver;
    private String postCode;
    private String postReceiverMobile;
    private List<Book> bookList;

    public String getDonationId() {
        return donationId;
    }

    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }

    public String getDonationStatus() {
        return donationStatus;
    }

    public void setDonationStatus(String donationStatus) {
        this.donationStatus = donationStatus;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public String getPostReceiver() {
        return postReceiver;
    }

    public void setPostReceiver(String postReceiver) {
        this.postReceiver = postReceiver;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostReceiverMobile() {
        return postReceiverMobile;
    }

    public void setPostReceiverMobile(String postReceiverMobile) {
        this.postReceiverMobile = postReceiverMobile;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
