package com.groupeisi.examen_java.students.mapper;

import com.groupeisi.examen_java.students.dto.requests.StudentDtoRequest;
import com.groupeisi.examen_java.students.dto.responses.StudentDtoResponse;
import com.groupeisi.examen_java.students.entities.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentEntity toStudentEntity(StudentDtoRequest studentDto);
    StudentDtoResponse toStudentDtoResponse(StudentEntity studentEntity);
    List<StudentDtoResponse> toStudentDtoResponseList(List<StudentEntity> studentEntityList) ;
    List<StudentEntity> toStudentEntityList(List<StudentDtoRequest> studentDtoList) ;


}
