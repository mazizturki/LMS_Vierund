package com.vierund.LMS.service;

import com.vierund.LMS.Membres;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class loginService {

    private static List<Membres> membresList = new ArrayList<>();

    static {
        membresList.add(new Membres("John", "Doe", "12345678", "19/01/2005", "johndoe@yahoo.fr", "john1234", "oxford", "063695214"));
        membresList.add(new Membres("John", "Smith", "12348765", "25/01/2000", "john@yahoo.fr", "john@012", "sorbonne", "061234567"));
    }

    public void ajouterMembre(Membres membre) {
        membresList.add(membre);  // Ajoute un membre à la liste
    }

    public Membres authenticate(String cin, String mdp) {
        return membresList.stream()
                .filter(membre -> membre.getCin().equals(cin) && membre.getMdp().equals(mdp))
                .findFirst()
                .orElse(null);
    }

    public List<Membres> getAllMembres() {
        return membresList;
    }
}
