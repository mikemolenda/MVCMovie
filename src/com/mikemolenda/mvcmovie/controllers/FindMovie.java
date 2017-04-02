package com.mikemolenda.mvcmovie.controllers;

import com.mikemolenda.mvcmovie.models.Movie;
import com.mikemolenda.mvcmovie.utils.DBUtils;
import com.mikemolenda.mvcmovie.utils.MySQLConnUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet that handles retrieving a movie. Returns movie info if found, list if more than one found, or not found error.
 */
@WebServlet(urlPatterns = "/find-movie")
public class FindMovie extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // New DB connection
            Connection conn = MySQLConnUtils.getMySQLConnection();

            String url;

            // Get movie by ID if ID passed
            if (request.getParameter("movieId") != null) {
                Movie movie = DBUtils.retrieveMovie(conn, Integer.parseInt(request.getParameter("movieId")));

                if (movie != null) {
                    request.setAttribute("movie", movie);
                    url = "/views/movie-info.jsp";
                } else {
                    url = "/views/not-found.jsp";
                }
            }

            // Search DB for title if title passed
            else if (request.getParameter("title") != null) {

                List<Movie> results = DBUtils.findMoviesByTitle(conn, request.getParameter("title"));

                // Show list of matching titles if more than one found
                if (results.size() > 1) {
                    request.setAttribute("movList", results);
                    url = "/views/list.jsp";

                    // Show item info if only one title found
                } else if (results.size() == 1) {
                    request.setAttribute("movie", results.get(0));
                    url = "/views/movie-info.jsp";

                    // Show not found if no results found
                } else {
                    url = "/views/not-found.jsp";
                }

            } else {
                url = "/views/not-found.jsp";
            }

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
