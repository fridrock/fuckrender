package ru.fridrock.prac5;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

  private Long id;

  private String firstName;

  private String lastName;

  private Integer birthYear;


}
