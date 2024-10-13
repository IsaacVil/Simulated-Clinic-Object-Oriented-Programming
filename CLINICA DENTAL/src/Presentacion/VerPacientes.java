/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import Conceptos.paciente;
import Util.XML_PACIENTES;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author taqui
 */
public class VerPacientes extends javax.swing.JFrame {

    /**
     * Creates new form VerPacientes
     */
    public VerPacientes() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        llenartabla();
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    actualizarCamposPaciente();
                }
            }
        });
        setSize(1024, 768); //Resolucion de la pantalla
        setTitle("PACIENTES");
        setResizable(false);
    }
    
    private void llenartabla(){
        ArrayList<paciente> pacientes = XML_PACIENTES.Cargar("src\\DATA\\pacientes.xml");
        Vector<String> columnanombre = new Vector<String>();
        columnanombre.addElement("ID");
        columnanombre.addElement("NOMBRE");
        columnanombre.addElement("TELEFONO");
        columnanombre.addElement("EMAIL");
        Vector<Vector> rowData = new Vector<Vector>();
        for (paciente p : pacientes){
            Vector<String> row = new Vector<String>();
            row.addElement(p.getId());
            row.addElement(p.getNombre());
            row.addElement(p.getTelefono());
            row.addElement(p.getEmail());
            rowData.addElement(row);
        }
        
        DefaultTableModel m = new DefaultTableModel(rowData, columnanombre) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // Ninguna celda es editable
            }
        };

        
        
        this.jTable1.setModel(m);
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        AreaID = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        AreaNombre = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        AreaTelefono = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        AreaMail = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PACIENTES");
        setBackground(new java.awt.Color(204, 204, 255));
        setPreferredSize(new java.awt.Dimension(1024, 768));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "TELEFONO", "MAIL"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel1.setText("Lista de Clientes");

        jButton1.setText("Nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Modificar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Borrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 32)); // NOI18N
        jLabel2.setText("ID");

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 32)); // NOI18N
        jLabel3.setText("Nombre");

        AreaID.setColumns(10);
        AreaID.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        AreaID.setRows(1);
        jScrollPane3.setViewportView(AreaID);

        AreaNombre.setColumns(10);
        AreaNombre.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        AreaNombre.setRows(1);
        jScrollPane4.setViewportView(AreaNombre);

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 32)); // NOI18N
        jLabel4.setText("Email");

        AreaTelefono.setColumns(10);
        AreaTelefono.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        AreaTelefono.setRows(1);
        jScrollPane5.setViewportView(AreaTelefono);

        AreaMail.setColumns(10);
        AreaMail.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        AreaMail.setRows(1);
        jScrollPane6.setViewportView(AreaMail);

        jLabel5.setFont(new java.awt.Font("sansserif", 0, 32)); // NOI18N
        jLabel5.setText("Telefono");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(83, 83, 83)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel3)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel4)))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane6)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(29, 29, 29))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 103, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 847, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane6)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void actualizarCamposPaciente() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) //Aqui sabemos cual esta elegida entonces podemos cambiar todo SI ES -1 NO SE A ELEGIDO NADA
        {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            String id = (String) model.getValueAt(selectedRow, 0);
            String nombre = (String) model.getValueAt(selectedRow, 1);
            String telefono = (String) model.getValueAt(selectedRow, 2);
            String email = (String) model.getValueAt(selectedRow, 3);

            AreaID.setText(id);
            AreaNombre.setText(nombre);
            AreaTelefono.setText(telefono);
            AreaMail.setText(email);
        }
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ArrayList<paciente> pacientes = XML_PACIENTES.Cargar("src\\DATA\\pacientes.xml");
        if (true) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            boolean encontrado = false;
            String id = AreaID.getText();
            String nombre = AreaNombre.getText();
            String telefono = AreaTelefono.getText();
            String email = AreaMail.getText();

            try {
                int idNumero = Integer.parseInt(id);
                for (paciente p : pacientes) {
                    if (p.getId().equals(id)) {
                        JOptionPane.showMessageDialog(this, "El ID seleccionado ya existe: " + id, "Error", JOptionPane.ERROR_MESSAGE);
                        encontrado = true;
                        break;
                    }
                }
                
                if (!encontrado){
                    paciente pac = new paciente(id, nombre, telefono, email);
                    pacientes.add(pac);

                    Object[] newRow = {id, nombre, telefono, email};
                    model.addRow(newRow); 
                    JOptionPane.showMessageDialog(this, "Paciente con ID " + id + " agregado correctamente.");
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un numero (No más de 9 digitos)", "Error", JOptionPane.ERROR_MESSAGE);
            }

            XML_PACIENTES.Guardar("src\\DATA\\pacientes.xml", pacientes);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ArrayList<paciente> pacientes = XML_PACIENTES.Cargar("src\\DATA\\pacientes.xml");
        String id = AreaID.getText();
        String nombre = AreaNombre.getText();
        String telefono = AreaTelefono.getText();
        String email = AreaMail.getText();

        boolean encontrado = false;
        DefaultTableModel mesa = (DefaultTableModel) jTable1.getModel();

        for (paciente p : pacientes) {
            if (p.getId().equals(id)) {
                p.setNombre(nombre);
                p.setTelefono(telefono);
                p.setEmail(email);
                encontrado = true;
                int linea = -1;
                for (int i = 0; i < mesa.getRowCount(); i++) {
                    if (mesa.getValueAt(i, 0).equals(id)) { 
                        linea = i;
                        break;
                    }
                }
                if (linea != -1) {
                    mesa.setValueAt(id, linea, 0);
                    mesa.setValueAt(nombre, linea, 1);
                    mesa.setValueAt(telefono, linea, 2);
                    mesa.setValueAt(email, linea, 3);
                }
                JOptionPane.showMessageDialog(this, "Paciente con ID " + id + " modificado correctamente.");
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(this, "No se permite modificar el ID de un usuario inexistente", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            XML_PACIENTES.Guardar("src\\DATA\\pacientes.xml", pacientes);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ArrayList<paciente> pacientes = XML_PACIENTES.Cargar("src\\DATA\\pacientes.xml");
        if (true) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            boolean encontrado = false;
            String id = AreaID.getText();

            try {
                int idNumero = Integer.parseInt(id);

                for (int i = 0; i < pacientes.size(); i++) {
                    paciente p = pacientes.get(i);
                    if (p.getId().equals(id)) {
                        pacientes.remove(i);
                        model.removeRow(i);
                        encontrado = true;
                        JOptionPane.showMessageDialog(this, "Paciente con ID " + id + " eliminado correctamente.");
                        break; 
                    }
                }

                if (!encontrado) {
                    JOptionPane.showMessageDialog(this, "El ID seleccionado no existe: " + id, "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
            }

            XML_PACIENTES.Guardar("src\\DATA\\pacientes.xml", pacientes);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VerPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerPacientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AreaID;
    private javax.swing.JTextArea AreaMail;
    private javax.swing.JTextArea AreaNombre;
    private javax.swing.JTextArea AreaTelefono;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
