package com.sekcja3.students.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@SequenceGenerator(name = "seqIdGen", initialValue = 200000, allocationSize = 1)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seqIdGen")
    private Long id;

    @NotNull(message = "Name cannot be null")
    private String firstName;

    @NotEmpty(message = "last name can't be empty")
    private String lastName;

    @NotBlank
    @Email
    @Column(unique = true) // validacja uniklanosci maila
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
