package com.example.wrcsearchfilter.data.model;

public class Book {

    private int id;
    private String titre;
    private String imageUrl;
    private Auteur auteur;
    private int anneeParution;


    public Book(int id, int years, String titre, Auteur auteur) {
        this.id = id;
        this.anneeParution = years;
        this.titre = titre;
        this.auteur = auteur;
    }

    public int getId() {
        return id;
    }

    public int getAnneeParution() {
        return anneeParution;
    }

    public String getTitre() {
        return titre;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", auteur=" + auteur +
                ", anneeParution=" + anneeParution +
                '}';
    }
}
