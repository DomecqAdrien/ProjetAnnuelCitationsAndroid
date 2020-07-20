package com.example.wrcsearchfilter.model;

import java.util.Date;

public class Recherche {
    private String citation;
    private Date date;

    public Recherche(String citation, Date date) {
        this.citation = citation;
        this.date = date;
    }

    public String getCitation() {
        return citation;
    }

    public Date getDate() {
        return date;
    }
}
