package com.thoughtworks.lirenlab.interfaces.donation.mobile;

import org.codehaus.jackson.annotate.JsonProperty;

public class NewDonationResponse {

    @JsonProperty("donation_id")
    private String donationId;

    @JsonProperty("link")
    private String link;

    public String getDonationId() {
        return donationId;
    }

    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
