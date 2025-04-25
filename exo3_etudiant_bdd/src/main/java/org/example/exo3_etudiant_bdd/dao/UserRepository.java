package org.example.exo3_etudiant_bdd.dao;

import org.example.exo3_etudiant_bdd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmail(String email);
}
