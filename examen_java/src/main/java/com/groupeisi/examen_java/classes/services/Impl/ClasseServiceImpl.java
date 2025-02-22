package com.groupeisi.examen_java.classes.services.Impl;

import com.groupeisi.examen_java.classes.dto.requests.ClasseDtoRequest;
import com.groupeisi.examen_java.classes.dto.responses.ClasseDtoResponse;
import com.groupeisi.examen_java.classes.entities.ClasseEntity;
import com.groupeisi.examen_java.classes.mapper.ClasseMapper;
import com.groupeisi.examen_java.classes.repository.ClasseRepository;
import com.groupeisi.examen_java.classes.services.ClasseService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
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
public class ClasseServiceImpl implements ClasseService {
    private final ClasseRepository classeRepository;
    private final ClasseMapper classeMapper;
    private final MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(ClasseServiceImpl.class);

    @Override
    @Transactional
    public Optional<ClasseDtoResponse> createClasse(ClasseDtoRequest classeDtoRequest) {
        if (classeRepository.findByName(classeDtoRequest.getName()).isPresent()) {
            String errorMessage = messageSource.getMessage("classe.exists",
                    new Object[]{classeDtoRequest.getName()}, Locale.getDefault());
            throw new EntityExistsException(errorMessage);
        }
        ClasseEntity classeEntity = classeMapper.toClasseEntity(classeDtoRequest);
        classeRepository.save(classeEntity);
        return Optional.of(classeMapper.toClasseDto(classeEntity));
    }

    @Override
    public Optional<List<ClasseDtoResponse>> getAllClasses() {
        List<ClasseEntity> classes = classeRepository.findAll();
        return Optional.of(classeMapper.toClasseDtoList(classes));
    }

    @Override
    public Optional<ClasseDtoResponse> getClasseById(Long id) {
        ClasseEntity classeEntity = classeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        messageSource.getMessage("classe.notfound", new Object[]{id}, Locale.getDefault())));
        return Optional.of(classeMapper.toClasseDto(classeEntity));
    }

    @Override
    public Optional<ClasseDtoResponse> getClasseByName(String name) {
        ClasseEntity classeEntity = classeRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(
                        messageSource.getMessage("classe.notfound", new Object[]{name}, Locale.getDefault())));
        return Optional.of(classeMapper.toClasseDto(classeEntity));
    }

    @Override
    @Transactional
    public boolean deleteClasse(Long id) {
        ClasseEntity classeEntity = classeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        messageSource.getMessage("classe.notfound", new Object[]{id}, Locale.getDefault())));
        classeRepository.deleteById(classeEntity.getId());
        return true;
    }

    @Override
    @Transactional
    public Optional<ClasseDtoResponse> updateClasse(Long id, ClasseDtoRequest classeDtoRequest) {
        ClasseEntity classeEntity = classeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        messageSource.getMessage("classe.notfound", new Object[]{id}, Locale.getDefault())));
        classeEntity.setName(classeDtoRequest.getName());
        classeEntity.setDescription(classeDtoRequest.getDescription());
        classeEntity.setArchive(classeDtoRequest.isArchive());
        classeRepository.save(classeEntity);
        return Optional.of(classeMapper.toClasseDto(classeEntity));
    }
}
