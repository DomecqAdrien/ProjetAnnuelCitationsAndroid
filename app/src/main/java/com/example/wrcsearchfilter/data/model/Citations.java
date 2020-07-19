package com.example.wrcsearchfilter.data.model;
import com.google.gson.annotations.SerializedName;

public class Citations {

    @SerializedName ("livre")
    private Livre livre;

    private int id;

    @SerializedName ("contenu")
    private String contenu;

    public Citations(Livre livre, int id, String contenu) {
        this.livre = livre;
        this.id = id;
        this.contenu = contenu;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public int getId() {
        return id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

}
