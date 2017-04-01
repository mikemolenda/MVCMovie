package com.mikemolenda.mvcmovie.utils;

import com.mikemolenda.mvcmovie.models.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Static methods for interacting with the database
 */
public class DBUtils {

    /**
     * Retrieve a single Movie item from the DB
     * @param conn Connection to the DB
     * @param id The id of the Movie to return
     * @return Movie object representing a single row in the Movies table, null if not found
     * @throws SQLException
     */
    public static Movie retrieveMovie(Connection conn, int id) throws SQLException {

        // Execute SQL query for row with specified id
        String sql = "SELECT a.Id, a.title, a.year, a.genre FROM movies a WHERE a.id=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();

        // Store result set in bean and return
        while(rs.next()) {
            String title = rs.getString("title");
            int year = rs.getInt("year");
            Movie.Genre genre = Movie.Genre.valueOf(rs.getString("genre"));

            Movie movie = new Movie(id, title, year, genre);
            return movie;
        }

        // Return null if no results found
        return null;

    }

    /**
     * Retrieve contents of Movies table in DB as an ArrayList
     * @param conn Connection to the DB
     * @return Iterable ArrayList of all entries in the movies table
     * @throws SQLException
     */
    public static List<Movie> retrieveAllMovies(Connection conn) throws SQLException {

        // Execute SQL query for row with specified id
        String sql = "SELECT a.id, a.title, a.year, a.genre FROM movies a";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        // Store result set in ArrayList and return
        List<Movie> movList = new ArrayList<Movie>();

        while(rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            int year = rs.getInt("year");
            Movie.Genre genre = Movie.Genre.valueOf(rs.getString("genre"));

            Movie movie = new Movie(id, title, year, genre);
            movList.add(movie);
        }

        return movList;

    }


}
