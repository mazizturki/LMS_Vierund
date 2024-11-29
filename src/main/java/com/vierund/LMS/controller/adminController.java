package com.vierund.LMS.controller;

import com.vierund.LMS.entity.Livres;
import com.vierund.LMS.entity.Membres;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;

@Controller
@RequestMapping("/admin")

public class adminController {

    private static final ArrayList<Membres> membresList = new ArrayList<>();
    private static final ArrayList<Livres> livresList = new ArrayList<>();

    @GetMapping("/ajoutLivre")
    public String afficherFormulaireAjoutLivre() {
        return "ajoutLivre";
    }

    @PostMapping("/ajouterLivre")
    public String ajouterLivre(@RequestParam String titre,
                               @RequestParam String auteur,
                               @RequestParam String editeur,
                               @RequestParam String isbn,
                               @RequestParam String datePub,
                               @RequestParam String genre,
                               @RequestParam String langue,
                               @RequestParam String nbPage,
                               @RequestParam String cible,
                               @RequestParam String prix) {
        Livres liver = new Livres(titre, isbn, auteur, editeur, datePub, genre, langue, nbPage, prix, cible);
        livresList.add(liver);
        return "redirect:/listeLivre";
    }

    @GetMapping("/listeLivre")
    public String afficherListeLivres(Model model) {
        model.addAttribute("livres", livresList);
        return "listeLivre";
    }

    @GetMapping("/suppLivre")
    public String afficherFormulaireSuppressionLivre() {
        return "suppLivre";
    }

    @PostMapping("/supprimerLivre")
    public String supprimerLivre(@RequestParam String isbn) {
        Iterator<Livres> iterator = livresList.iterator();
        while (iterator.hasNext()) {
            Livres livre = iterator.next();
            if (livre.getIsbn().equals(isbn)) {
                iterator.remove();
                break;
            }
        }
        return "redirect:/listeLivre";
    }

    @GetMapping("/ajoutEtudiant")
    public String afficherFormulaireAjoutEtudiant() {
        return "ajoutEtudiant";
    }

    @PostMapping("/ajouterEtudiant")
    public String ajouterEtudiant(@RequestParam String nom,
                                  @RequestParam String prenom,
                                  @RequestParam String cin,
                                  @RequestParam String dateNaiss,
                                  @RequestParam String email,
                                  @RequestParam String mdp,
                                  @RequestParam String uni,
                                  @RequestParam String tel) {
        Membres membre = new Membres(nom, prenom, cin, dateNaiss, email, mdp, uni, tel);
        membresList.add(membre);
        return "redirect:/listeEtudiants";
    }

    @GetMapping("/listeEtudiants")
    public String afficherListeEtudiants(Model model) {
        model.addAttribute("membres", membresList);
        return "listeEtudiants";
    }

    @GetMapping("/suppEtudiant")
    public String afficherFormulaireSuppressionEtudiant() {
        return "suppEtudiant";
    }

    @PostMapping("/supprimerEtudiant")
    public String supprimerEtudiant(@RequestParam String cin) {
        Iterator<Membres> iterator = membresList.iterator();
        while (iterator.hasNext()) {
            Membres membre = iterator.next();
            if (membre.getCin().equals(cin)) {
                iterator.remove();
                break;
            }
        }
        return "redirect:/listeEtudiants";
    }
}
