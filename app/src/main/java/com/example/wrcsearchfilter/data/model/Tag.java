package com.example.wrcsearchfilter.data.model;

class Tag {
    int id;
    String tag;

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }
}
