package com.vetcare.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {
    private static DatabaseConnection instance;
    private static Connection connection;

    private DatabaseConnection() throws SQLException {   // constructor privado: nadie hace "new"
        String url = AppConfig.get("db.url");            // cadena de Supabase
        String user = AppConfig.get("db.user");
        String password = AppConfig.get("db.password");
        DatabaseConnection.connection = DriverManager.getConnection(url, user, password);
    }

    

    public static synchronized DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {                          // se crea solo la primera vez
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public static  Connection getConnection() { return connection; }
}