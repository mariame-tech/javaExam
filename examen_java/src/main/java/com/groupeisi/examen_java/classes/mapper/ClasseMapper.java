package com.groupeisi.examen_java.classes.mapper;

import com.groupeisi.examen_java.classes.dto.requests.ClasseDtoRequest;
import com.groupeisi.examen_java.classes.dto.responses.ClasseDtoResponse;
import com.groupeisi.examen_java.classes.entities.ClasseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClasseMapper {

    // Convertir une ClasseEntity en ClasseDtoResponse
    ClasseDtoResponse toClasseDto(ClasseEntity classeEntity);

    // Convertir une liste de ClasseEntity en liste de ClasseDtoResponse
    List<ClasseDtoResponse> toClasseDtoList(List<ClasseEntity> classeEntities);

    // Convertir un ClasseDtoRequest en ClasseEntity
    @Mapping(target = "id", ignore = true)
    ClasseEntity toClasseEntity(ClasseDtoRequest classeDto);

    // Convertir une liste de ClasseDtoRequest en liste de ClasseEntity
    List<ClasseEntity> toClasseEntityList(List<ClasseDtoRequest> classeDtoRequests);


}
