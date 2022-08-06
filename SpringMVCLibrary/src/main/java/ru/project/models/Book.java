package ru.project.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 200, message = "Минимальное длина 2 максимальная 200 букв")
    @Column(name = "book_name")
    private String bookName;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 100, message = "Минимальная длина 2 максимальная 100 букв")
    @Column(name = "auhtor_name")
    private String authorName;

    @Min(value = 1500, message = "Год издательства должен быть, больше 1500 года")
    @Column(name = "publishing_year")
    private int publishingYear;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person reader;

    @Column(name = "taken_book")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenBook;

    @Transient
    private boolean timeIsOver;

    public Book() {
    }

    public Book(int bookId, String bookName, String authorName, int publishingYear) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.publishingYear = publishingYear;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    public Person getReader() {
        return reader;
    }

    public void setReader(Person reader) {
        this.reader = reader;
    }

    public Date getTakenBook() {
        return takenBook;
    }

    public void setTakenBook(Date takenBook) {
        this.takenBook = takenBook;
    }

    public boolean isTimeIsOver() {
        return timeIsOver;
    }

    public void setTimeIsOver(boolean timeIsOver) {
        this.timeIsOver = timeIsOver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId && publishingYear == book.publishingYear && Objects.equals(bookName, book.bookName) && Objects.equals(authorName, book.authorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, bookName, authorName, publishingYear);
    }
}
