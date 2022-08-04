package ru.project.models;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;


@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int person_id;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 100, message = "Минимальное длина 2 максимальная 100 букв")
    @Column(name = "name")
    private String name;

    @Min(value = 1900, message = "Год рождение не может быть старше 1900 года рождения")
    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "reader")
    private List<Book> books;

    public Person(){
    }

    public Person(int person_id, String name, int age) {
        this.person_id = person_id;
        this.name = name;
        this.age = age;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
