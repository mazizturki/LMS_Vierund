package com.vierund.LMS;

public class admin extends Membres {

    public admin(String nom, String prenom, String cin, String dateNaiss, String email, String mdp, String uni, String tel) {
        super(nom, prenom, cin, dateNaiss, email, mdp, uni, tel);
        super.setStatut("A");
    }

    @Override
    public void setStatut(String statut) {
        throw new UnsupportedOperationException("Le statut d'un administrateur ne peut pas être modifié.");
    }
}

