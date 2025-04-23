package org.example.exo2_etudiant.controller;

import org.example.exo2_etudiant.model.Etudiant;
import org.example.exo2_etudiant.service.EtudiantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class EtudiantController {
    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @RequestMapping("/Etudiants")
    @ResponseBody
    public List<Etudiant> getAllEtudiants() {
        return etudiantService.getAllEtudiants();
    }

    @RequestMapping("/detail/{EtudiantId}")
    public String detail(@PathVariable("EtudiantId") UUID etudiantId, Model model) {
        Etudiant etudiant = etudiantService.getEtudiantById(etudiantId);
        model.addAttribute("etudiant", etudiant);
        return "details";
    }

    @RequestMapping("/listeEtudiants")
    public String listEtudiants(@RequestParam(name = "etudiantName", required = false) String name, Model model) {
        List<Etudiant> etudiants;

        if (name != null && !name.isEmpty()) {
            etudiants = etudiantService.getEtudiantByName(name);
        } else {
            etudiants = etudiantService.getAllEtudiants();
        }

        model.addAttribute("etudiants", etudiants);
        return "liste_etudiants";
    }

    @RequestMapping("/inscription")
    public String form(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        return "inscription";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("etudiant") Etudiant etudiant) {
        System.out.println(etudiant);
        etudiantService.ajouterEtudiant(etudiant);
        return "redirect:/listeEtudiants    ";
    }

    @RequestMapping("/")
    public String home() {
        return "home";
    }
}
