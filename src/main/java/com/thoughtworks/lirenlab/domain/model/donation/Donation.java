package com.thoughtworks.lirenlab.domain.model.donation;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.thoughtworks.lirenlab.domain.model.device.DeviceId;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "donations")
@Access(AccessType.FIELD)

public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private DonationStatus status;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "books", joinColumns = @JoinColumn(name = "donation_id"))
    private List<Book> books;

    @Column
    @AttributeOverride(name = "value", column = @Column(name = "device_id"))
    private DeviceId deviceId;

    @Column(name = "created_date")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "updated_date")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedDate;


    public Donation(DeviceId deviceId, List<Book> books) {
        this.deviceId = deviceId;
        this.books = Lists.newArrayList(books);
        this.updatedDate = new Date();
        this.createdDate = new Date();
        this.status = DonationStatus.NEW;
    }


    /**
     * Required By Hibernate
     */
    public Donation() {
    }

    public DonationId id() {
        Preconditions.checkState(this.id != null,
                "Id has not been set. Donation Id is generated using database auto incremental.");
        return new DonationId(this.id.toString());
    }

    public DonationStatus status() {
        return status;
    }

    public DeviceId deviceId() {
        return deviceId;
    }

    public List<Book> books() {
        return ImmutableList.copyOf(books);
    }

    public Date createdDate() {
        return createdDate;
    }


    public Date updatedDate() {
        return updatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Donation donation = (Donation) o;

        if (!id.equals(donation.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
