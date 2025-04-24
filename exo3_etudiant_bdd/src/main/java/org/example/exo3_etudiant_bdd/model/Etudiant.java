package org.example.exo3_etudiant_bdd.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Le nom ne doit pas être vide")
    private String name;
    @NotBlank(message = "La classe ne doit pas être vide")
    private String classe;
    @NotNull(message = "L'âge ne doit pas être nul")
    @Min(value = 0, message = "L'âge doit être positif")
    private Integer age;
}
