package com.groupeisi.examen_java.registrations.services.Impl;

import com.groupeisi.examen_java.registrations.dto.requests.RegistrationDtoRequest;
import com.groupeisi.examen_java.registrations.dto.responses.RegistrationDtoResponse;
import com.groupeisi.examen_java.registrations.entities.RegistrationEntity;
import com.groupeisi.examen_java.registrations.mapper.RegistrationMapper;
import com.groupeisi.examen_java.registrations.repository.RegistrationRepository;
import com.groupeisi.examen_java.registrations.services.RegistrationService;
import com.groupeisi.examen_java.students.entities.StudentEntity;
import com.groupeisi.examen_java.students.repository.StudentRepository;
import com.groupeisi.examen_java.classes.entities.ClasseEntity;
import com.groupeisi.examen_java.classes.repository.ClasseRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final RegistrationMapper registrationMapper;
    private final StudentRepository studentRepository;
    private final ClasseRepository classeRepository;
    private final MessageSource messageSource;

    private final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    @Override
    @Transactional
    public Optional<RegistrationDtoResponse> createRegistration(RegistrationDtoRequest registrationDto) {
        StudentEntity student = studentRepository.findById(registrationDto.getStudentId())
                .orElseThrow(() -> {
                    logger.error("student.notfound", registrationDto.getStudentId());
                    return new EntityNotFoundException("Student not found");
                });

        ClasseEntity classe = classeRepository.findById(registrationDto.getClasseId())
                .orElseThrow(() -> {
                    logger.error("classe.notfound", registrationDto.getClasseId());
                    return new EntityNotFoundException("Classe not found");
                });

        RegistrationEntity registration = registrationMapper.toRegistrationEntity(registrationDto);
        registration.setStudent(student);
        registration.setClasse(classe);
        registration.setDate(LocalDate.now());

        RegistrationEntity savedRegistration = registrationRepository.save(registration);
        logger.info("Registration created successfully with ID: {}", savedRegistration.getId());

        return Optional.of(registrationMapper.toRegistrationDtoResponse(savedRegistration));
    }

    @Override
    public Optional<List<RegistrationDtoResponse>> getAllRegistrations() {
        List<RegistrationEntity> registrations = registrationRepository.findAll();
        logger.debug("Found {} registrations", registrations.size());
        return Optional.of(registrationMapper.toRegistrationDtoResponseList(registrations));
    }

    @Override
    public Optional<RegistrationDtoResponse> getRegistrationById(Long id) {
        return registrationRepository.findById(id)
                .map(registration -> Optional.of(registrationMapper.toRegistrationDtoResponse(registration)))
                .orElseThrow(() -> new EntityNotFoundException("Registration not found with ID: " + id));
    }

    @Override
    public boolean deleteRegistration(Long id) {
        if (registrationRepository.findById(id).isEmpty()) {
            String errorMessage = messageSource.getMessage("registration.notfound", new Object[]{id}, Locale.getDefault());
            logger.error("Registration with ID {} not found: {}", id, errorMessage);
            throw new EntityNotFoundException(errorMessage);
        }
        registrationRepository.deleteById(id);
        logger.info("Successfully deleted registration with ID: {}", id);
        return true;
    }

    @Override
    public Optional<RegistrationDtoResponse> updateRegistration(Long id, RegistrationDtoRequest registrationDto) {
        RegistrationEntity registration = registrationRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Registration not found with ID: {}", id);
                    return new EntityNotFoundException("Registration not found");
                });

        StudentEntity student = registration.getStudent();
        ClasseEntity classe = registration.getClasse();

        // Mise à jour des champs de l'inscription
        registration.setDescription(registrationDto.getDescription());
        registration.setArchive(registrationDto.getArchive());

        if (registrationDto.getStudentFirstName() != null) student.setFirstName(registrationDto.getStudentFirstName());
        if (registrationDto.getStudentLastName() != null) student.setLastName(registrationDto.getStudentLastName());
        if (registrationDto.getStudentEmailPro() != null) student.setEmailPro(registrationDto.getStudentEmailPro());
        if (registrationDto.getStudentEmailPerso() != null) student.setEmailPerso(registrationDto.getStudentEmailPerso());
        if (registrationDto.getStudentPhoneNumber() != null) student.setPhoneNumber(registrationDto.getStudentPhoneNumber());
        if (registrationDto.getStudentAddress() != null) student.setAddress(registrationDto.getStudentAddress());
        if (registrationDto.getStudentRegistrationNu() != null) student.setRegistrationNu(registrationDto.getStudentRegistrationNu());

        studentRepository.save(student);

        if (registrationDto.getClasseName() != null) classe.setName(registrationDto.getClasseName());
        if (registrationDto.getClasseDescription() != null) classe.setDescription(registrationDto.getClasseDescription());

        classeRepository.save(classe);

        // Sauvegarde finale de l'inscription
        RegistrationEntity updatedRegistration = registrationRepository.save(registration);
        logger.info("Registration updated successfully with ID: {}", updatedRegistration.getId());

        return Optional.of(registrationMapper.toRegistrationDtoResponse(updatedRegistration));
    }



    @Override
    public Optional<List<RegistrationDtoResponse>> getRegistrationsByDate(LocalDate date) {
        Optional<List<RegistrationEntity>> registrations = registrationRepository.findByDate(date)
                .filter(list -> !list.isEmpty());

        if (registrations.isPresent()) {
            List<RegistrationDtoResponse> dtos = registrationMapper.toRegistrationDtoResponseList(registrations.get());
            return Optional.of(dtos);
        } else {
            logger.warn("No registrations found for date: {}", date);
            return Optional.empty();
        }
    }
}
