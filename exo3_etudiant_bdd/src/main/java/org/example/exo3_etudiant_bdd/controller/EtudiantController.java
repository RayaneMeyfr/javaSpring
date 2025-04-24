package org.example.exo3_etudiant_bdd.controller;

import jakarta.validation.Valid;
import org.example.exo3_etudiant_bdd.model.Etudiant;
import org.example.exo3_etudiant_bdd.service.EtudiantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        return etudiantService.findAll();
    }

    @RequestMapping("/detail/{etudiantId}")
    public String detail(@PathVariable("etudiantId") int etudiantId, Model model) {
        Etudiant etudiant = etudiantService.findById(etudiantId);
        model.addAttribute("etudiant", etudiant);
        return "details";
    }

    @RequestMapping("/listeEtudiants")
    public String listEtudiants(@RequestParam(name = "etudiantName", required = false) String name, Model model) {
        List<Etudiant> etudiants;

        if (name != null && !name.isEmpty()) {
            etudiants = etudiantService.findByName(name);
        } else {
            etudiants = etudiantService.findAll();
        }

        model.addAttribute("etudiants", etudiants);
        return "liste_etudiants";
    }

    @RequestMapping("/inscription")
    public String form(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        return "inscription";
    }

    @RequestMapping("/update/{etudiantId}")
    public String update(@PathVariable("etudiantId") int id, Model model) {
        model.addAttribute("etudiant", etudiantService.findById(id));
        return "inscription"; // pour cohérence avec le formulaire d'inscription
    }


    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("etudiant") Etudiant etudiant, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "inscription";
        }else {
            etudiantService.save(etudiant);
        }
        return "redirect:/listeEtudiants    ";
    }

    @RequestMapping("/delete/{etudiantId}")
    public String delete(@PathVariable("etudiantId") int id) {
        etudiantService.delete(etudiantService.findById(id));
        return "redirect:/listeEtudiants";
    }

    @RequestMapping("/")
    public String home() {
        return "home";
    }
}
