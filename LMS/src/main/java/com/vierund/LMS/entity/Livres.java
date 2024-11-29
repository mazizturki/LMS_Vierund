package com.vierund.LMS.entity;

import java.util.ArrayList;
import java.util.Iterator;

public class Livres {
    private String idLivre;
    private String titre;
    private String auteur;
    private String editeur;
    private String datePublication;
    private String isbn;
    private String genre;
    private String langue;
    private String nbPage;
    private String prix;
    private String cible;
    private boolean disponible = true;

    private static final ArrayList<Livres> tabLiv = new ArrayList<>();
    private static int idCounter = 1;

    public Livres(String titre, String isbn, String auteur, String editeur, String datePublication,
                  String genre, String langue, String nbPage, String prix, String cible) {
        this.idLivre = generateId();
        this.titre = titre;
        this.isbn = isbn;
        this.auteur = auteur;
        this.editeur = editeur;
        this.datePublication = datePublication;
        this.genre = genre;
        this.langue = langue;
        this.nbPage = nbPage;
        this.prix = prix;
        this.cible = cible;
    }

    private static String generateId() {
        return "L" + String.format("%04d", idCounter++);
    }

    public String getIdLivre() {
        return idLivre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getNbPage() {
        return nbPage;
    }

    public void setNbPage(String nbPage) {
        this.nbPage = nbPage;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getCible() {
        return cible;
    }

    public void setCible(String cible) {
        this.cible = cible;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public static synchronized void ajouterLivre(Livres livre) {
        tabLiv.add(livre);
    }

    public static synchronized void supprimerLivre(String isbn) throws Exception {
        Iterator<Livres> iterator = tabLiv.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Livres livre = iterator.next();
            if (livre.getIsbn().equals(isbn)) {
                iterator.remove();
                found = true;
                break;
            }
        }

        if (!found) {
            throw new Exception("Livre avec ISBN " + isbn + " non trouvé.");
        }
    }

    public static synchronized ArrayList<Livres> getAllLivres() {
        return new ArrayList<>(tabLiv);
    }

    public static boolean verifierDisponibilite(String isbn) {
        for (Livres livre : tabLiv) {
            if (livre.getIsbn().equals(isbn)) {
                return livre.isDisponible();
            }
        }
        return false;
    }

    public static void mettreAJourDisponibilite(String isbn, boolean disponible) {
        for (Livres livre : tabLiv) {
            if (livre.getIsbn().equals(isbn)) {
                livre.setDisponible(disponible);
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "Livre{" +
                "idLivre='" + idLivre + '\'' +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", disponible=" + disponible +
                '}';
    }
}
