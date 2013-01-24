package com.thoughtworks.lirenlab.domain.model.donation;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.thoughtworks.lirenlab.domain.model.device.DeviceId;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Iterables.any;
import static com.google.common.collect.Lists.transform;
import static com.thoughtworks.lirenlab.domain.model.donation.DonationStatus.APPROVED;
import static com.thoughtworks.lirenlab.domain.model.donation.DonationStatus.REJECTED;
import static com.thoughtworks.lirenlab.domain.model.donation.PostSpecification.emptySpecification;

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

    @Embedded
    @AttributeOverride(name = "specification", column = @Column(name = "post_spec"))
    private PostSpecification postSpecification;


    private Donation(DeviceId deviceId, List<Book> books) {
        this.deviceId = deviceId;
        this.books = Lists.newArrayList(books);
        this.createdDate = new Date();
        this.updatedDate = createdDate;
        this.status = DonationStatus.NEW;
        this.postSpecification = emptySpecification();
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

    public void updatePostSpecification(PostSpecification postSpecification) {
        this.postSpecification = postSpecification;
    }

    public void confirm() {
        checkState(!postSpecification().isEmpty(), "post specifications should not be empty.");
        if (any(books, isApprovedBook())) {
            this.status = APPROVED;
            return;
        }
        this.status = REJECTED;
    }

    private Predicate<Book> isApprovedBook() {
        return new Predicate<Book>() {
            @Override
            public boolean apply(Book book) {
                return book.status().equals(BookStatus.APPROVED);
            }
        };
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

    public PostSpecification postSpecification() {
        return this.postSpecification;
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
