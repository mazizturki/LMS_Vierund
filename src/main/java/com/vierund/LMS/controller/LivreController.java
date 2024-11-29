package com.vierund.LMS.controller;

import com.vierund.LMS.entity.Livres;
import com.vierund.LMS.pretLivre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

@Controller
@RequestMapping("/livre")
public class LivreController {

    private static ArrayList<pretLivre> prets = new ArrayList<>();

    @GetMapping("/ajoutLivre")
    public String afficherFormulaire() {
        return "ajoutLivre";
    }

    @PostMapping("/ajouterLivre")
    public String ajouterLivre(@RequestParam String titre, @RequestParam String auteur,
                               @RequestParam String editeur, @RequestParam String date_pub,
                               @RequestParam String isbn, @RequestParam String genre,
                               @RequestParam String lang, @RequestParam String nbPage,
                               @RequestParam String prix, @RequestParam String pubcib,
                               Model model) {
        if (!Livres.verifierDisponibilite(isbn)) {
            model.addAttribute("error", "Un livre avec cet ISBN existe déjà.");
            return "ajoutLivre";
        }

        Livres livre = new Livres(titre, isbn, auteur, editeur, date_pub, genre, lang, nbPage, prix, pubcib);
        Livres.ajouterLivre(livre);
        return "redirect:/livre/listeLivre";
    }

    @GetMapping("/listeLivre")
    public String afficherListe(Model model) {
        model.addAttribute("livres", Livres.getAllLivres());
        return "listeLivre";
    }

    @GetMapping("/suppLivre")
    public String afficherFormulaireSuppression() {
        return "suppLivre";
    }

    @PostMapping("/suppLivre")
    public String supprimerLivre(@RequestParam String isbn, Model model) {
        try {
            Livres.supprimerLivre(isbn);
        } catch (Exception e) {
            model.addAttribute("error", "ISBN introuvable.");
            return "suppLivre";
        }

        return "redirect:/livre/listeLivre";
    }

    @GetMapping("/emprunterLivre")
    public String afficherFormulaireEmprunt(Model model) {
        model.addAttribute("livres", Livres.getAllLivres());
        return "louerInv";
    }

    @PostMapping("/louerInv")
    public String emprunterLivre(@RequestParam String nomInv,
                                 @RequestParam String cinInv,
                                 @RequestParam String isbn,
                                 @RequestParam String datePret,
                                 @RequestParam String dateRet,
                                 Model model) {
        for (Livres livre : Livres.getAllLivres()) {
            if (livre.getIsbn().equals(isbn) && livre.isDisponible()) {
                pretLivre pret = new pretLivre(livre, null, datePret, dateRet);
                pret.setEmprunteurInv(nomInv, cinInv);
                prets.add(pret);
                Livres.mettreAJourDisponibilite(isbn, false);
                return "redirect:/livre/listeLivre";
            }
        }
        model.addAttribute("error", "Le livre avec l'ISBN " + isbn + " n'est pas disponible ou introuvable.");
        return "louerInv";
    }


    @GetMapping("/retournerLivre")
    public String afficherFormulaireRetour() {
        return "retournerLivre";
    }

    @PostMapping("/retournerLivre")
    public String retournerLivre(@RequestParam String isbn, Model model) {
        for (pretLivre pret : prets) {
            if (pret.getLivre().getIsbn().equals(isbn) && !pret.isEstTermine()) {
                pret.terminerPret();
                return "redirect:/livre/listeLivre";
            }
        }

        model.addAttribute("error", "Aucun prêt actif trouvé pour cet ISBN.");
        return "retournerLivre";
    }
}
