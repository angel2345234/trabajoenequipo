package com.academico.dao;
import com.academico.config.ConexionDB;
import java.math.BigDecimal;
import java.sql.*;
public class InscripcionDAO {
    public void registrarInscripcion(int idAlumno,int idCurso,BigDecimal nota) throws SQLException{
        Connection con=ConexionDB.getInstancia().getConexion();
        try{
            con.setAutoCommit(false);
            String sql="INSERT INTO Inscripciones (id_alumno,id_curso,nota) VALUES (?,?,?)";
            try(PreparedStatement ps=con.prepareStatement(sql)){
                ps.setInt(1,idAlumno); ps.setInt(2,idCurso); ps.setBigDecimal(3,nota); ps.executeUpdate();
            }
            con.commit();
        }catch(SQLException e){
            con.rollback(); throw new SQLException("Rollback ejecutado",e);
        }finally{ con.setAutoCommit(true); }
    }
}
