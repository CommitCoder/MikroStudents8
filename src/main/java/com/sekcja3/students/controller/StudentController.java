package com.sekcja3.students.controller;


import com.sekcja3.students.model.Student;
import com.sekcja3.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController // zapytanie w jsonie :)
@RequestMapping("/students") // /students początek każdego url w tym controlerze
public class StudentController {

    private StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    // podawany Json w metodzie POST zostanie z automatu zmapowany na obiekt Javowy Student
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Student addStudent(@RequestBody @Valid Student student) {
        return studentRepository.save(student);
    }

    // orElseGet() lepszy dla tego casa

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        return studentOptional.map(student -> ResponseEntity.ok(student))
                .orElseGet(() -> ResponseEntity.notFound().build()); // wykona się tylko wtedy gdy optional jest pusty
    }

//       @GetMapping("/{id}")
//    public ResponseEntity<Student> getStudent(@PathVariable  Long id){
//       return studentRepository.findById(id)
//               .map(student -> ResponseEntity.ok(student))
//               .orElse(ResponseEntity.notFound().build());  // orElse wykonuje się zawsze nawet gdy .map() coś zwraca
//    }


    // post edycja


    //patch modyfikacja dane pola

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return studentRepository.findById(id)
                .map(student -> {
                    studentRepository.delete(student);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PUT edycja całego zasobu
    // PUT 1opcja

    @PutMapping("/{id}")
    public ResponseEntity<?> putStudent(@PathVariable Long id, @Valid @RequestBody Student student) {
        return studentRepository.findById(id)
                .map(studentFromDb ->{
                    studentFromDb.setFirstName(student.getFirstName());
                    studentFromDb.setLastName(student.getLastName());
                    studentFromDb.setEmail(student.getEmail());
                    return ResponseEntity.ok().body(studentRepository.save(studentFromDb));
                }).orElseGet(()->ResponseEntity.notFound().build());
    }


//    // PUT 2 opcja
//    // jeśli obiekt istnieje to go zmodyfikuje, jeśli nie to go stworzy z tym że response code będzie inny
//    @PutMapping
//    public ResponseEntity<Student> putStudent(@Valid @RequestBody Student student){
//        return ResponseEntity.ok().body(studentRepository.save(student));
//    }


    //patch
    @PatchMapping("/{id}")
    public ResponseEntity<?> patchStudent(@PathVariable Long id, @RequestBody Student student){
        return studentRepository.findById(id)
                .map(studentFromDb ->{
                    if (!StringUtils.isEmpty(student.getFirstName())) {
                        studentFromDb.setFirstName(student.getFirstName());
                    }
                    if (!StringUtils.isEmpty(student.getLastName())) {
                        studentFromDb.setLastName(student.getLastName());
                    }
                    return ResponseEntity.ok().body(studentRepository.save(studentFromDb));

                }).orElseGet(()-> ResponseEntity.notFound().build());

    }





























    // BUDNY KOD ale można popatrzeć na przykłady

//    @GetMapping("/lastname")
//    public List<Student> findStudent(@RequestParam String lastName, @RequestParam int numberOfPage){
//        Pageable pageable = PageRequest.of(numberOfPage, 2, Sort.by("firstName"));
//        return studentRepository.findByLastName(lastName, pageable);
//    }
//
//    @GetMapping("/find")
//    public List<Student> findStudent2(@RequestParam String lastName, @RequestParam String firstName){
//        return studentRepository.findByLastNameAndFirstNameIsNotLikeAllIgnoreCase(lastName, firstName);
//    }
//
//    @GetMapping("/marian")
//    public List<Student> findStudent3(){
//        return studentRepository.findStudentsWithNameMarian();
//    }





}
