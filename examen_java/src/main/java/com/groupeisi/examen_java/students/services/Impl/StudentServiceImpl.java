package com.groupeisi.examen_java.students.services.Impl;

import com.groupeisi.examen_java.students.dto.requests.StudentDtoRequest;
import com.groupeisi.examen_java.students.dto.responses.StudentDtoResponse;
import com.groupeisi.examen_java.students.entities.StudentEntity;
import com.groupeisi.examen_java.students.mapper.StudentMapper;
import com.groupeisi.examen_java.students.repository.StudentRepository;
import com.groupeisi.examen_java.students.services.StudentService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    private final MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    // Create a student
    @Override
    @Transactional
    public Optional<StudentDtoResponse> saveStudent(StudentDtoRequest studentDto) {
        // Check existing student by RegistrationNu
        if (studentRepository.findByRegistrationNu(studentDto.getRegistrationNu()).isPresent()) {
            throw new EntityExistsException(messageSource.getMessage("student.exists", new Object[]{studentDto.getRegistrationNu()}, Locale.getDefault()));
        }

        // Convert a DTO in entity et save
        StudentEntity student = studentMapper.toStudentEntity(studentDto);
        logger.info("Reference: {}", student.getRegistrationNu());
        StudentEntity savedStudent = studentRepository.save(student);

        // Return DTO response
        return Optional.of(studentMapper.toStudentDtoResponse(savedStudent));
    }



    @Override
    public Optional<StudentDtoResponse> getStudentById(Long id) {
        // Retrieve student by ID
        return studentRepository.findById(id)
                .map(student -> Optional.of(studentMapper.toStudentDtoResponse(student)))
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("student.notfound", new Object[]{id}, Locale.getDefault())));
    }



    @Override
    public Optional<List<StudentDtoResponse>> getAllStudents() {
        // Récupérer tous les étudiants
        List<StudentEntity> students = studentRepository.findAll();
        return Optional.of(studentMapper.toStudentDtoResponseList(students));
    }


    @Override
    public boolean deleteStudent(Long id) {
        if (studentRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(messageSource.getMessage("student.notfound", new Object[]{id}, Locale.getDefault()));
        }
        studentRepository.deleteById(id);
        return true;
    }



    @Override
    public Optional<StudentDtoResponse> updateStudent(Long id, StudentDtoRequest studentDto) {
        // Vérifier si l'étudiant existe avant de le mettre à jour
        return studentRepository.findById(id)
                .map(student -> {
                    // Mettre à jour les champs de l'étudiant
                    student.setFirstName(studentDto.getFirstName());
                    student.setLastName(studentDto.getLastName());
                    student.setEmailPro(studentDto.getEmailPro());
                    student.setEmailPerso(studentDto.getEmailPerso());
                    student.setPhoneNumber(studentDto.getPhoneNumber());
                    student.setAddress(studentDto.getAddress());
                    student.setArchive(studentDto.getArchive());
                    student.setRegistrationNu(studentDto.getRegistrationNu());

                    // Sauvegarder les modifications
                    StudentEntity updatedStudent = studentRepository.save(student);
                    return Optional.of(studentMapper.toStudentDtoResponse(updatedStudent));
                })
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("student.notfound", new Object[]{id}, Locale.getDefault())));
    }


}
