package com.example.application.crud.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column (name = "email") @Email
    private String email;
    @Column (name = "password") @NotEmpty
    private String password;
    @Column (name = "firstName") @NotEmpty
    private String firstName;
    @Column (name = "lastName") @NotEmpty
    private String lastName;

    // role
    @Column (name = "role") @NotEmpty
    private String role;

    // student data
    @Column (name = "year_of_study")
    private Integer year_of_study;

    // teacher data
    @Column (name = "position")
    private String position;

    public User() {}

    // student constructor
    public User(@Valid  Integer id, String email, String password, String firstName,
                String lastName, Integer year_of_study) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.year_of_study = year_of_study;
    }


    // teacher constructor
    public User(@Valid  Integer id, String email, String password, String firstName,
                   String lastName, String position) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
    }

    //student relations
    @ManyToMany
    @JoinTable(name="student_groups",
            joinColumns = @JoinColumn(name="student_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="group_id", referencedColumnName="id")
    )
    private Set<Group> student_groups;

    public Set<Group> getStudentGroups() {
        return student_groups;
    }

    public void setStudentGroups(Set<Group> student_groups) {
        this.student_groups = student_groups;
    }

    @ManyToMany
    @JoinTable(name="student_subjects",
            joinColumns = @JoinColumn(name="student_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="subject_id", referencedColumnName="id")
    )
    private Set<Subject> student_subjects;

    public Set<Subject> getStudentSubjects() {
        return student_subjects;
    }

    public void setStudentSubjects(Set<Subject> subjects) {
        this.student_subjects = subjects;
    }


    // teacher relations
    @ManyToMany
    @JoinTable(name="teacher_subjects",
            joinColumns = @JoinColumn(name="teacher_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="subject_id", referencedColumnName="id")
    )
    private Set<Subject> teacher_subjects;

    public Set<Subject> getTeacherSubjects() {
        return teacher_subjects;
    }

    public void setTeacherSubjects(Set<Subject> subjects) {
        this.teacher_subjects = teacher_subjects;
    }
}