package ru.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
