package ru.fridrock.prac5;


public class Person {

  private Long id;

  private String firstName;

  private String lastName;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getBirthYear() {
    return birthYear;
  }

  public void setBirthYear(Integer birthYear) {
    this.birthYear = birthYear;
  }

  private Integer birthYear;

  public Person(){

  }
  public Person(Long id, String fistName, String lastName, Integer birthYear){
    this.id = id;
    this.firstName = fistName;
    this.lastName = lastName;
    this.birthYear = birthYear;
  }

}
