package com.academico;
import com.academico.ui.VentanaCursos;
import com.academico.ui.VentanaRegistroAlumno;
import javax.swing.*;
public class App {
    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            JFrame menu=new JFrame("Control Académico - Demo");
            JButton a=new JButton("Registro Alumno"), c=new JButton("Cursos");
            JPanel p=new JPanel(); p.add(a); p.add(c);
            menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); menu.add(p); menu.setSize(320,120); menu.setLocationRelativeTo(null); menu.setVisible(true);
            a.addActionListener(e-> new VentanaRegistroAlumno().setVisible(true));
            c.addActionListener(e-> new VentanaCursos().setVisible(true));
        });
    }
}
