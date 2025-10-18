package com.academico.dao;
import com.academico.config.ConexionDB;
import com.academico.modelo.Curso;
import java.sql.*; import java.util.*;
public class CursoDAO {
    public void registrar(Curso c) throws SQLException{
        String sql="INSERT INTO Cursos (nombre,descripcion) VALUES (?,?)";
        try(PreparedStatement ps=ConexionDB.getInstancia().getConexion().prepareStatement(sql)){
            ps.setString(1,c.getNombre()); ps.setString(2,c.getDescripcion()); ps.executeUpdate();
        }
    }
    public List<Curso> listar() throws SQLException{
        List<Curso> l=new ArrayList<>();
        String sql="SELECT id,nombre,descripcion FROM Cursos ORDER BY id DESC";
        try(PreparedStatement ps=ConexionDB.getInstancia().getConexion().prepareStatement(sql);
            ResultSet rs=ps.executeQuery()){
            while(rs.next()) l.add(new Curso(rs.getInt(1),rs.getString(2),rs.getString(3)));
        }
        return l;
    }
    public void eliminar(int id) throws SQLException{
        String sql="DELETE FROM Cursos WHERE id=?";
        try(PreparedStatement ps=ConexionDB.getInstancia().getConexion().prepareStatement(sql)){
            ps.setInt(1,id); ps.executeUpdate();
        }
    }
}
