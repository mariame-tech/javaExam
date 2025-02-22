
package com.groupeisi.examen_java.services.Impl;


import com.groupeisi.examen_java.classes.dto.requests.ClasseDtoRequest;
import com.groupeisi.examen_java.classes.dto.responses.ClasseDtoResponse;
import com.groupeisi.examen_java.classes.entities.ClasseEntity;
import com.groupeisi.examen_java.classes.mapper.ClasseMapper;
import com.groupeisi.examen_java.classes.repository.ClasseRepository;
import com.groupeisi.examen_java.classes.services.Impl.ClasseServiceImpl;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClasseServiceImplTests {

    @Mock
    private ClasseRepository classeRepository;

    @InjectMocks
    private ClasseServiceImpl classeService;

    @Mock
    private ClasseMapper classeMapper;

    @Mock
    private MessageSource messageSource;

    @Test
    void saveClasseOK() {
        when(classeRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(classeMapper.toClasseEntity(any())).thenReturn(getClasseEntity());
        when(classeRepository.save(any())).thenReturn(getClasseEntity());
        when(classeMapper.toClasseDto(any())).thenReturn(getClasseDtoResponse());

        Optional<ClasseDtoResponse> response = classeService.createClasse(getClasseDtoRequest());
        assertTrue(response.isPresent());
    }

    @Test
    void saveClasseKO() {
        when(classeRepository.findByName(anyString())).thenReturn(Optional.of(getClasseEntity()));
        when(messageSource.getMessage(eq("classe.exists"), any(), any(Locale.class))).thenReturn("Classe already exists");

        EntityExistsException exception = assertThrows(EntityExistsException.class, () -> classeService.createClasse(getClasseDtoRequest()));
        assertEquals("Classe already exists", exception.getMessage());
    }

    @Test
    void getAllClasses() {
        when(classeRepository.findAll()).thenReturn(List.of(getClasseEntity()));
        when(classeMapper.toClasseDtoList(any())).thenReturn(List.of(getClasseDtoResponse()));

        Optional<List<ClasseDtoResponse>> classes = classeService.getAllClasses();
        assertTrue(classes.isPresent());
        assertEquals(1, classes.get().size());
    }

    @Test
    void getClasseByIdOK() {
        when(classeRepository.findById(anyLong())).thenReturn(Optional.of(getClasseEntity()));
        when(classeMapper.toClasseDto(any())).thenReturn(getClasseDtoResponse());

        Optional<ClasseDtoResponse> classe = classeService.getClasseById(1L);
        assertTrue(classe.isPresent());
    }

    @Test
    void getClasseByIdKO() {
        when(classeRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("classe.notfound"), any(), any(Locale.class))).thenReturn("Classe not found");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> classeService.getClasseById(1L));
        assertEquals("Classe not found", exception.getMessage());
    }

    @Test
    void deleteClasseOK() {
        when(classeRepository.findById(anyLong())).thenReturn(Optional.of(getClasseEntity()));

        boolean result = classeService.deleteClasse(1L);
        assertTrue(result);
        verify(classeRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteClasseKO() {
        when(classeRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("classe.notfound"), any(), any(Locale.class))).thenReturn("Classe not found");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> classeService.deleteClasse(1L));
        assertEquals("Classe not found", exception.getMessage());
    }

    @Test
    void updateClasseOK() {
        when(classeRepository.findById(anyLong())).thenReturn(Optional.of(getClasseEntity()));
        when(classeRepository.save(any())).thenReturn(getClasseEntity());
        when(classeMapper.toClasseDto(any())).thenReturn(getClasseDtoResponse());

        Optional<ClasseDtoResponse> updatedClasse = classeService.updateClasse(1L, getClasseDtoRequest());
        assertTrue(updatedClasse.isPresent());
    }

    @Test
    void updateClasseKO() {
        when(classeRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("classe.notfound"), any(), any(Locale.class))).thenReturn("Classe not found");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> classeService.updateClasse(1L, getClasseDtoRequest()));
        assertEquals("Classe not found", exception.getMessage());
    }

    private ClasseDtoRequest getClasseDtoRequest() {
        ClasseDtoRequest classeDtoRequest = new ClasseDtoRequest();
        classeDtoRequest.setName("Info");
        classeDtoRequest.setDescription("Classe des étudiants en informatique");
        classeDtoRequest.setArchive(true);

        return classeDtoRequest;
    }

    private ClasseEntity getClasseEntity() {
        ClasseEntity classeEntity = new ClasseEntity();
        classeEntity.setName("Info");
        classeEntity.setId(1L);
        classeEntity.setDescription("Classe des étudiants en informatique");
        classeEntity.setArchive(true);

        return classeEntity;
    }

    private ClasseDtoResponse getClasseDtoResponse() {
        ClasseDtoResponse classeDtoResponse = new ClasseDtoResponse();
        classeDtoResponse.setName("Info");
        classeDtoResponse.setDescription("Classe des étudiants en informatique");
        classeDtoResponse.setArchive(true);

        return  classeDtoResponse;
    }
}
