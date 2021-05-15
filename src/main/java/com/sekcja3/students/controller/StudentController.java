package com.sekcja3.students.controller;


import com.sekcja3.students.model.Student;
import com.sekcja3.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController // zapytanie w jsonie :)
@RequestMapping("/students") // /students początek każdego url w tym controlerze
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public List<Student> getStudents(@RequestParam(required = false) Student.Status status) {
        return studentService.getStudents(status);
    }

    // podawany Json w metodzie POST zostanie z automatu zmapowany na obiekt Javowy Student
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Student addStudent(@RequestBody @Valid Student student) {
        return studentService.addStudent(student);
    }


    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }


    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
       studentService.deleteStudent(id);
    }


    @PutMapping("/{id}")
    public Student putStudent(@PathVariable Long id, @Valid @RequestBody Student student) {
        return studentService.putStudent(id,student);
    }

//    // PUT 2 opcja
//    // jeśli obiekt istnieje to go zmodyfikuje, jeśli nie to go stworzy z tym że response code będzie inny
//    @PutMapping
//    public ResponseEntity<Student> putStudent(@Valid @RequestBody Student student){
//        return ResponseEntity.ok().body(studentRepository.save(student));
//    }


    @PatchMapping("/{id}")
    public Student patchStudent(@PathVariable Long id, @RequestBody Student student){
        return studentService.patchStudent(id, student);
    }


}
