package com.stelios.cakenaysh.Util;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private final String HOST = "localhost";
    private final int PORT = 3306;
    private final String DATABASE = "cakenaysh";
    private final String USERNAME = "root";
    private final String PASSWORD = "GRUBBYGUSTARD123";

    private HikariDataSource hikari;

    //connects the database to the server
    public void connect() throws SQLException {
        hikari = new HikariDataSource();
        hikari.setDataSourceClassName("com.mysql.cj.jdbc.MysqlDataSource");
        hikari.addDataSourceProperty("serverName" ,HOST);
        hikari.addDataSourceProperty("port" ,PORT);
        hikari.addDataSourceProperty("databaseName" ,DATABASE);
        hikari.addDataSourceProperty("user" ,USERNAME);
        hikari.addDataSourceProperty("password" ,PASSWORD);
    }

    //checks if the database is connected
    public boolean isConnected() {return (hikari != null);}

    //gets the connection
    public HikariDataSource getHikari() {return hikari;}

    //disconnects the database from the server
    public void disconnect() {
        if (isConnected()){
            hikari.close();
        }
    }

}
