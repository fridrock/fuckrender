package ru.fridrock.prac5;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
  @Operation(summary = "Get all persons")
  public List<Person> getAllPersons() {
    return personRepository.findAll();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get person by id")
  public ResponseEntity<Person> getPersonById(@Parameter(description = "Person id", required = true) @PathVariable Long id) {
    Optional<Person> person = personRepository.findById(id);
    return person.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  @Operation(summary = "Create person")
  public ResponseEntity<Person> createPerson(@Parameter(description="Person details", required= true)@RequestBody Person person) {
    Person savedPerson = personRepository.save(person);
    return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  @Operation(summary = "Update person")
  public ResponseEntity<Person> updatePerson(
      @Parameter(description="Person id", required = true) @PathVariable Long id,
      @Parameter(description="Person details", required = true) @RequestBody Person personDetails) {
    return personRepository.findById(id).map(person -> {
      person.setFirstName(personDetails.getFirstName());
      person.setLastName(personDetails.getLastName());
      person.setBirthYear(personDetails.getBirthYear());
      Person updatedPerson = personRepository.save(person);
      return ResponseEntity.ok(updatedPerson);
    }).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete person")
  public ResponseEntity<Void> deletePerson(
      @Parameter(description = "Person id", required = true) @PathVariable Long id) {
    return personRepository.findById(id).map(person -> {
      personRepository.delete(person);
      return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }).orElseGet(() -> ResponseEntity.notFound().build());
  }
}

