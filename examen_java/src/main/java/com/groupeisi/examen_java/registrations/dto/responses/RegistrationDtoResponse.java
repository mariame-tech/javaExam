package com.groupeisi.examen_java.registrations.dto.responses;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationDtoResponse {
    private Long id;
    private LocalDate date;
    private String description;
    private boolean archive;
    private String classeName;
    private String classeDescription;
    private String studentFirstName;
    private String studentLastName;
    private String studentEmailPro;
    private String studentEmailPerso;
    private String studentPhoneNumber;
    private String studentAddress;
    private String studentRegistrationNu;
}
