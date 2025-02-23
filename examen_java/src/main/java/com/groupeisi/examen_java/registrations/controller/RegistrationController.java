package com.groupeisi.examen_java.registrations.controller;


import com.groupeisi.examen_java.registrations.dto.requests.RegistrationDtoRequest;
import com.groupeisi.examen_java.registrations.dto.responses.RegistrationDtoResponse;
import com.groupeisi.examen_java.registrations.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/registrations")
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    // Create a new registration
    @PostMapping
    public ResponseEntity<RegistrationDtoResponse> createRegistration(@RequestBody RegistrationDtoRequest registrationDto) {
        RegistrationDtoResponse response = registrationService.createRegistration(registrationDto).get();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get all registrations
    @GetMapping
    public ResponseEntity<List<RegistrationDtoResponse>> getAllRegistrations() {
        List<RegistrationDtoResponse> response = registrationService.getAllRegistrations().get();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // Get a registration by ID
    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDtoResponse> getRegistrationById(@PathVariable Long id) {
        RegistrationDtoResponse response = registrationService.getRegistrationById(id).get();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Delete a registration by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long id) {
        registrationService.deleteRegistration(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    // Update a registration by ID
    @PutMapping("/{id}")
    public ResponseEntity<RegistrationDtoResponse> updateRegistration(
            @PathVariable Long id,
            @RequestBody RegistrationDtoRequest registrationDto) {
        RegistrationDtoResponse response = registrationService.updateRegistration(id, registrationDto).get();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get registrations by date
    @GetMapping("/date/{date}")
    public ResponseEntity<List<RegistrationDtoResponse>> getRegistrationsByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Optional<List<RegistrationDtoResponse>> registrations = registrationService.getRegistrationsByDate(date);
        return new ResponseEntity<>(registrations.get(), HttpStatus.OK);
    }

}
