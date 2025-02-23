package com.groupeisi.examen_java.students.controller;
import com.groupeisi.examen_java.students.dto.requests.StudentDtoRequest;
import com.groupeisi.examen_java.students.dto.responses.StudentDtoResponse;
import com.groupeisi.examen_java.students.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDtoResponse> saveStudent(@RequestBody StudentDtoRequest studentDto) {
        Optional<StudentDtoResponse> StudentDto1 = studentService.saveStudent(studentDto);
        return new ResponseEntity<>(StudentDto1.get(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDtoResponse> getStudentById(@PathVariable Long id) {
        Optional<StudentDtoResponse> productDto1 = studentService.getStudentById(id);
        return new ResponseEntity<>(productDto1.get(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StudentDtoResponse>> getAllStudents() {
        Optional<List<StudentDtoResponse>> students = studentService.getAllStudents();
        return new ResponseEntity<>(students.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDtoResponse> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentDtoRequest studentDto) {
        Optional<StudentDtoResponse> updatedStudent = studentService.updateStudent(id,studentDto);
        return new ResponseEntity<>(updatedStudent.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
