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
import java.util.List;

@WebServlet(urlPatterns = "/list-movies")
public class ListMovies extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            // New DB connection
            Connection conn = MySQLConnUtils.getMySQLConnection();
            List<Movie> results =  DBUtils.retrieveAllMovies(conn);
            String url;

            // Respond with list of all movies
            if (!results.isEmpty()) {
                url = "/views/movie-list.jsp";
                request.setAttribute("movList", results);
                request.setAttribute("pageTitle", "All Movies");
            } else {
                url = "/views/not-found.jsp";
                request.setAttribute("pageTitle", "Not Found!");
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
