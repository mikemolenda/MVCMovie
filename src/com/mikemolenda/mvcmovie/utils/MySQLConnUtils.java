package com.mikemolenda.mvcmovie.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Static methods to establish connections to a MySQL database
 */
public class MySQLConnUtils {

    /**
     * Establishes new MySQL connection using the default parameters
     * @return Connection - MySQL connection object
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getMySQLConnection()
            throws ClassNotFoundException, SQLException {

        String hostname = "localhost";
        String dbName = "mvcmovie";
        String username = "root";
        String password = "WX33mqbzCTkBmn$M";

        return getMySQLConnection(hostname, dbName, username, password);

    }

    /**
     * Establishes new MySQL connection using supplied parameters
     * @return Connection - MySQL connection object
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getMySQLConnection(
            String hostname, String dbName, String username, String password)
            throws ClassNotFoundException, SQLException {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            String connectionUrl = "jdbc:mysql://" + hostname + ":3306/" + dbName + "?useLegacyDatetimeCode=false&serverTimezone=America/Chicago";

            Connection conn = DriverManager.getConnection(connectionUrl, username, password);
            return conn;

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;

    }

}
