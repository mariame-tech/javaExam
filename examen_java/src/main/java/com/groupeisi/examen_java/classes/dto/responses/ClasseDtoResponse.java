package com.groupeisi.examen_java.classes.dto.responses;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClasseDtoResponse {
    private Long id;
    private String name;
    private String description;
    private boolean archive;
}
