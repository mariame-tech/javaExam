package com.groupeisi.examen_java.students.repository;

import com.groupeisi.examen_java.students.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
    Optional<StudentEntity> findByLastName(String lastName);
    Optional<StudentEntity> findByEmailPro(String emailPro);
    Optional<StudentEntity>findById(Long id);
    Optional<StudentEntity>findByRegistrationNu(String registrationNu);

}
