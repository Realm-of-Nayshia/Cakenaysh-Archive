package com.stelios.cakenaysh.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private final String HOST = "localhost";
    private final int PORT = 3306;
    private final String DATABASE = "testserver_main_plugin";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private Connection connection;

    //connects the database to the server
    public void connect() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false",
                USERNAME, PASSWORD);
    }

    //checks if the database is connected
    public boolean isConnected() {return (connection != null);}

    //gets the connection
    public Connection getConnection() {return connection;}

    //disconnects the database from the server
    public void disconnect() {
        if (isConnected()){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
