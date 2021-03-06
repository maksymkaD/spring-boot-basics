package com.example.application.dal.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Validated
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "email") @Email
    private String email;

    @Column (name = "password") @NotEmpty
    private String password;

    @Column (name = "name") @NotEmpty
    private String name;

    @Column (name = "lastName") @NotEmpty
    private String lastName;

    /*
        admin, teacher, student
     */
    @Column (name = "role") @NotEmpty
    private String role;

    public User() {}

    /*
        Admin constructor
    */
    public User(String email, String password, String name, String lastName, String role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.role = role;
    }

    /*
        Student constructor
     */
    @Column (name = "year")
    private Integer year;

    public User(String email, String password, String name, String lastName, String role, Integer year) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.role = role;
        this.year = year;
    }

    @ManyToMany
    @JoinTable(
            name = "student_subjects",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    Set<Subject> studentSubjects;

    /*
        Teacher constructor
     */
    @Column (name = "position")
    private String position;

    public User(String email, String password, String name, String lastName, String role, String position) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.role = role;
        this.position = position;
    }

    /*
        Shared subject table
     */
    @ManyToMany
    @JoinTable(
            name = "user_subjects",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    Set<Subject> userSubjects;
}
