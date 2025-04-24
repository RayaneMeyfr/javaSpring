package org.example.exo3_etudiant_bdd.dao;

import org.example.exo3_etudiant_bdd.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
    List<Etudiant> findByName(String name);
}
