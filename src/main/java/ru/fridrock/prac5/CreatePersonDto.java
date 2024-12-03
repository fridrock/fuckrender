package ru.fridrock.prac5;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreatePersonDto {
  private String firstName;
  private String lastName;
  private Integer birthYear;
}
