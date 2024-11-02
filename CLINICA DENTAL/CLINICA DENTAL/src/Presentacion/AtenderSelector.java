/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Presentacion;

import Conceptos.estado;
import Conceptos.medico;
import Conceptos.paciente;
import Conceptos.servicio;
import Conceptos.solicitud;
import Util.XML_ESTADOS;
import Util.XML_MEDICOS;
import Util.XML_PACIENTES;
import Util.XML_SERVICIOS;
import Util.XML_SOLICITUDES;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author taqui
 */
public class AtenderSelector extends javax.swing.JDialog {
    private static String idservicio;
    public AtenderSelector(java.awt.Frame parent, boolean modal, String idservicio) {
        super(parent, modal);
        this.idservicio = idservicio;
        initComponents();
        cargarServiciosEnTabla();
    }

    private void cargarServiciosEnTabla() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de agregar datos
        ArrayList<paciente> pacientes = XML_PACIENTES.Cargar("src\\DATA\\pacientes.xml");
        ArrayList<servicio> servicios = XML_SERVICIOS.Cargar("src\\DATA\\servicios.xml");
        ArrayList<medico> medicos = XML_MEDICOS.Cargar("src\\DATA\\medicos.xml", servicios);
        ArrayList<estado> estados = XML_ESTADOS.Cargar("src\\DATA\\estados.xml");
        ArrayList<solicitud> solicitudes = XML_SOLICITUDES.Cargar("src\\DATA\\solicitudes.xml", pacientes, medicos, servicios, estados);
        for (solicitud soli : solicitudes) {
            if (idservicio.equals(soli.consultarId())) {  // Comparar si el ID coincide
                for (servicio s : servicios) {
                    if (soli.consultarServicio().getId().equals(s.getId())){
                        continue;
                    }
                    else{
                        if (soli.consultarOtrosservicios() != null){
                            boolean isChecked = soli.consultarOtrosservicios().contains(s);
                            model.addRow(new Object[]{isChecked, s.getId(), s.getNombre()}); // Aquí se agrega el valor booleano
                        }
                        else{
                            boolean isChecked = false;
                            model.addRow(new Object[]{isChecked, s.getId(), s.getNombre()});
                        }
                    }
                }
                break;
            }
        }
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Seleccion", "ID", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Aceptar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        ArrayList<paciente> pacientes = XML_PACIENTES.Cargar("src\\DATA\\pacientes.xml");
        ArrayList<servicio> servicios = XML_SERVICIOS.Cargar("src\\DATA\\servicios.xml");
        ArrayList<medico> medicos = XML_MEDICOS.Cargar("src\\DATA\\medicos.xml", servicios);
        ArrayList<estado> estados = XML_ESTADOS.Cargar("src\\DATA\\estados.xml");
        ArrayList<solicitud> solicitudes = XML_SOLICITUDES.Cargar("src\\DATA\\solicitudes.xml", pacientes, medicos, servicios, estados);
        
        
        ArrayList<servicio> serviciosSeleccionados = new ArrayList<>();

        for (int i = 0; i < model.getRowCount(); i++) {
            boolean isChecked = (Boolean) model.getValueAt(i, 0);
            String servicioId = (String) model.getValueAt(i, 1);

            if (isChecked) {
                for (servicio s : servicios) {
                    if (servicioId.equals(s.getId())) {
                        serviciosSeleccionados.add(s);
                        break;
                    }
                }
            }
        }

        for (solicitud soli : solicitudes){
            if (soli.consultarId().equals(idservicio)){
                soli.atenderOtrosservicios(serviciosSeleccionados);
            }
        }

        XML_SOLICITUDES.Guardar("src\\DATA\\solicitudes.xml", solicitudes);
        JOptionPane.showMessageDialog(this, "Se guardaron los cambios correctamente.");
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(AtenderSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AtenderSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AtenderSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AtenderSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AtenderSelector dialog = new AtenderSelector(new javax.swing.JFrame(), true, idservicio);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}