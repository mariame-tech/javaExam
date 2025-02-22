package com.groupeisi.examen_java.students.services;

import com.groupeisi.examen_java.students.dto.requests.StudentDtoRequest;
import com.groupeisi.examen_java.students.dto.responses.StudentDtoResponse;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Optional<StudentDtoResponse>saveStudent(StudentDtoRequest studentDto);
    Optional<List<StudentDtoResponse>> getAllStudents();
    Optional<StudentDtoResponse>getStudentById(Long id);
    boolean deleteStudent(Long id);
    Optional<StudentDtoResponse>updateStudent(Long id, StudentDtoRequest studentDtoRequest);

}
