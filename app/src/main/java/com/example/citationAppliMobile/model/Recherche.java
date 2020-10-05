package com.example.citationAppliMobile.model;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    public String getDate() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Log.i("jour", String.valueOf(date.getDay()));
        Log.i("date",df.format(date));
        String r = df.format(date);
        return r;
    }
}
