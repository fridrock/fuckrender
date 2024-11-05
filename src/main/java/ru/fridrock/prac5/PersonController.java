package ru.fridrock.prac5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

  private final PersonRepository personRepository;

  @Autowired
  public PersonController(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @GetMapping
  public List<Person> getAllPersons() {
    return personRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
    Optional<Person> person = personRepository.findById(id);
    return person.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Person> createPerson(@RequestBody Person person) {
    Person savedPerson = personRepository.save(person);
    return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person personDetails) {
    return personRepository.findById(id).map(person -> {
      person.setFirstName(personDetails.getFirstName());
      person.setLastName(personDetails.getLastName());
      person.setBirthYear(personDetails.getBirthYear());
      Person updatedPerson = personRepository.save(person);
      return ResponseEntity.ok(updatedPerson);
    }).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
    return personRepository.findById(id).map(person -> {
      personRepository.delete(person);
      return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }).orElseGet(() -> ResponseEntity.notFound().build());
  }
}

