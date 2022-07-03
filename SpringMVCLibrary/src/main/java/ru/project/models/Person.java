package ru.project.models;
import javax.validation.constraints.*;

public class Person {

    private int person_id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "You name should be between 2 and 100 characters")
    private String name;

    @Min(value = 6, message = "Age should be greater than 6")
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
