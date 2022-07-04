package ru.project.models;
import javax.validation.constraints.*;

public class Person {

    private int person_id;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 100, message = "Минимальное длина 2 максимальная 100 букв")
    private String name;

    @Min(value = 1900, message = "Год рождение не может быть старше 1900 года рождения")
    private int age;

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
}
