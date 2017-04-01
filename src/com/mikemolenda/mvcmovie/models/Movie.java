package com.mikemolenda.mvcmovie.models;

import java.io.Serializable;

public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Genre {Action, Animation, Comedy, Documentary, Drama, Horror, SciFi, Western}

    private int id;
    private String title;
    private int year;
    private Genre genre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

}
