package com.example.application.dto.student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentUpdateDTO {
    private String email;
    private String password;
    private String name;
    private String lastName;
    private Integer year;
}
