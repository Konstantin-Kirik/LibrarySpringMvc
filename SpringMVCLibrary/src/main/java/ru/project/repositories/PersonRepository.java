package ru.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
