package com.example.wrcsearchfilter.model;

import java.io.Serializable;
import java.util.List;

public class Auteur implements Serializable {

    public int id;
    String nom;
    String prenom;
    public String dateNaissance;
    List<Book> books;

    public Auteur(String nom, String prenom, int id, String dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
        this.dateNaissance = dateNaissance;
    }

    @Override
    public String toString() {
        return "Auteur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance='" + dateNaissance + '\'' +
                ", books=" + books +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public List<Book> getBooks() {
        return books;
    }
}

