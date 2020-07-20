package com.example.wrcsearchfilter.model;

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

    public String getTag() {
        return tag;
    }
}
