package org.example.exo3_etudiant_bdd.controller;

import org.example.exo3_etudiant_bdd.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;
    private String location = "src/main/resources/static/img";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String login() {
        return "formConnexion";
    }

    @PostMapping("login")
    public String login(@RequestParam("email") String email, @RequestParam("mdp") String mdp, Model model) {
        if (userService.login(email, mdp)) {
            return "redirect:/";
        } else
            return "redirect:/login";
    }

    @RequestMapping("/logout")
    public String logout() {
        if (userService.checkLogin()) {
            userService.logout();
            return "redirect:/login";
        } else
            return "redirect:/login";
    }

}
