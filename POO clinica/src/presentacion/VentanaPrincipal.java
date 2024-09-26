package presentacion;

import javax.swing.JOptionPane;

public class VentanaPrincipal extends javax.swing.JFrame {
    public VentanaPrincipal() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        ArchivoPop = new javax.swing.JMenu();
        salir = new javax.swing.JMenuItem();
        ServiciosPop = new javax.swing.JMenu();
        UtilPop = new javax.swing.JMenu();
        mclientes = new javax.swing.JMenu();
        Mclientes = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CLÍNICA");

        ArchivoPop.setText("Archivo");

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        ArchivoPop.add(salir);

        jMenuBar1.add(ArchivoPop);

        ServiciosPop.setText("Servicios");
        jMenuBar1.add(ServiciosPop);

        UtilPop.setText("Util");

        mclientes.setText("Pop up Cliente");
        mclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mclientesActionPerformed(evt);
            }
        });
        UtilPop.add(mclientes);

        Mclientes.setText("Mostrar Clientes");
        Mclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MclientesActionPerformed(evt);
            }
        });
        UtilPop.add(Mclientes);

        jMenuBar1.add(UtilPop);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salirActionPerformed

    private void mclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mclientesActionPerformed

    }//GEN-LAST:event_mclientesActionPerformed

    private void MclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MclientesActionPerformed
        try{
            VentanaVerClientes mostrarclientes = new VentanaVerClientes(VentanaPrincipal.this, true);
            mostrarclientes.setVisible(true);
        }catch (UnsupportedOperationException ex) {
            JOptionPane.showMessageDialog(null, "No se pudieron cargar los clientes \n" + "Contacte con soporte \n" + ex, "Error con la pantalla", JOptionPane.ERROR_MESSAGE);
        }  
    }//GEN-LAST:event_MclientesActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu ArchivoPop;
    private javax.swing.JMenuItem Mclientes;
    private javax.swing.JMenu ServiciosPop;
    private javax.swing.JMenu UtilPop;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu mclientes;
    private javax.swing.JMenuItem salir;
    // End of variables declaration//GEN-END:variables
}
