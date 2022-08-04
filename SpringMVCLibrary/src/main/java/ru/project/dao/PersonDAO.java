//package ru.project.dao;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//import ru.project.models.Book;
//import ru.project.models.Person;
//import java.util.List;
//
//@Component
//public class PersonDAO {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index() {
//        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Person show(int person_id) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id = ?", new Object[]{person_id},
//                        new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny().orElse(null);
//    }
//
//    public void save(Person person) {
//        jdbcTemplate.update("INSERT INTO Person(name, age) VALUES (?, ?)",
//                person.getName(), person.getAge());
//    }
//
//    public void update(int person_id, Person updatePerson) {
//        jdbcTemplate.update("UPDATE Person SET name=?, age=? WHERE person_id =?",
//                updatePerson.getName(), updatePerson.getAge(), person_id);
//    }
//
//    public void delete(int person_id) {
//        jdbcTemplate.update("DELETE FROM Person WHERE person_id = ?", person_id);
//    }
//
//
//    public List<Book> getBooksPersonId(int person_id) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id = ?", new Object[]{person_id},
//                new BeanPropertyRowMapper<>(Book.class));
//    }
//
//}
