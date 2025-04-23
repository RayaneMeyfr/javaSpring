package org.example.exo2_etudiant.service;

import org.example.exo2_etudiant.model.Etudiant;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class EtudiantService {
    private final Map<UUID, Etudiant> etudiants;

    public EtudiantService() {
        etudiants = new HashMap<>();

        Etudiant etudiantA = Etudiant.builder()
                .id(UUID.randomUUID())
                .name("Toto")
                .classe("2b")
                .age(18).build();

        Etudiant etudiantB = Etudiant.builder()
                .id(UUID.randomUUID())
                .name("Tata")
                .classe("2a")
                .age(18).build();

        Etudiant etudiantC = Etudiant.builder()
                .id(UUID.randomUUID())
                .name("Titi")
                .classe("1c")
                .age(18).build();

        etudiants.put(etudiantA.getId(), etudiantA);
        etudiants.put(etudiantB.getId(), etudiantB);
        etudiants.put(etudiantC.getId(), etudiantC);
    }

    public void ajouterEtudiant(Etudiant etudiant) {
        etudiant.setId(UUID.randomUUID());
        etudiants.put(etudiant.getId(), etudiant);
    }

    public List<Etudiant> getAllEtudiants() {
        return etudiants.values().stream().toList();
    }

    public Etudiant getEtudiantById(UUID id) {
        return etudiants.get(id);
    }

    public List<Etudiant> getEtudiantByName(String name) {
        return etudiants.values().stream().filter(etudiant -> etudiant.getName().equals(name)).toList();
    }

}
