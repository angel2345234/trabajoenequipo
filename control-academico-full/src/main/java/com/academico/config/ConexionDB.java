package com.academico.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static ConexionDB instancia;
    private Connection conexion;
    private static final String URL = "jdbc:mysql://localhost:3306/control_academico?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    private ConexionDB() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Error al conectar con la base de datos", e);
        }
    }
    public static synchronized ConexionDB getInstancia() throws SQLException {
        if (instancia == null) instancia = new ConexionDB();
        return instancia;
    }
    public Connection getConexion() { return conexion; }
}
