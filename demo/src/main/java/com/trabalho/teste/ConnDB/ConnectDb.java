package com.trabalho.teste.ConnDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDb {

    private static final String URL =
            "jdbc:h2:~/test";
    public static Connection conectar() throws Exception {
        return DriverManager.getConnection(URL, "sa", "");
    }
}