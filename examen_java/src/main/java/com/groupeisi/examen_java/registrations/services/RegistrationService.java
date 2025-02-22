package com.groupeisi.examen_java.registrations.services;


import com.groupeisi.examen_java.registrations.dto.requests.RegistrationDtoRequest;
import com.groupeisi.examen_java.registrations.dto.responses.RegistrationDtoResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RegistrationService {
    Optional<RegistrationDtoResponse> createRegistration(RegistrationDtoRequest registrationDto);
    Optional<List<RegistrationDtoResponse>> getAllRegistrations();
    Optional<RegistrationDtoResponse>getRegistrationById(Long id);
    boolean deleteRegistration(Long id);
    Optional<RegistrationDtoResponse>updateRegistration(Long id, RegistrationDtoRequest registrationDtoRequest);
    Optional<List<RegistrationDtoResponse>> getRegistrationsByDate(LocalDate date);
}
