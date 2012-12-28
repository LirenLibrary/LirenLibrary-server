package com.thoughtworks.lirenlab.interfaces.donation.web;

import com.thoughtworks.lirenlab.interfaces.donation.facade.DonationServiceFacade;
import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.DonationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/donations")
public class DonationController {

    private DonationServiceFacade donationServiceFacade;

    @Autowired
    public DonationController(final DonationServiceFacade donationServiceFacade) {
        this.donationServiceFacade = donationServiceFacade;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        List<DonationDTO> newDonations = donationServiceFacade.getNewDonations();
        model.addAttribute("donations", newDonations);
        return "/donations/index";
    }
}
