package com.example.wrcsearchfilter.data.model;

import com.google.gson.annotations.SerializedName;

public class Livre {

    private int id;

    private int years;

    @SerializedName("Title")
    private String Title;

    @SerializedName("Citations")
    private String Citations;

    @SerializedName ("CitationsConnexes")
    private String CitationsConnexes;

    @SerializedName ("tags")
    private String tags;
    Author author;

    public Livre(int id, int years, String title, Author author) {
        this.id = id;
        this.years = years;
        Title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCitations() {
        return Citations;
    }

    public void setCitations(String citations) {
        Citations = citations;
    }

    public String getCitationsConnexes() {
        return CitationsConnexes;
    }

    public void setCitationsConnexes(String citationsConnexes) {
        CitationsConnexes = citationsConnexes;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
