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

        // Execute SQL query
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

    /**
     * Retrieves ArrayList of movies that contain searchTerm (case-insensitive)
     * @param conn Connection to the DB
     * @param searchTerm Title to query
     * @return
     * @throws SQLException
     */
    public static List<Movie> findMoviesByTitle(Connection conn, String searchTerm) throws SQLException {

        // Select all movies
        String sql = "SELECT a.id, a.title, a.year, a.genre FROM movies a";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        // If title contains searchTerm, store in ArrayList and return
        List<Movie> movList = new ArrayList<Movie>();

        while(rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            int year = rs.getInt("year");
            Movie.Genre genre = Movie.Genre.valueOf(rs.getString("genre"));

            if (title.toLowerCase().contains(searchTerm.toLowerCase())) {
                Movie movie = new Movie(id, title, year, genre);
                movList.add(movie);
            }

        }

        return movList;

    }

    /**
     * Updates a row in the DB with the data from a bean
     * @param conn Connection to the DB
     * @param movie Movie object containing the data to write
     * @throws SQLException
     */
    public static void updateMovie(Connection conn, Movie movie) throws SQLException {

        // Prepare SQL statement
        String sql = "UPDATE movies SET title=?, year=?, genre=? WHERE id=?";
        PreparedStatement pstm = conn.prepareStatement(sql);

        // Set column values
        pstm.setString(1, movie.getTitle());
        pstm.setInt(2, movie.getYear());
        pstm.setString(3, movie.getGenre().toString());
        pstm.setInt(4, movie.getId());

        // Execute SQL
        pstm.executeUpdate();

    }

    /**
     * Inserts a new row in the DB, using data from a Movie bean
     * @param conn Connection to the DB
     * @param movie Movie object containing the data to write
     * @throws SQLException
     */
    public static void insertMovie(Connection conn, Movie movie) throws SQLException {

        // Prepare SQL statement
        String sql = "INSERT INTO movies(title, year, genre) VALUES (?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);

        // Set column values
        // id is auto-incrementing, so do not set
        pstm.setString(1, movie.getTitle());
        pstm.setInt(2, movie.getYear());
        pstm.setString(3, movie.getGenre().toString());

        // Execute SQL
        pstm.executeUpdate();

    }

    /**
     * Deletes the row from the database with the specified id
     * @param conn Connection to the DB
     * @param id ID of the row to delete
     * @throws SQLException
     */
    public static void deleteMovie(Connection conn, int id) throws SQLException {

        // Execute SQL statement for row with specified ID
        String sql = "DELETE movies WHERE id=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        pstm.executeUpdate();

    }

}
