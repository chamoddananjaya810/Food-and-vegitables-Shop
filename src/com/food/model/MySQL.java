package com.food.model;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class MySQL {

    private static Connection connection;
    private static final String USERNAME = "root";
    public static final String PASSWORD = "Chamod@2002$$";

    public static final String DATABASE = "foodshop";

    public static Statement CreateConnection() throws Exception {

        if (connection == null) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/" + DATABASE, USERNAME, PASSWORD);
        }

        Statement statement = connection.createStatement();

        return statement;

    }

    public static void iud(String query) {

        try {
            CreateConnection().executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ResultSet search(String query) throws Exception {

        ResultSet resultSet = CreateConnection().executeQuery(query);

        return resultSet;
    }

}
