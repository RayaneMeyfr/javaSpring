package org.example.exo3_etudiant_bdd.service;

import jakarta.servlet.http.HttpSession;
import org.example.exo3_etudiant_bdd.dao.UserRepository;
import org.example.exo3_etudiant_bdd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    HttpSession session;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public void delete(User user) {
        userRepository.delete(user);
    }

    public boolean login(String email, String mdp) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
                return false;
        }else  {
            if (email.equals(user.getEmail()) && mdp.equals(user.getMdp())) {
                session.setAttribute("login", "OK");
                return true;
            } else {
                return false;
            }
        }
    }

    public void logout() {
        session.invalidate();
    }

    public boolean checkLogin() {
        try {
            String isOk = session.getAttribute("login").toString();
            return isOk.equals("OK");
        } catch (Exception e) {
            return false;
        }
    }
}
