package ru.project.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {

    private int book_id;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 200, message = "Минимальное длина 2 максимальная 200 букв")
    private String book_name;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 100, message = "Минимальная длина 2 максимальная 100 букв")
    private String auhtor_name;

    @Min(value = 1500, message = "Год издательства должен быть, больше 1500 года")
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
