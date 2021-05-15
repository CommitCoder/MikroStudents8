package com.sekcja3.students.service;

import com.sekcja3.students.model.Student;

import java.util.List;

public interface StudentService {

    Student getStudent(Long id);
    List<Student> getStudents(Student.Status status);
    Student addStudent(Student student);
    void deleteStudent(Long id);
    Student putStudent(Long id, Student student);
    Student patchStudent(Long id, Student student);



}
