package ru.project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.project.models.Book;
import ru.project.models.Person;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> indexBook() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book showBook(int book_id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id = ?", new Object[]{book_id},
                        new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void saveBook(Book book) {
        jdbcTemplate.update("INSERT INTO Book(book_name, auhtor_name, publishing_year) VALUES (?, ?, ?)",
                book.getBook_name(), book.getAuhtor_name(), book.getPublishing_year());
    }

    public void updateBook(int book_id, Book updateBook) {
        jdbcTemplate.update("UPDATE Book SET book_name =?, auhtor_name=?, publishing_year=? WHERE book_id =?",
                updateBook.getBook_name(), updateBook.getAuhtor_name(), updateBook.getPublishing_year(), book_id);
    }

    public void deleteBook(int book_id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id = ?", book_id);
    }

    public Optional<Person> getBookPersonId(int book_id) {
        return jdbcTemplate.query("SELECT p.name, p.age  FROM Book b JOIN Person p ON b.person_id = p.person_id " +
                        "WHERE b.book_id = ?", new Object[]{book_id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void release(int book_id) {
        jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE book_id=?", book_id);
    }

    public void assign(int person_id, Person selectPerson) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", selectPerson.getPerson_id(), person_id);
    }
}
