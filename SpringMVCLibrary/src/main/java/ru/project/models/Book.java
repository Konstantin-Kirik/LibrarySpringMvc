package ru.project.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 200, message = "Минимальное длина 2 максимальная 200 букв")
    @Column(name = "book_name")
    private String book_name;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 100, message = "Минимальная длина 2 максимальная 100 букв")
    @Column(name = "auhtor_name")
    private String auhtor_name;

    @Min(value = 1500, message = "Год издательства должен быть, больше 1500 года")
    @Column(name = "publishing_year")
    private int publishing_year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person reader;

    public Book() {
    }

    public Book(int book_id, String book_name, String auhtor_name, int publishing_year) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.auhtor_name = auhtor_name;
        this.publishing_year = publishing_year;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuhtor_name() {
        return auhtor_name;
    }

    public void setAuhtor_name(String auhtor_name) {
        this.auhtor_name = auhtor_name;
    }

    public int getPublishing_year() {
        return publishing_year;
    }

    public void setPublishing_year(int publishing_year) {
        this.publishing_year = publishing_year;
    }

    public Person getReader() {
        return reader;
    }

    public void setReader(Person reader) {
        this.reader = reader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return book_id == book.book_id && publishing_year == book.publishing_year && Objects.equals(book_name, book.book_name) && Objects.equals(auhtor_name, book.auhtor_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_id, book_name, auhtor_name, publishing_year);
    }
}
