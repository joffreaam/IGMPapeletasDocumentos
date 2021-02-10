/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.vista;

import ec.gob.igm.cne.dao.TBaseDAO;
import ec.gob.igm.cne.dao.TProvinciaDAO;
import ec.gob.igm.cne.dao.TDignidadDAO;
import ec.gob.igm.cne.documentos.Item;
import ec.gob.igm.cne.entidades.TProvincia;
import ec.gob.igm.cne.entidades.TDignidad;
import ec.gob.igm.cne.utils.ReportGenarator;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author desarrollo
 */
public class FrmActasPapeletas extends javax.swing.JDialog {
    // Add Joffre A
    private List<Object[]> lstdignidades;
    private List<Object[]> lstProvincias;
    private List<BigDecimal> lstLotes;
    private TBaseDAO base;
    // ------ **** -----
    private TProvinciaDAO provinciaDAO;
    private TDignidadDAO dignidadDAO;
    
    //private static final String ESTADO = "DESPACHADA";
    private static final String ESTADO = "CERRADO";
    private static final String TIPO = "PAPELETA";

    /**
     * Creates new form FrmActasPapeletas
     */
    public FrmActasPapeletas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        //add Joffre A
        this.base=new TBaseDAO();
        this.lstdignidades=new ArrayList();
        this.lstProvincias= new ArrayList();
        this.lstLotes= new ArrayList();
        // ------- ***
        
        dignidadDAO = new TDignidadDAO();
        provinciaDAO = new TProvinciaDAO();

        setTitle("Generación de Actas de Papeletas por rango de Lotes");
        
        cargarDignidad(cmbDignidad);
        cargarProvincia(cmbProvincia1);
        
//         List<TDignidad> listaDignidad = dignidadDAO.listarTodas();
//        String[] arrayDignidad = new String[listaDignidad.size()];
//        for (int i = 0; i < arrayDignidad.length; i++) {
//            arrayDignidad[i] = listaDignidad.get(i).toString();
//        }
//        cmbDignidad.setModel(new DefaultComboBoxModel(arrayDignidad));
               
//        List<TProvincia> listaProvincias = provinciaDAO.listarProvinciasXEstadoYTipoDeLote(ESTADO, TIPO);        
//        String[] arrayProvincias = new String[listaProvincias.size()];
//        for (int i = 0; i < arrayProvincias.length; i++) {
//            arrayProvincias[i] = listaProvincias.get(i).toString();
//        }
//        cmbProvincia1.setModel(new DefaultComboBoxModel(arrayProvincias));

//        if (arrayProvincias.length > 0) {
//            if (cmbDignidad.getSelectedItem().toString().contains("ecuador".toUpperCase())) {
//                spnDesde.setEnabled(false);
//                spnHasta.setEnabled(false);
//            }
//        }
//
//        cmbDignidad.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if (e.getStateChange() == ItemEvent.SELECTED) {
//                    Object item = e.getItem();
//                    if (item != null) {
//                        String idProvinciaStr = item.toString().split("-")[0];
//                        int idProvincia = Integer.parseInt(idProvinciaStr);
//                        List<BigDecimal> listaLotes = provinciaDAO.listarLotesXEstadoTipoYProvincia(idProvincia, TIPO, ESTADO);
//                        if (listaLotes != null && listaLotes.size() > 0) {
//                            if (!spnDesde.isEnabled()) {
//                                spnDesde.setEnabled(true);
//                            }
//                            if (!spnHasta.isEnabled()) {
//                                spnHasta.setEnabled(true);
//                            }
//                            SpinnerListModel spinnerModelDesde = new SpinnerListModel(listaLotes);
//                            SpinnerListModel spinnerModelHasta = new SpinnerListModel(listaLotes);
//                            spnDesde.setModel(spinnerModelDesde);
//                            spnHasta.setModel(spinnerModelHasta);
//                            setSpinnerNonEditable(spnDesde);
//                            setSpinnerNonEditable(spnHasta);
//                        } else {
//                            spnDesde.setEnabled(false);
//                            spnHasta.setEnabled(false);
//                        }
//                    }
//                }
//            }
//        });
    }

    public void setSpinnerNonEditable(JSpinner spinner) {
        JFormattedTextField formatter = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
        formatter.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbDignidad = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        spnDesde = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        spnHasta = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtEncargadoCNE = new javax.swing.JTextField();
        txtEncargadoIGM = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnGenerar = new javax.swing.JButton();
        dchFecha = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        cmbProvincia1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setLabelFor(cmbDignidad);
        jLabel1.setText("Provincia:");

        cmbDignidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDignidadActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lote"));

        jLabel2.setLabelFor(spnDesde);
        jLabel2.setText("Desde:");

        jLabel3.setLabelFor(spnHasta);
        jLabel3.setText("Hasta:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(spnDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(spnHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(spnDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(spnHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setLabelFor(txtEncargadoCNE);
        jLabel4.setText("Encargado CNE:");

        jLabel5.setLabelFor(txtEncargadoIGM);
        jLabel5.setText("Encargado IGM:");

        txtEncargadoCNE.setText("Lcda Laura Ayala");
        txtEncargadoCNE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEncargadoCNEActionPerformed(evt);
            }
        });

        txtEncargadoIGM.setText("Ing. María Eugnia Torres");

        jLabel6.setText("Fecha:");

        btnGenerar.setText("Generar Acta");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        dchFecha.setToolTipText("");

        jLabel7.setLabelFor(cmbDignidad);
        jLabel7.setText("Dignidad:");

        cmbProvincia1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProvincia1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addComponent(btnGenerar))
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtEncargadoIGM)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dchFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtEncargadoCNE)))
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbDignidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(65, 65, 65)
                    .addComponent(cmbProvincia1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(20, 20, 20)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmbDignidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEncargadoCNE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEncargadoIGM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dchFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(btnGenerar)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(54, 54, 54)
                    .addComponent(cmbProvincia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(232, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        //add Joffre Alava
        Item provinciaSeleccionada = (Item)cmbProvincia1.getSelectedItem();
        Integer idProvincia = provinciaSeleccionada.getId();//cmbProvincia1.getSelectedItem().toString().substring(0, 1);
        
        Item dignidadSeleccionada = (Item)cmbDignidad.getSelectedItem();
        Integer idDignidad = dignidadSeleccionada.getId();//cmbProvincia1.getSelectedItem().toString().substring(0, 1);
        //---- -------- ---******** ----
        
        Integer loteMin = ((BigDecimal) spnDesde.getValue()).intValue();
        Integer loteMax = ((BigDecimal) spnHasta.getValue()).intValue();
        
        Date fecha = dchFecha.getDate();
        String igmEntrega = txtEncargadoIGM.getText();
        String cneRecibe = txtEncargadoCNE.getText();
        // Validaciones
        Map<String, Object> params = new HashMap<>();
        String coneccion =new String ("CNEELECCIONES");
        params.put("REPORT_CONNECTION",coneccion);
        params.put("ID_DIGNIDAD", idDignidad);  // Comentado por Joffre A ====  params.put("ID_DIGNIDAD", "7");
        params.put("ID_PROVINCIA", idProvincia);        
        params.put("IGM_ENTREGA", igmEntrega);
        params.put("CNE_RECIBE", cneRecibe);
        params.put("LOTE_MIN", loteMin);
        params.put("LOTE_MAX", loteMax);
        params.put("FECHA", fecha);
        //TODO: establecer el ID_DIGNIDAD para el reporte
        
        ReportGenarator reporteGenerator = new ReportGenarator();
        try {
            JasperPrint jp;
            if(TIPO.equals("PAPELETA")){
                jp = reporteGenerator.genarateReport(ReportGenarator.ACTAS_PAPELETAS, params);
            }else{
                jp = reporteGenerator.genarateReport(ReportGenarator.ACTAS_DOCUMENTOS, params);
            }
            JasperViewer jasperViewer = new JasperViewer(jp, false);
            JDialog dialog = new JDialog(this);//the owner
            dialog.setContentPane(jasperViewer.getContentPane());
            dialog.setSize(jasperViewer.getSize());
            dialog.setTitle("Vista Previa de Acta de Entrega de Papeletas Electorales");
//            dialog.setIconImage(Toolkit.getDefaultToolkit().getImage(
//                    getClass().getResource("URL IMG")));
            dialog.setVisible(true);

        } catch (JRException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error al generar reporte", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(FrmActasPapeletas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnGenerarActionPerformed

    private void cmbDignidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDignidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDignidadActionPerformed

    private void cmbProvincia1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProvincia1ActionPerformed
        // TODO add your handling code here:
        List<BigDecimal> listaLotes = cargarLotesXEstadoTipoYProvincia(cmbProvincia1);
        if (listaLotes != null && listaLotes.size() > 0) {
            if (!spnDesde.isEnabled()) {
                spnDesde.setEnabled(true);
            }
            if (!spnHasta.isEnabled()) {
                spnHasta.setEnabled(true);
            }
            SpinnerListModel spinnerModelDesde = new SpinnerListModel(listaLotes);
            SpinnerListModel spinnerModelHasta = new SpinnerListModel(listaLotes);
            spnDesde.setModel(spinnerModelDesde);
            spnHasta.setModel(spinnerModelHasta);
            setSpinnerNonEditable(spnDesde);
            setSpinnerNonEditable(spnHasta);
        } else {
            spnDesde.setEnabled(false);
            spnHasta.setEnabled(false);
        }
        
        
    }//GEN-LAST:event_cmbProvincia1ActionPerformed

    private void txtEncargadoCNEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEncargadoCNEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEncargadoCNEActionPerformed

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
            java.util.logging.Logger.getLogger(FrmActasPapeletas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmActasPapeletas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmActasPapeletas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmActasPapeletas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmActasPapeletas dialog = new FrmActasPapeletas(new javax.swing.JFrame(), true);
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

    // add Joffre A
    public void cargarDignidad(JComboBox cmbDignidad) {
       
       this.lstdignidades=base.cargarDignidades();
       for (Object[] dignidades : lstdignidades) {
            Item dignidad = new Item(((BigDecimal)dignidades[0]).intValue(),dignidades[1].toString());
            cmbDignidad.addItem(dignidad); 
        }
    }  
    
    public void cargarProvincia(JComboBox cmbProvincia1) {
        
        this.lstProvincias = base.cargarProvinciasXEstadoYTipoDeLote(ESTADO, TIPO, ESTADO);
        for (Object[] provincias : lstProvincias) {
            Item provincia = new Item(((BigDecimal)provincias[0]).intValue(),provincias[1].toString());
            cmbProvincia1.addItem(provincia); 
        }
    }
    
    public List<BigDecimal> cargarLotesXEstadoTipoYProvincia(JComboBox cmbProvincia1) {
        Item provinciaSeleccionada = (Item)cmbProvincia1.getSelectedItem();
        Integer idProvincia = provinciaSeleccionada.getId();
        provinciaDAO = new TProvinciaDAO();
        
        this.lstLotes = provinciaDAO.listarLotesXEstadoTipoYProvincia(idProvincia, TIPO, ESTADO);
        SpinnerListModel spinnerModelDesde = new SpinnerListModel(lstLotes);
        SpinnerListModel spinnerModelHasta = new SpinnerListModel(lstLotes);
        spnDesde.setModel(spinnerModelDesde);
        spnHasta.setModel(spinnerModelHasta);
        setSpinnerNonEditable(spnDesde);
        setSpinnerNonEditable(spnHasta);

        return this.lstLotes;
    }
    
    ///// -------- hasta aca
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerar;
    private javax.swing.JComboBox<String> cmbDignidad;
    private javax.swing.JComboBox<String> cmbProvincia1;
    private com.toedter.calendar.JDateChooser dchFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner spnDesde;
    private javax.swing.JSpinner spnHasta;
    private javax.swing.JTextField txtEncargadoCNE;
    private javax.swing.JTextField txtEncargadoIGM;
    // End of variables declaration//GEN-END:variables
}
