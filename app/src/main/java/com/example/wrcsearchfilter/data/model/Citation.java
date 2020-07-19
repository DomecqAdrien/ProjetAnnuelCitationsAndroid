package com.example.wrcsearchfilter.data.model;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Citation {

    private int id;
    private Book book;
    private String citation;
    private List<Tag> tags;
    private List<Citation> CitationsConnexes;

    @Override
    public String toString() {
        return "Citation{" +
                "id=" + id +
                ", book=" + book +
                ", citation='" + citation + '\'' +
                ", tags=" + tags +
                ", CitationsConnexes=" + CitationsConnexes +
                '}';
    }


    public Citation(Book book, int id, String citation) {
        this.book = book;
        this.id = id;
        this.citation = citation;
    }

    public Book getBook() {
        return book;
    }

    public int getId() {
        return id;
    }

    public String getCitation() {
        return citation;
    }

    public List<Citation> getCitationsConnexes() {
        return CitationsConnexes;
    }

    public List<Tag> getTags() {
        return tags;
    }
}
