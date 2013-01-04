package com.thoughtworks.lirenlab.infrastructure.persistence.hibernate;

import com.thoughtworks.lirenlab.domain.model.donation.Donation;
import com.thoughtworks.lirenlab.domain.model.donation.DonationId;
import com.thoughtworks.lirenlab.domain.model.donation.DonationRepository;
import com.thoughtworks.lirenlab.domain.model.donation.DonationStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DonationRepositoryHibernate extends HibernateRepository implements DonationRepository {

    @Override
    public DonationId save(Donation donation) {
        currentSession().saveOrUpdate(donation);
        return donation.id();
    }

    @Override
    public List<Donation> newDonations() {
        return currentSession()
                .createQuery("from Donation d where d.status = :status order by d.createdDate asc")
                .setParameter("status", DonationStatus.NEW).list();
    }


}