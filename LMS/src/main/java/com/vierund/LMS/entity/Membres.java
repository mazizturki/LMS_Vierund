package com.vierund.LMS.entity;

import java.util.ArrayList;

public class Membres {
    private String nom;
    private String prenom;
    private String cin;
    private String dateNaiss;
    private String email;
    private String mdp;
    private String uni;
    private String tel;
    private String statut;

    private static ArrayList<Membres> membresList = new ArrayList<>();

    public Membres(String nom, String prenom, String cin, String dateNaiss, String email, String mdp, String uni, String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.dateNaiss = dateNaiss;
        this.email = email;
        this.mdp = mdp;
        this.uni = uni;
        this.tel = tel;
        this.statut = "M";
        membresList.add(this);

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(String dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getUni() {
        return uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public static ArrayList<Membres> getMembresList() {
        return membresList;
    }
}
