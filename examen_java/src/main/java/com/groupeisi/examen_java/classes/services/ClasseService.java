package com.groupeisi.examen_java.classes.services;

import com.groupeisi.examen_java.classes.dto.requests.ClasseDtoRequest;
import com.groupeisi.examen_java.classes.dto.responses.ClasseDtoResponse;

import java.util.List;
import java.util.Optional;

public interface ClasseService {

    Optional<ClasseDtoResponse> createClasse(ClasseDtoRequest classeDto);

    Optional<ClasseDtoResponse> getClasseById(Long id);

    Optional<ClasseDtoResponse> getClasseByName(String name);

    Optional<List<ClasseDtoResponse>> getAllClasses();

    Optional<ClasseDtoResponse> updateClasse(Long id, ClasseDtoRequest classeDto);

    boolean deleteClasse(Long id);
}

