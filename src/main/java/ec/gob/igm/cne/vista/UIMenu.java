package ec.gob.igm.cne.vista;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import ec.gob.igm.cne.vista.IMenu;
//import java.io.File;

/**
 * Vista principal del Menu
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */
public class UIMenu extends javax.swing.JFrame
{
    private IMenu interfaz;

    public UIMenu(IMenu interfaz)
    {
        initComponents();
        this.setVisible(true);
        this.setTitle("Menú - Sistema para Generación de Etiquetas de Papeletas y Documentos");
        setLocationRelativeTo(null);
        this.interfaz = interfaz;
        interfaz.cargar(txtNombre, btnEtiquetasP, btnEtiquetasD, btnREtiquetasP, btnREtiquetasD, btnActasP, btnActasD, btnActasP, btnActasD);
        btnActasP.setVisible(false);
        btnEtiquetasP.setVisible(false);
        btnREtiquetasP.setVisible(false);
        btnLoteP.setVisible(false);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnEtiquetasP = new javax.swing.JButton();
        btnLoteP = new javax.swing.JButton();
        btnREtiquetasD = new javax.swing.JButton();
        btnREtiquetasP = new javax.swing.JButton();
        btnEtiquetasD = new javax.swing.JButton();
        btnLoteD = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnActasD = new javax.swing.JButton();
        btnActasP = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itmConfig = new javax.swing.JMenuItem();
        itmCerrar = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        itmAdminUsuarios = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itmManual = new javax.swing.JMenuItem();
        itmAcerca = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setOpaque(false);

        btnEtiquetasP.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        btnEtiquetasP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/papeleta.png"))); // NOI18N
        btnEtiquetasP.setText("ETIQUETAS PAPELETAS");
        btnEtiquetasP.setToolTipText("");
        btnEtiquetasP.setEnabled(false);
        btnEtiquetasP.setMargin(new java.awt.Insets(2, 12, 2, 12));
        btnEtiquetasP.setName("btnGeneraP"); // NOI18N
        btnEtiquetasP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEtiquetasPActionPerformed(evt);
            }
        });

        btnLoteP.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        btnLoteP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/producto.png"))); // NOI18N
        btnLoteP.setText("LOTE PAPELETAS");
        btnLoteP.setToolTipText("");
        btnLoteP.setEnabled(false);
        btnLoteP.setName("btnLoteP"); // NOI18N
        btnLoteP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLotePActionPerformed(evt);
            }
        });

        btnREtiquetasD.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnREtiquetasD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tag.png"))); // NOI18N
        btnREtiquetasD.setText("REIMPRESION ETIQUETAS");
        btnREtiquetasD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnREtiquetasDActionPerformed(evt);
            }
        });

        btnREtiquetasP.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        btnREtiquetasP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tag.png"))); // NOI18N
        btnREtiquetasP.setText("REIMPRESION E. PAPELETAS");
        btnREtiquetasP.setEnabled(false);
        btnREtiquetasP.setName("btnReimpresionP"); // NOI18N
        btnREtiquetasP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnREtiquetasPActionPerformed(evt);
            }
        });

        btnEtiquetasD.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnEtiquetasD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/documento_35px.png"))); // NOI18N
        btnEtiquetasD.setText("GENERACION ETIQUETAS");
        btnEtiquetasD.setName("btnGeneraDocumentos"); // NOI18N
        btnEtiquetasD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEtiquetasDActionPerformed(evt);
            }
        });

        btnLoteD.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnLoteD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/producto.png"))); // NOI18N
        btnLoteD.setText("GENERACION LOTES");
        btnLoteD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoteDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnLoteP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEtiquetasP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnREtiquetasP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(btnEtiquetasD, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLoteD, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(btnREtiquetasD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 116, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEtiquetasP, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnREtiquetasP, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLoteP, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEtiquetasD, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnREtiquetasD, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLoteD, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Consultas"));
        jPanel4.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.disabledShadow"));
        jPanel4.setToolTipText("");
        jPanel4.setOpaque(false);

        btnActasD.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnActasD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/documento_24px.png"))); // NOI18N
        btnActasD.setText("IMPRESION DE ACTAS");
        btnActasD.setToolTipText("");
        btnActasD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActasDActionPerformed(evt);
            }
        });

        btnActasP.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnActasP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/papeleta.png"))); // NOI18N
        btnActasP.setText("ACTAS PAPELETAS");
        btnActasP.setEnabled(false);
        btnActasP.setName("btnActasP"); // NOI18N
        btnActasP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActasPActionPerformed(evt);
            }
        });

        txtNombre.setEditable(false);
        txtNombre.setColumns(20);
        txtNombre.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtNombre.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Bienvenido(a):");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnActasP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActasD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(btnActasP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnActasD, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, -1, 260));

        jPanel2.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 82, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, 230, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo2.jpeg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 420, 350, 60));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("DOCUMENTOS ELECTORALES");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 310, -1));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("CopyRight IGM 2021.02.18 V1.4.033 GitHub");
        jLabel5.setToolTipText("");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 400, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon("D:\\temp\\CNE.jpg")); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 470));

        jMenu1.setText("Archivo");

        itmConfig.setText("Configuración");
        itmConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmConfigActionPerformed(evt);
            }
        });
        jMenu1.add(itmConfig);

        itmCerrar.setText("Cerrar Sesión");
        itmCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmCerrarActionPerformed(evt);
            }
        });
        jMenu1.add(itmCerrar);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Usuarios");

        itmAdminUsuarios.setText("Administrar Usuarios");
        itmAdminUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmAdminUsuariosActionPerformed(evt);
            }
        });
        jMenu3.add(itmAdminUsuarios);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Ayuda");

        itmManual.setText("Ver manual");
        jMenu2.add(itmManual);

        itmAcerca.setText("Acerca de");
        itmAcerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmAcercaActionPerformed(evt);
            }
        });
        jMenu2.add(itmAcerca);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEtiquetasPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEtiquetasPActionPerformed
        interfaz.generaEtiquetasPapeletas();
    }//GEN-LAST:event_btnEtiquetasPActionPerformed

    private void btnLoteDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoteDActionPerformed
        interfaz.lotesDocumentos();
    }//GEN-LAST:event_btnLoteDActionPerformed

    private void btnREtiquetasPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnREtiquetasPActionPerformed
        //interfaz.documento();
    }//GEN-LAST:event_btnREtiquetasPActionPerformed

    private void btnEtiquetasDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEtiquetasDActionPerformed
        interfaz.generaEtiquetasDocumentos();
    }//GEN-LAST:event_btnEtiquetasDActionPerformed

    private void btnLotePActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnLotePActionPerformed
    {//GEN-HEADEREND:event_btnLotePActionPerformed
        interfaz.lotesPapeletas();
    }//GEN-LAST:event_btnLotePActionPerformed

    private void itmCerrarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_itmCerrarActionPerformed
    {//GEN-HEADEREND:event_itmCerrarActionPerformed
        interfaz.cerrarSesion();
    }//GEN-LAST:event_itmCerrarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing
        interfaz.cerrarSesion();
    }//GEN-LAST:event_formWindowClosing

    private void btnREtiquetasDActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnREtiquetasDActionPerformed
    {//GEN-HEADEREND:event_btnREtiquetasDActionPerformed
        interfaz.reimprimeEtiquetasDocumentos();
    }//GEN-LAST:event_btnREtiquetasDActionPerformed

    private void itmAcercaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_itmAcercaActionPerformed
    {//GEN-HEADEREND:event_itmAcercaActionPerformed
        
        try{
            ImageIcon icon = new ImageIcon(getClass().getResource("/igm2.png"));
            JOptionPane.showMessageDialog(null, "Sistema de Generación de Etiquetas\npara Papeletas y Documentos Electorales\n© 2020 INSTITUTO GEOGRÁFICO MILITAR","Acerca de", JOptionPane.INFORMATION_MESSAGE, icon);
        }catch(NullPointerException ex){
            JOptionPane.showMessageDialog(null, "Sistema de Generación de Etiquetas\npara Papeletas y Documentos Electorales\n© 2020 INSTITUTO GEOGRÁFICO MILITAR","Acerca de", JOptionPane.INFORMATION_MESSAGE);
        }        
    }//GEN-LAST:event_itmAcercaActionPerformed

    private void itmConfigActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_itmConfigActionPerformed
    {//GEN-HEADEREND:event_itmConfigActionPerformed
        //interfaz.configuracion();
    }//GEN-LAST:event_itmConfigActionPerformed

    private void itmAdminUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmAdminUsuariosActionPerformed
        // TODO add your handling code here:
        FrmAdminUsuarios frmAdminUsuarios = new FrmAdminUsuarios();
        frmAdminUsuarios.setVisible(true);
        frmAdminUsuarios.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_itmAdminUsuariosActionPerformed

    private void btnActasPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActasPActionPerformed
        interfaz.actasPapeletas();
    }//GEN-LAST:event_btnActasPActionPerformed

    private void btnActasDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActasDActionPerformed
        interfaz.actasDocumentos();
    }//GEN-LAST:event_btnActasDActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActasD;
    private javax.swing.JButton btnActasP;
    private javax.swing.JButton btnEtiquetasD;
    private javax.swing.JButton btnEtiquetasP;
    private javax.swing.JButton btnLoteD;
    private javax.swing.JButton btnLoteP;
    private javax.swing.JButton btnREtiquetasD;
    private javax.swing.JButton btnREtiquetasP;
    private javax.swing.JMenuItem itmAcerca;
    private javax.swing.JMenuItem itmAdminUsuarios;
    private javax.swing.JMenuItem itmCerrar;
    private javax.swing.JMenuItem itmConfig;
    private javax.swing.JMenuItem itmManual;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
