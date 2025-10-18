package com.academico.dao;

import com.academico.config.ConexionDB;
import com.academico.modelo.Alumno;
import java.sql.*;
import java.util.*;

public class AlumnoDAO {

    // INSERT
    public void registrar(Alumno a) throws SQLException {
        String sql = "INSERT INTO Alumnos (nombre, contrasena) VALUES (?, ?)";
        try (PreparedStatement ps = ConexionDB.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getContrasena()); // (hash ya calculado desde GUI)
            ps.executeUpdate();
        }
    }

    // SELECT * 
    public List<Alumno> listar() throws SQLException {
        List<Alumno> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, contrasena FROM Alumnos ORDER BY id DESC";
        try (PreparedStatement ps = ConexionDB.getInstancia().getConexion().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Alumno(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("contrasena")
                ));
            }
        }
        return lista;
    }

    // ✅ VALIDACIÓN DE LOGIN
    // Devuelve true si usuario + hash coinciden en BD
    public boolean validarLogin(String nombre, String hashContrasena) throws SQLException {
        String sql = "SELECT 1 FROM Alumnos WHERE nombre = ? AND contrasena = ? LIMIT 1";
        try (PreparedStatement ps = ConexionDB.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, hashContrasena);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    // ✅ BUSCAR POR NOMBRE (evitar duplicados)
    public Alumno buscarPorNombre(String nombre) throws SQLException {
        String sql = "SELECT id, nombre, contrasena FROM Alumnos WHERE nombre = ?";
        try (PreparedStatement ps = ConexionDB.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Alumno(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("contrasena")
                    );
                }
            }
        }
        return null;
    }

    // ✅ ELIMINAR (opcional)
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Alumnos WHERE id = ?";
        try (PreparedStatement ps = ConexionDB.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
