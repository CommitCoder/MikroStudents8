package com.sekcja3.students.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Student {

    // more validations on pdf from section 3 @@@@@@@@@@@@@@@@


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // walidacja podczas zapisu do bazy danych na encji,
    // samo not null 500, z adnotacją Valid już 400 bład po stronei klienta,
    //message zmiana domyślnej wiadomości w kodzie błedu 400
//    @NotNull(message = "Field can not be empty")
    @NotNull(message = "Name cannot be null") // must be present in json but can be empty
    private String firstName;

    @NotEmpty(message = "last name can't be empty") // at least white space
    private String lastName;
//    @NotBlank any character
    // Pattern(regexp = "[1]a]") customowy regexp
    @Email // email validation
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
