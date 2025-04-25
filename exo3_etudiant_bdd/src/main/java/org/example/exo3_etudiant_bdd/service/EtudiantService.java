package org.example.exo3_etudiant_bdd.service;

import org.example.exo3_etudiant_bdd.dao.EtudiantRepository;
import org.example.exo3_etudiant_bdd.model.Etudiant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantService {
    private final EtudiantRepository etudiantRepository;

    public EtudiantService(EtudiantRepository etudiantRepository) {
                this.etudiantRepository = etudiantRepository;
    }

    public List<Etudiant> findAll() {
        return etudiantRepository.findAll();
    }

    public List<Etudiant> findByName(String name) {
       return etudiantRepository.findByName(name);
    }

    public Etudiant findById(int id) {
        return etudiantRepository.findById(id).orElse(null);
    }

    public Etudiant save(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public void delete(Etudiant etudiant) {
        etudiantRepository.delete(etudiant);
    }
}
