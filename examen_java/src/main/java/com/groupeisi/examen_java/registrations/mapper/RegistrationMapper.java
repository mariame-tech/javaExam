package com.groupeisi.examen_java.registrations.mapper;


import com.groupeisi.examen_java.registrations.dto.requests.RegistrationDtoRequest;
import com.groupeisi.examen_java.registrations.dto.responses.RegistrationDtoResponse;
import com.groupeisi.examen_java.registrations.entities.RegistrationEntity;
import com.groupeisi.examen_java.students.entities.StudentEntity;
import com.groupeisi.examen_java.students.mapper.StudentMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring" , uses = StudentMapper.class)
public interface RegistrationMapper {
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "classe", ignore = true)
    RegistrationEntity toRegistrationEntity(RegistrationDtoRequest registrationDto);

    @Mapping(source = "student.firstName", target = "studentFirstName")
    @Mapping(source = "student.lastName", target = "studentLastName")
    @Mapping(source = "student.emailPro", target = "studentEmailPro")
    @Mapping(source = "student.emailPerso", target = "studentEmailPerso")
    @Mapping(source = "student.phoneNumber", target = "studentPhoneNumber")
    @Mapping(source = "student.address", target = "studentAddress")
    @Mapping(source = "student.registrationNu", target = "studentRegistrationNu")
    @Mapping(source = "classe.name", target = "classeName")
    @Mapping(source = "classe.description", target = "classeDescription")
    RegistrationDtoResponse toRegistrationDtoResponse(RegistrationEntity registrationEntity);

    List<RegistrationEntity> toRegistrationEntityList(List<RegistrationDtoRequest> registrationDtoList) ;

    List<RegistrationDtoResponse> toRegistrationDtoResponseList(List<RegistrationEntity> registrationEntityList);


}
