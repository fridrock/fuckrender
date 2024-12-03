package ru.fridrock.prac5;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
@Slf4j
public class PersonController {

  //  private final PersonRepository personRepository;
  private ArrayList<Person> persons = new ArrayList<>();


  @GetMapping
  @Operation(summary = "Get all persons")
  public List<Person> getAllPersons() {
    return persons;
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get person by id")
  public ResponseEntity<Person> getPersonById(@Parameter(description = "Person id", required = true) @PathVariable Long id) {
    Person personFound = null;
    for (Person p : persons) {
      if (p.getId() == id) {
        personFound = p;
        break;
      }
    }
    return ResponseEntity.ok(personFound);
  }

  @PostMapping
  @Operation(summary = "Create person")
  public ResponseEntity<Person> createPerson(
      @Parameter(description = "Person details", required = true) @RequestBody CreatePersonDto dto) {
    log.info("POHUY");
    System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    log.info(dto.toString());
    Person person = new Person();
    person.setBirthYear(dto.getBirthYear());
    person.setFirstName(dto.getFirstName());
    person.setLastName(dto.getLastName());
    Long id = persons.size() == 0 ? 0 : persons.get(persons.size() - 1).getId() + 1;
    person.setId(id);
    persons.add(person);
    log.info(person.toString());
    return new ResponseEntity<>(person, HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  @Operation(summary = "Update person")
  public ResponseEntity<Person> updatePerson(
      @Parameter(description = "Person id", required = true) @PathVariable Long id,
      @Parameter(description = "Person details", required = true) @RequestBody Person personDetails) {
    Person foundPerson = null;
    for (Person p : persons) {
      if (p.getId() == id) {
        p.setLastName(personDetails.getLastName());
        p.setFirstName(personDetails.getFirstName());
        p.setBirthYear(personDetails.getBirthYear());
        foundPerson = p;
        break;
      }
    }

    return ResponseEntity.ok(foundPerson);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete person")
  public ResponseEntity<Void> deletePerson(
      @Parameter(description = "Person id", required = true) @PathVariable Long id) {
    Person foundPerson = null;
    for (Person p : persons) {
      if (p.getId() == id) {
        foundPerson = p;
        break;
      }
    }
    persons.remove(foundPerson);

    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//
//    return personRepository.findById(id).map(person -> {
//      personRepository.delete(person);
//      return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//    }).orElseGet(() -> ResponseEntity.notFound().build());
  }
}

