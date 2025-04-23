package org.example.exo2_etudiant.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Etudiant {
    private UUID id;
    private String name;
    private String classe;
    private int age;
}
