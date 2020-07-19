package com.example.wrcsearchfilter.data.model;

import com.google.gson.annotations.SerializedName;

public class Author {

    @SerializedName("Name")
    String Name;

    @SerializedName("Surname")
    String Surname;

    @SerializedName("livre")
    Livre livre;

    public int id;

    public Author(String name, String surname, Livre livre, int id) {
        Name = name;
        Surname = surname;
        this.livre = livre;
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
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

    public void setId(int id) {
        this.id = id;
    }
}
