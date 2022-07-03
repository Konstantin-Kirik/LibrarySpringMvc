package ru.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.project.dao.BookDAO;
import ru.project.dao.PersonDAO;
import ru.project.models.Book;
import ru.project.models.Person;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/library")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String indexBook(Model model) {
        model.addAttribute("library", bookDAO.indexBook());
        return "library/index_book";
    }

    @GetMapping("/{book_id}")
    public String showBook(@PathVariable("book_id") int book_id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.showBook(book_id));

        Optional<Person> personBook = bookDAO.getBookPersonId(book_id);

        if (personBook.isPresent())
            model.addAttribute("locate", personBook.get());
        else
            model.addAttribute("people", personDAO.index());

        return "library/show_book";
    }

    @GetMapping("/new_book")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "library/new_book";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "library/new_book";
        }
        bookDAO.saveBook(book);
        return "redirect:/library";
    }

    @GetMapping("/{book_id}/edit_book")
    public String editBook(Model model, @PathVariable("book_id") int book_id) {
        model.addAttribute("book", bookDAO.showBook(book_id));
        return "library/edit_book";
    }

    @PatchMapping("/{book_id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("book_id") int book_id) {

        if (bindingResult.hasErrors()) {
            return "library/edit_book";
        }
        bookDAO.updateBook(book_id, book);
        return "redirect:/library";
    }

    @DeleteMapping("/{book_id}")
    public String deleteBook(@PathVariable("book_id") int book_id) {
        bookDAO.deleteBook(book_id);
        return "redirect:/library";
    }

    @PatchMapping("/{book_id}/release")
    public String release(@PathVariable("book_id") int book_id) {
        bookDAO.release(book_id);
        return "redirect:/library/" + book_id;
    }

    @PatchMapping("/{book_id}/assign")
    public String assign(@PathVariable("book_id") int book_id, @ModelAttribute("person") Person selectPerson) {
        bookDAO.assign(book_id, selectPerson);
        return "redirect:/library/" + book_id;
    }
}
