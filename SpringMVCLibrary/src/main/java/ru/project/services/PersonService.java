package ru.project.services;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.models.Book;
import ru.project.models.Person;
import ru.project.repositories.PersonRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = personRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void upDate(int id, Person updatePerson) {
        updatePerson.setPerson_id(id);
        personRepository.save(updatePerson);
    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
    }

    public List<Book> getBooksPersonId(int id) {
        Optional<Person> foundPerson = personRepository.findById(id);

        if (foundPerson.isPresent()) {
           Hibernate.initialize(foundPerson.get().getBooks());

           foundPerson.get().getBooks().forEach(book -> {
               long differenceInMilli = Math.abs(book.getTakenBook().getTime() - new Date().getTime());
               if (differenceInMilli > 864000000){
                   book.setTimeIsOver(true);
               }

           });
            return foundPerson.get().getBooks();
        } else {
            return Collections.emptyList();
        }
    }
}
