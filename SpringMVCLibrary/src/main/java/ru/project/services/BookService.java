package ru.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.models.Book;
import ru.project.models.Person;
import ru.project.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(boolean sortByYear) {
        if(sortByYear){
            return bookRepository.findAll(Sort.by("publishingYear"));
        }else {
            return bookRepository.findAll();
        }
    }

    public List<Book> findPagination(Integer page, Integer booksPerPage) {
            return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public Book findOne(int id) {
        Optional<Book> foundBook = bookRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void upDate(int id, Book updateBook) {
        Book bookUpDatePerson = bookRepository.findById(id).get();
        updateBook.setBookId(id);
        updateBook.setReader(bookUpDatePerson.getReader());

        bookRepository.save(updateBook);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public Person getBookPersonId(int id) {
        return bookRepository.findById(id).map(book -> book.getReader()).orElse(null);
    }

    @Transactional
    public void release(int id) {
        bookRepository.findById(id).ifPresent(b -> b.setReader(null));
    }

    @Transactional
    public void assign(int id, Person assignPerson) {
        bookRepository.findById(id).ifPresent(b -> b.setReader(assignPerson));
    }

    public List<Book> searchByTitle(String query) {
        return bookRepository.findByBookNameStartingWith(query);
    }

}
