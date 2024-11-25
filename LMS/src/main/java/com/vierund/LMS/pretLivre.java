package com.vierund.LMS;

import java.util.Date;

public class pretLivre {
    private String idPret;
    private Livres livre;
    private Membres emprunteur;
    private String datePret;
    private String dateRetour;
    private String nomInv;
    private String cinInv;
    private boolean estTermine;
    private static int idCounter = 1;

    public pretLivre(Livres livre, Object o, String datePret, String dateRet) {
    }

    public void Pret(Livres livre, Membres emprunteur, String datePret, String dateRetour) {
        this.idPret = generateId();
        this.livre = livre;
        this.emprunteur = emprunteur;
        this.datePret = datePret;
        this.dateRetour = dateRetour;
        this.estTermine = false;

        if (!livre.isDisponible()) {
            throw new IllegalStateException("Le livre " + livre.getTitre() + " n'est pas disponible.");
        }
        livre.setDisponible(false);
    }

    private String generateId() {
        return "P" + String.format("%04d", idCounter++);
    }

    public String getIdPret() {
        return idPret;
    }

    public Livres getLivre() {
        return livre;
    }

    public Membres getEmprunteur() {
        return emprunteur;
    }

    public String getDatePret() {
        return datePret;
    }

    public void setDatePret(String datePret) {
        this.datePret = datePret;
    }

    public String getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(String dateRetour) {
        this.dateRetour = dateRetour;
    }

    public void setEmprunteurInv(String nomInv, String cinInv) {
        this.nomInv = nomInv;
        this.cinInv = cinInv;
    }

    public String getNomInv() {
        return nomInv;
    }

    public String getCinInv() {
        return cinInv;
    }

    public boolean isEstTermine() {
        return estTermine;
    }

    public void terminerPret() {
        if (!estTermine) {
            estTermine = true;
            livre.setDisponible(true);
        } else {
            throw new IllegalStateException("Le prêt est déjà terminé.");
        }
    }

    @Override
    public String toString() {
        return "Prêt{" +
                "idPret='" + idPret + '\'' +
                ", livre=" + livre.getTitre() +
                ", emprunteur=" + emprunteur.getNom() + " " + emprunteur.getPrenom() +
                ", datePret=" + datePret +
                ", dateRetour=" + dateRetour +
                ", estTermine=" + estTermine +
                '}';
    }
}
