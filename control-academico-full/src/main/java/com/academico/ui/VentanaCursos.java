package com.academico.ui;

import com.academico.dao.CursoDAO;
import com.academico.modelo.Curso;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentanaCursos extends JFrame {
    private final JTextField txtNombre = new JTextField(18);
    private final JTextField txtDesc   = new JTextField(18);

    private final JButton btnAgregar    = new JButton("Agregar");
    private final JButton btnRefrescar  = new JButton("Refrescar");
    private final JButton btnEliminar   = new JButton("Eliminar");

    private final DefaultListModel<String> model = new DefaultListModel<>();
    private final JList<String> lista = new JList<>(model);

    // Mantiene los IDs en el mismo orden que la lista para eliminar correctamente
    private final java.util.List<Integer> ids = new ArrayList<>();

    public VentanaCursos() {
        setTitle("Cursos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel superior compacto
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        top.add(new JLabel("Nombre:"));
        top.add(txtNombre);
        top.add(new JLabel("Descripción:"));
        top.add(txtDesc);
        top.add(btnAgregar);
        top.add(btnRefrescar);
        top.add(btnEliminar);

        // Área de lista con tamaño preferido compacto
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setPreferredSize(new Dimension(480, 140)); // <- ajusta alto (p.ej. 120/160/200)

        // Layout principal
        setLayout(new BorderLayout());
        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // Acciones
        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String desc   = txtDesc.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingresa el nombre del curso.");
                return;
            }
            try {
                new CursoDAO().registrar(new Curso(nombre, desc));
                txtNombre.setText("");
                txtDesc.setText("");
                refrescar();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        btnRefrescar.addActionListener(e -> refrescar());

        btnEliminar.addActionListener(e -> {
            int idx = lista.getSelectedIndex();
            if (idx < 0) {
                JOptionPane.showMessageDialog(this, "Selecciona un curso para eliminar.");
                return;
            }
            int idCurso = ids.get(idx);
            int resp = JOptionPane.showConfirmDialog(this,
                    "¿Eliminar el curso seleccionado?", "Confirmar",
                    JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                try {
                    new CursoDAO().eliminar(idCurso);
                    refrescar();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                }
            }
        });

        // Carga inicial
        refrescar();

        // Ajusta al contenido (mejor que setSize)
        pack();
    }

    private void refrescar() {
        model.clear();
        ids.clear();
        try {
            List<Curso> cs = new CursoDAO().listar();
            for (Curso c : cs) {
                ids.add(c.getId());
                String desc = (c.getDescripcion() == null || c.getDescripcion().isBlank())
                        ? "" : c.getDescripcion();
                model.addElement("#" + c.getId() + " - " + c.getNombre() + " | " + desc);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al listar: " + e.getMessage());
        }
    }
}

