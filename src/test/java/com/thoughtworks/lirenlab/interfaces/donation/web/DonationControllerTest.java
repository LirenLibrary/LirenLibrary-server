package com.thoughtworks.lirenlab.interfaces.donation.web;

import com.thoughtworks.lirenlab.domain.model.donation.Donation;
import com.thoughtworks.lirenlab.interfaces.donation.facade.internal.assembler.DonationDTOAssembler;
import com.thoughtworks.lirenlab.utils.YamlLoader;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:context.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class DonationControllerTest {


    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private SessionFactory sessionFactory;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void should_show_new_donations_view() throws Exception {

        Donation donation = YamlLoader.load("classpath:yaml/donation/id_1.yaml", Donation.class);
        sessionFactory.getCurrentSession().save(donation);

        sessionFactory.getCurrentSession().flush();

        mockMvc.perform(get("/donations/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("/donations/index"))
                .andExpect(model().attributeExists("donations"))
                .andExpect(model().attribute("donations", hasItems(new DonationDTOAssembler().toDTO(donation))));
    }

}
