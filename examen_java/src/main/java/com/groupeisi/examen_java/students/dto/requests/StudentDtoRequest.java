package com.groupeisi.examen_java.students.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDtoRequest {
    private Long id;
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Professional email must be valid")
    @NotBlank(message = "Professional email is required")
    private String emailPro;

    @Email(message = "Personal email must be valid")
    @NotBlank(message = "Personal email is required")
    private String emailPerso;

    @Pattern(regexp = "^\\+?[0-9]{8,15}$", message = "Phone number must be valid")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    private String address;

    @NotNull(message = "Archive status is required")
    private Boolean archive;

    @NotBlank(message = "Registration number is required")
    private String registrationNu;
}
