package com.groupeisi.examen_java.students.entities;

import com.groupeisi.examen_java.registrations.entities.RegistrationEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "students")
public class StudentEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String emailPro;

    @Column(nullable = false)
    private String emailPerso;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Boolean archive;

    @Column(nullable = false)
    private String registrationNu;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<RegistrationEntity> registrations = new ArrayList<>();
}
