package ru.project.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {

    private int book_id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 200, message = "You name should be between 2 and 200 characters")
    private String book_name;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "You name should be between 2 and 100 characters")
    private String auhtor_name;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 1500, max = 2022, message = "You name should be between 1500 and 2022 characters")
    private int publishing_year;

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
}
