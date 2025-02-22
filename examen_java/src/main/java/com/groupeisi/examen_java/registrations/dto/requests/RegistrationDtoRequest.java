package com.groupeisi.examen_java.registrations.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationDtoRequest {
    private Long id;

    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Archive status is required")
    private Boolean archive;

    @NotNull
    private Long studentId;

    @NotNull
    private Long classeId;

    @NotBlank
    private String classeName;

    @NotBlank
    private String classeDescription;

    @NotBlank
    private String studentFirstName;

    @NotBlank
    private String studentLastName;

    @NotBlank
    private String studentEmailPro;

    @NotBlank
    private String studentEmailPerso;

    @NotBlank
    private String studentPhoneNumber;

    @NotBlank
    private String studentAddress;
    @NotBlank
    private String studentRegistrationNu;
}
