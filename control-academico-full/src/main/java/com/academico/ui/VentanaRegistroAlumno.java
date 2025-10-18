package com.academico.ui;
import com.academico.dao.AlumnoDAO;
import com.academico.modelo.Alumno;
import javax.swing.*; import java.awt.*; import java.sql.SQLException;
public class VentanaRegistroAlumno extends JFrame {
    private JTextField txtNombre=new JTextField(22), txtDireccion=new JTextField(22);
    private JButton btnRegistrar=new JButton("Registrar");
    public VentanaRegistroAlumno(){
        setTitle("Registro de Alumno"); setSize(420,210); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel p=new JPanel(new GridBagLayout()); GridBagConstraints gc=new GridBagConstraints(); gc.insets=new Insets(6,6,6,6); gc.anchor=GridBagConstraints.WEST;
        gc.gridx=0;gc.gridy=0;p.add(new JLabel("Nombre:"),gc); gc.gridx=1;p.add(txtNombre,gc);
        gc.gridx=0;gc.gridy=1;p.add(new JLabel("Contraseña:"),gc); gc.gridx=1;p.add(txtDireccion,gc);
        gc.gridx=1;gc.gridy=2;gc.anchor=GridBagConstraints.EAST;p.add(btnRegistrar,gc); add(p);
        btnRegistrar.addActionListener(e->{
            String n=txtNombre.getText().trim(); String d=txtDireccion.getText().trim();
            if(n.isEmpty()){JOptionPane.showMessageDialog(this,"Ingresa el nombre.");return;}
            try{ new AlumnoDAO().registrar(new Alumno(n,d)); JOptionPane.showMessageDialog(this,"Alumno registrado."); txtNombre.setText(""); txtDireccion.setText(""); }
            catch(SQLException ex){ JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE); }
        });
    }
}
