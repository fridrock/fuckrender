package ru.fridrock.prac5;

import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//@Entity
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "persons")
//@ToString
public class Person {

//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

//  @Column(name = "first_name", nullable = false)
  private String firstName;

//  @Column(name = "last_name", nullable = false)
  private String lastName;

//  @Column(name = "birth_year", nullable = false)
  private Integer birthYear;



}
