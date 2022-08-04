package ru.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.project.models.Book;
import ru.project.models.Person;
import ru.project.services.BookService;
import ru.project.services.PersonService;

import javax.validation.Valid;


@Controller
@RequestMapping("/library")
public class BookController {

    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping()
    public String indexBook(Model model,
                            @RequestParam(value = "page", required = false) Integer page,
                            @RequestParam(value = "books_per_page", required = false) Integer booksPerPage) {

        model.addAttribute("library", bookService.findWithPagination(page, booksPerPage));

        return "library/index_book";
    }

    @GetMapping("/{book_id}")
    public String showBook(@PathVariable("book_id") int book_id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookService.findOne(book_id));

        Person bookReader = bookService.getBookPersonId(book_id);

        if (bookReader != null)
            model.addAttribute("location", bookReader);
        else
            model.addAttribute("people", personService.findAll());

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
        bookService.save(book);
        return "redirect:/library";
    }

    @GetMapping("/{book_id}/edit_book")
    public String editBook(Model model, @PathVariable("book_id") int book_id) {
        model.addAttribute("book", bookService.findOne(book_id));
        return "library/edit_book";
    }

    @PatchMapping("/{book_id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                             @PathVariable("book_id") int book_id) {

        if (bindingResult.hasErrors()) {
            return "library/edit_book";
        }
        bookService.upDate(book_id, book);
        return "redirect:/library";
    }

    @DeleteMapping("/{book_id}")
    public String deleteBook(@PathVariable("book_id") int book_id) {
        bookService.delete(book_id);
        return "redirect:/library";
    }

    @PatchMapping("/{book_id}/release")
    public String release(@PathVariable("book_id") int book_id) {
        bookService.release(book_id);
        return "redirect:/library/" + book_id;
    }

    @PatchMapping("/{person_id}/assign")
    public String assign(@PathVariable("person_id") int book_id, @ModelAttribute("person") Person assignPerson) {
        bookService.assign(book_id, assignPerson);
        return "redirect:/library/" + book_id;
    }
}
