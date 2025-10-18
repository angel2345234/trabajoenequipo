package com.academico.ui;

import com.academico.dao.AlumnoDAO;
import com.academico.util.PasswordUtil;

import javax.swing.*;
import java.awt.*;

public class VentanaLogin extends JFrame {
    private final JTextField txtUsuario = new JTextField(18);
    private final JPasswordField txtContrasena = new JPasswordField(18);
    private final JButton btnIngresar = new JButton("Ingresar");

    public VentanaLogin() {
        setTitle("Login - Control Académico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(6,6,6,6);
        gc.anchor = GridBagConstraints.WEST;

        gc.gridx=0; gc.gridy=0; p.add(new JLabel("Usuario (Nombre):"), gc);
        gc.gridx=1; p.add(txtUsuario, gc);

        gc.gridx=0; gc.gridy=1; p.add(new JLabel("Contraseña:"), gc);
        gc.gridx=1; p.add(txtContrasena, gc);

        gc.gridx=1; gc.gridy=2; gc.anchor = GridBagConstraints.EAST;
        p.add(btnIngresar, gc);

        add(p);
        pack();

        btnIngresar.addActionListener(e -> intentarLogin());
        getRootPane().setDefaultButton(btnIngresar); // Enter para enviar
    }

    private void intentarLogin() {
        String usuario = txtUsuario.getText().trim();
        String passPlano = new String(txtContrasena.getPassword()).trim();

        if (usuario.isEmpty() || passPlano.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa usuario y contraseña.");
            return;
        }

        String hash = PasswordUtil.sha256(passPlano);
        try {
            boolean ok = new AlumnoDAO().validarLogin(usuario, hash);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Bienvenido, " + usuario + "!");
                // Aquí puedes abrir tu menú principal y cerrar el login:
                // new App().main(null);  // si tu App tiene main estático
                // o:
                new MenuPrincipal().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al validar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Menú mínimo para ejemplo (puedes reemplazar por tu App existente)
    public static class MenuPrincipal extends JFrame {
        public MenuPrincipal() {
            setTitle("Menú - Control Académico");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            JButton btnAlumnos = new JButton("Registro Alumno");
            JButton btnCursos  = new JButton("Cursos");
            btnAlumnos.addActionListener(e -> new VentanaRegistroAlumno().setVisible(true));
            btnCursos.addActionListener(e -> new VentanaCursos().setVisible(true));
            JPanel p = new JPanel(); p.add(btnAlumnos); p.add(btnCursos);
            add(p); pack();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaLogin().setVisible(true));
    }
}
