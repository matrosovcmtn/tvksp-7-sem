package ru.matrosov.TVKSP_3prac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.matrosov.TVKSP_3prac.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
