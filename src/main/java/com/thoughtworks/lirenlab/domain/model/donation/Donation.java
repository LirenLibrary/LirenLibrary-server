package com.thoughtworks.lirenlab.domain.model.donation;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.thoughtworks.lirenlab.domain.model.device.DeviceId;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.transform;

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
    @OrderBy("isbn")
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


    private Donation(DeviceId deviceId, List<Book> books) {
        this.deviceId = deviceId;
        this.books = Lists.newArrayList(books);
        this.createdDate = new Date();
        this.updatedDate = createdDate;
        this.status = DonationStatus.NEW;
    }

    /**
     * Required By Hibernate
     */
    public Donation() {
    }

    public static Donation donation(DeviceId deviceId, List<Book> books) {
        return new Donation(deviceId, books);
    }

    public void approve(String isbn) {
        books = transform(books, updateStatusByIsbn(isbn, BookStatus.APPROVED));
    }

    public void reject(String isbn) {
        books = transform(books, updateStatusByIsbn(isbn, BookStatus.REJECTED));
    }

    private Function<Book, Book> updateStatusByIsbn(final String isbn,final BookStatus status) {
        return new Function<Book, Book>() {
            @Override
            public Book apply(Book book) {
                if (book.isbn().equals(isbn)) {
                    return new Book(isbn, book.title(), status);
                }
                return book;
            }
        };
    }

    public DonationId id() {
        checkState(this.id != null,
                "Id has not been set. Donation Id is generated using database auto incremental.");
        return DonationId.donationId(this.id.toString());
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
