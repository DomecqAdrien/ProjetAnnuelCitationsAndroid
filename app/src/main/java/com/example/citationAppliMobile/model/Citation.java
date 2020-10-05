package com.example.citationAppliMobile.model;

import java.util.List;

public class Citation {

    private int id;
    private Book book;
    private String citation;
    private List<Tag> tags;
    private List<Citation> citationsConnexes;

    @Override
    public String toString() {
        return "Citation{" +
                "id=" + id +
                ", book=" + book +
                ", citation='" + citation + '\'' +
                ", tags=" + tags +
                ", citationsConnexes=" + citationsConnexes +
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
        return citationsConnexes;
    }

    public String getTags() {
        String tagsToString = "";
        for(int i = 0; i < tags.size(); i++){
            tagsToString += tags.get(i).getTag();
            if(i != tags.size()-1){
                tagsToString += ", ";
            }
        }
        return tagsToString;
    }
}
