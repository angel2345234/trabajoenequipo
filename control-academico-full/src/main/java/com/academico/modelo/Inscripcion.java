package com.academico.modelo;
import java.math.BigDecimal;
public class Inscripcion {
    private int id; private int idAlumno; private int idCurso; private BigDecimal nota;
    public Inscripcion() {}
    public Inscripcion(int idAlumno,int idCurso,BigDecimal nota){this.idAlumno=idAlumno;this.idCurso=idCurso;this.nota=nota;}
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public int getIdAlumno(){return idAlumno;} public void setIdAlumno(int idAlumno){this.idAlumno=idAlumno;}
    public int getIdCurso(){return idCurso;} public void setIdCurso(int idCurso){this.idCurso=idCurso;}
    public BigDecimal getNota(){return nota;} public void setNota(BigDecimal nota){this.nota=nota;}
}
