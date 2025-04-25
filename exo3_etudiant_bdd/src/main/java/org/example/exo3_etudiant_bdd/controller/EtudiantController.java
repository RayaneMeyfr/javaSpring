package org.example.exo3_etudiant_bdd.controller;

import jakarta.validation.Valid;
import org.example.exo3_etudiant_bdd.model.Etudiant;
import org.example.exo3_etudiant_bdd.service.EtudiantService;
import org.example.exo3_etudiant_bdd.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class EtudiantController {
    private final EtudiantService etudiantService;
    private final UserService userService;
    private String location = "src/main/resources/static/img";

    public EtudiantController(EtudiantService etudiantService, UserService userService) {
        this.etudiantService = etudiantService;
        this.userService = userService;
    }

    @RequestMapping("/Etudiants")
    @ResponseBody
    public List<Etudiant> getAllEtudiants() {
        if (userService.checkLogin()) {
            return etudiantService.findAll();
        }
        return null;
    }

    @RequestMapping("/detail/{etudiantId}")
    public String detail(@PathVariable("etudiantId") int etudiantId, Model model) {
        if (userService.checkLogin()) {
            Etudiant etudiant = etudiantService.findById(etudiantId);
            model.addAttribute("etudiant", etudiant);
            return "details";
        }
        return "redirect:/login";
    }

    @RequestMapping("/listeEtudiants")
    public String listEtudiants(@RequestParam(name = "etudiantName", required = false) String name, Model model) {
        if (userService.checkLogin()) {
            List<Etudiant> etudiants;

            if (name != null && !name.isEmpty()) {
                etudiants = etudiantService.findByName(name);
            } else {
                etudiants = etudiantService.findAll();
            }

            model.addAttribute("etudiants", etudiants);
            return "liste_etudiants";
        }
        return "redirect:/login";
    }

    @RequestMapping("/inscription")
    public String form(Model model) {
        if (userService.checkLogin()) {
            model.addAttribute("etudiant", new Etudiant());
            return "inscription";
        }
        return "redirect:/login";
    }

    @RequestMapping("/update/{etudiantId}")
    public String update(@PathVariable("etudiantId") int id, Model model) {
        if (userService.checkLogin()) {
            model.addAttribute("etudiant", etudiantService.findById(id));
            return "inscription";
        }
        return "redirect:/login";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("etudiant") Etudiant etudiant, BindingResult bindingResult) {
        if (!userService.checkLogin()) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            return "inscription";
        } else {
            etudiantService.save(etudiant);
        }
        return "redirect:/listeEtudiants";
    }

    @RequestMapping("/delete/{etudiantId}")
    public String delete(@PathVariable("etudiantId") int id) {
        if (userService.checkLogin()) {
            etudiantService.delete(etudiantService.findById(id));
            return "redirect:/listeEtudiants";
        }
        return "redirect:/login";
    }

    @RequestMapping("/")
    public String home() {
        if (userService.checkLogin()) {
            return "home";
        }else
            return "redirect:/login";
    }

    @RequestMapping("/addImage/{etudiantId}")
    public String formImage(@PathVariable("etudiantId") int id, Model model) {
        if (userService.checkLogin()) {
            model.addAttribute("id", id);
            return "formImage";
        }
        return "redirect:/login";
    }


    @PostMapping("/upload")
    public String postForm(@RequestParam("image") MultipartFile image,@RequestParam("id") int id) throws IOException {
        Path destinationFile = Paths.get(location).resolve(image.getOriginalFilename()).toAbsolutePath();
        InputStream inputStream = image.getInputStream();
        Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);

        Etudiant etudiant = etudiantService.findById(id);
        etudiant.setImage(image.getOriginalFilename());
        etudiantService.save(etudiant);
        return "redirect:/listeEtudiants";
    }


}
