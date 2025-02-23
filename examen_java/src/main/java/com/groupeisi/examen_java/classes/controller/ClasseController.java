package com.groupeisi.examen_java.classes.controller;

import com.groupeisi.examen_java.classes.dto.requests.ClasseDtoRequest;
import com.groupeisi.examen_java.classes.dto.responses.ClasseDtoResponse;
import com.groupeisi.examen_java.classes.services.ClasseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class ClasseController {

    private final ClasseService classeService;
    private static final Logger logger = LoggerFactory.getLogger(ClasseController.class);

    @PostMapping
    public ResponseEntity<ClasseDtoResponse> createClasse(@RequestBody ClasseDtoRequest classeDto) {
        logger.info("Création d'une nouvelle classe: {}", classeDto.getName());
        Optional<ClasseDtoResponse> createdClasse = classeService.createClasse(classeDto);
        return createdClasse.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<ClasseDtoResponse>> getAllClasses() {
        logger.info("Récupération de toutes les classes");
        Optional<List<ClasseDtoResponse>> classes = classeService.getAllClasses();
        return classes.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClasseDtoResponse> getClasseById(@PathVariable Long id) {
        logger.info("Recherche de la classe avec ID: {}", id);
        Optional<ClasseDtoResponse> classe = classeService.getClasseById(id);
        return classe.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ClasseDtoResponse> getClasseByName(@PathVariable String name) {
        logger.info("Recherche de la classe avec le nom: {}", name);
        Optional<ClasseDtoResponse> classe = classeService.getClasseByName(name);
        return classe.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClasseDtoResponse> updateClasse(@PathVariable Long id, @RequestBody ClasseDtoRequest classeDto) {
        logger.info("Mise à jour de la classe avec ID: {}", id);
        Optional<ClasseDtoResponse> updatedClasse = classeService.updateClasse(id, classeDto);
        return updatedClasse.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClasse(@PathVariable Long id) {
        logger.info("Suppression de la classe avec ID: {}", id);
        boolean deleted = classeService.deleteClasse(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
