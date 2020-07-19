package com.example.wrcsearchfilter.ressource;

import android.widget.ImageView;

public class Videos {
    private String name;
    private String id;
    private String playeur;
    private ImageView photo;

    public Videos(String name, ImageView photo, String id, String playeur) {
        this.name = name;
        this.id = id;
        this.playeur = playeur;
        this.photo = photo;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public ImageView getPhoto() { return photo; }

    public void setPhoto(ImageView photo) { this.photo = photo; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPlayeur() { return playeur; }

    public void setPlayeur(String playeur) { this.playeur = playeur; }

}

