package com.sekcja3.students.repository;

import com.sekcja3.students.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository  //  extends JpaRepository  ->  tak naprawdę @Repository nie jest potrzebne ale dla czytelności
public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);

    List<Student> findAllByStatus(Student.Status status);


    // BRUDNY KOD ale można popatrzeć na przykłady


//     ctrl + spacja dużo fajnych podpowiedzi do nazw metod z JPA Repo
//    List<Student> findByLastName(String lastName, Pageable pageable);

//    List<Student> findByLastNameAndFirstNameIsNotLikeAllIgnoreCase(String lastName, String firstName);
//
//    @Query("SELECT s from Student s where s.firstName = 'Marian' ")
//    List<Student> findStudentsWithNameMarian();

}
