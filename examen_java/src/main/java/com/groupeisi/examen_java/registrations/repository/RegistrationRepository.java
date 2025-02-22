package com.groupeisi.examen_java.registrations.repository;


import com.groupeisi.examen_java.registrations.entities.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationEntity,Long> {


    Optional<List<RegistrationEntity>>findByDate(LocalDate date);
}
