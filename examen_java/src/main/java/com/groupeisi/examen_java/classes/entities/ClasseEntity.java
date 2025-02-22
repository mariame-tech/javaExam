package com.groupeisi.examen_java.classes.entities;

import com.groupeisi.examen_java.registrations.entities.RegistrationEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "classes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClasseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean archive;

    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL)
    private List<RegistrationEntity> registrations;
}
