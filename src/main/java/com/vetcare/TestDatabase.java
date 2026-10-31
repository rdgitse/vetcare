package com.vetcare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDatabase {

    public static void main(String[] args) {
        // Connection connection = DriverManager.getConnection(url, user, password);

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://aws-0-us-east-1.pooler.supabase.com:5432/postgres?sslmode=require", "postgres.pisvvgfltzlrpnbaqkmk", "BLZPwW6HQkqtopHb");
            if(connection != null){
                System.out.println("conexion valida");
            }

        } catch (SQLException e) {
            System.out.println("Connection is invalid: " + e.getMessage());
        }
    }
}