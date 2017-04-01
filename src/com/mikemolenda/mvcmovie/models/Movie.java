package com.mikemolenda.mvcmovie.models;

import java.io.Serializable;

/**
 * Bean for handling Movie data.
 */
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Genre {Action, Animation, Comedy, Crime, Documentary, Drama, Horror, SciFi, War, Western, Misc}

    private int id;
    private String title;
    private int year;
    private Genre genre;

    public Movie(int id, String title, int year, Genre genre) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genre = genre;
    }

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
