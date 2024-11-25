package com.vierund.LMS.controller;

import com.vierund.LMS.Livres;
import com.vierund.LMS.Membres;
import com.vierund.LMS.pretLivre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class membreController {

    private static final List<Membres> membresList = new ArrayList<>();
    private static final List<Livres> livresList = new ArrayList<>();
    private static final List<pretLivre> pretList = new ArrayList<>();

    private static Membres membreConnecte = null;

    static {
        membreConnecte = new Membres("Jean", "Dupont", "123456", "1990-01-01", "jean@example.com", "password", "Université", "0612345678");
        membresList.add(membreConnecte);

        livresList.add(new Livres("Livre A", "123", "Auteur A", "Editeur A", "2022-01-01", "Fiction", "Français", "300", "10.99", "Adultes"));
        livresList.add(new Livres("Livre B", "456", "Auteur B", "Editeur B", "2023-01-01", "Science", "Anglais", "250", "12.99", "Enfants"));
    }

    @GetMapping("/allPeriode")
    public String afficherFormulaireProlongation() {
        return "allPeriode"; // Page allPeriode.html
    }

    @PostMapping("/prolongerPret")
    public String prolongerPret(@RequestParam String isbn, @RequestParam String nouvelleDateRetour, Model model) {
        for (pretLivre pret : pretList) {
            if (pret.getLivre().getIsbn().equals(isbn)) {
                pret.setDateRetour(nouvelleDateRetour); // Mise à jour de la date de retour
                model.addAttribute("success", "La période de prêt a été prolongée avec succès.");
                return "allPeriode";
            }
        }
        model.addAttribute("error", "Aucun prêt trouvé avec cet ISBN.");
        return "allPeriode";
    }

    @GetMapping("/ReservLigne")
    public String afficherFormulaireReservation() {
        return "ReservLigne"; // Page ReservLigne.html
    }

    @PostMapping("/louerLivre")
    public String louerLivre(@RequestParam String titre, @RequestParam String datePret, @RequestParam String dateRetour, Model model) {
        for (Livres livre : livresList) {
            if (livre.getTitre().equalsIgnoreCase(titre) && livre.isDisponible()) {
                pretLivre pret = new pretLivre(livre, membreConnecte, datePret, dateRetour);
                pretList.add(pret);
                livre.setDisponible(false);

                model.addAttribute("success", "Le livre a été loué avec succès.");
                return "ReservLigne";
            }
        }
        model.addAttribute("error", "Aucun livre disponible avec ce titre.");
        return "ReservLigne";
    }

    @GetMapping("/modifCompte")
    public String afficherFormulaireModificationCompte(Model model) {
        model.addAttribute("membre", membreConnecte);
        return "modifCompte";
    }

    @PostMapping("/modifierCompte")
    public String modifierCompte(@RequestParam String email, @RequestParam String tel, @RequestParam String mdp, Model model) {
        if (membreConnecte != null) {
            membreConnecte.setEmail(email);
            membreConnecte.setTel(tel);
            membreConnecte.setMdp(mdp);
            model.addAttribute("success", "Vos informations ont été mises à jour avec succès.");
        } else {
            model.addAttribute("error", "Erreur lors de la mise à jour de votre compte.");
        }
        return "modifCompte";
    }
}
