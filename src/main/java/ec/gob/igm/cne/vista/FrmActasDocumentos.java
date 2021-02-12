/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.vista;

import ec.gob.igm.cne.dao.TBaseDAO;
import ec.gob.igm.cne.dao.TProvinciaDAO;
import ec.gob.igm.cne.entidades.TProvincia;
import ec.gob.igm.cne.utils.ReportGenarator;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
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
import ec.gob.igm.cne.documentos.Item;
/**
 *
 * @author desarrollo
 * GITHUb: Test por Joffre A.
 */
public class FrmActasDocumentos extends javax.swing.JDialog {

private TProvinciaDAO provinciaDAO;
    private static final String ESTADO2 ="RECIBIDA";
    private static final String ESTADO = "CERRADO";
    private static final String TIPO = "DOCUMENTO";
    private List<Object[]> lstProvincias;
    private List<BigDecimal> lstLotes;
    private TBaseDAO base;
    
    /**
     * Creates new form FrmActasPapeletas
     */
    public FrmActasDocumentos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        // Add Joffre A
        this.base=new TBaseDAO();
        this.lstProvincias=new ArrayList();
        this.lstLotes=new ArrayList();
        
        ///--------- aca
        
        initComponents();
        cargarProvincia(cmbProvincia);
        provinciaDAO = new TProvinciaDAO();

        setTitle("Generación de Actas de Papeletas por rango de Lotes");
////
//        cmbProvincia.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if (e.getStateChange() == ItemEvent.SELECTED) {
//                    Object item = e.getItem();
//                    if (item != null) {                        
//                        //String idProvinciaStr = item.toString().split("-")[0];
//                        //int idProvincia = Integer.parseInt(idProvinciaStr);
//                        Item provinciaSeleccionada = (Item)cmbProvincia.getSelectedItem();
//                        int idProvincia = provinciaSeleccionada.getId();
//                        
//                        List<BigDecimal> listaLotes = provinciaDAO.listarLotesXEstadoTipoYProvincia(idProvincia, TIPO, ESTADO,ESTADO2);
//                        if (listaLotes != null && listaLotes.size() > 0) {
//                            if (!spnDesde.isEnabled()) {
//                                spnDesde.setEnabled(true);
//                            }
//                            if (!spnHasta.isEnabled()) {
//                                spnHasta.setEnabled(true);
//                            }
//                            SpinnerListModel spinnerModelDesde = new SpinnerListModel(listaLotes);
//                            SpinnerListModel spinnerModelHasta = new SpinnerListModel(listaLotes);
//                            // facilita para la impresion desde el ultimo lote generado
//                            spnHasta.setModel(spinnerModelDesde); //spnDesde.setModel(spinnerModelDesde);
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

        dchFecha.setDate(new Date());
    }

    public void setSpinnerNonEditable(JSpinner spinner) {
        JFormattedTextField formatter = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
        formatter.setEditable(false);
    }

    public boolean checkDate(final String date) {
        try {
            // ResolverStyle.STRICT for 30, 31 days checking, and also leap year.
            LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("uuuu-M-d")
                            .withResolverStyle(ResolverStyle.STRICT)
            );
            return true;
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return false;
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

        jLabel1 = new javax.swing.JLabel();
        cmbProvincia = new javax.swing.JComboBox<>();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setLabelFor(cmbProvincia);
        jLabel1.setText("Provincia:");

        cmbProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProvinciaActionPerformed(evt);
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

        txtEncargadoCNE.setText("Ing. Lasso");

        txtEncargadoIGM.setText("Ing. Yaku");

        jLabel6.setText("Fecha:");

        btnGenerar.setText("Generar Acta");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
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
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtEncargadoCNE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtEncargadoIGM))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnGenerar)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dchFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(cmbProvincia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEncargadoCNE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEncargadoIGM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(dchFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(btnGenerar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        System.out.println(dchFecha.getDate().toString());
        
        Integer idProvincia;
        int loteMin, loteMax;
        try {
            loteMin = (Integer) spnDesde.getValue();
            loteMax = (Integer) spnHasta.getValue();
        } catch (ClassCastException ex) {
            loteMin = ((BigDecimal) spnDesde.getValue()).intValue();
            loteMax = ((BigDecimal) spnHasta.getValue()).intValue();
        }
        Date fecha = dchFecha.getDate();
        String igmEntrega = txtEncargadoIGM.getText();
        String cneRecibe = txtEncargadoCNE.getText();
        // Validaciones
        if (cmbProvincia.getSelectedItem() != null && !igmEntrega.isEmpty() && !cneRecibe.isEmpty() && dchFecha.getDate() != null) {
            //add Joffre Alava
            Item provinciaSeleccionada = (Item)cmbProvincia.getSelectedItem();
            idProvincia = provinciaSeleccionada.getId();
            if (loteMin <= loteMax && loteMin > 0 && loteMax > 0) {                
                Map<String, Object> params = new HashMap<>();
                params.put("ID_PROVINCIA", idProvincia);
                params.put("LOTE_MIN", loteMin);
                params.put("LOTE_MAX", loteMax);
                params.put("IGM_ENTREGA", igmEntrega);
                params.put("CNE_RECIBE", cneRecibe);
                params.put("FECHA", fecha);
                //TODO: establecer el ID_DIGNIDAD para el reporte
                params.put("ID_DIGNIDAD", "1");
                ReportGenarator reporteGenerator = new ReportGenarator();
                try {
                    JasperPrint jp;
                    if (TIPO.equals("PAPELETA")) {
                        jp = reporteGenerator.genarateReport(ReportGenarator.ACTAS_PAPELETAS, params);
                    } else {
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
                    Logger.getLogger(FrmActasDocumentos.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "El rango de lotes proporcionado no es válido", "Rango no válido", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor llene todos los campos para poder generar el acta", "Información necesaria", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void cmbProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProvinciaActionPerformed
        // TODO add your handling code here:
        //List<BigDecimal> listaLotes = cargarLotesXEstadoTipoYProvincia(cmbProvincia);
//        if (listaLotes != null && listaLotes.size() > 0) {
//            if (!spnDesde.isEnabled()) {
//                spnDesde.setEnabled(true);
//            }
//            if (!spnHasta.isEnabled()) {
//                spnHasta.setEnabled(true);
//            }
//            SpinnerListModel spinnerModelDesde = new SpinnerListModel(listaLotes);
//            SpinnerListModel spinnerModelHasta = new SpinnerListModel(listaLotes);
//            spnDesde.setModel(spinnerModelDesde);
//            spnHasta.setModel(spinnerModelHasta);
//            setSpinnerNonEditable(spnDesde);
//            setSpinnerNonEditable(spnHasta);
//        } else {
//            spnDesde.setEnabled(false);
//            spnHasta.setEnabled(false);
//        }


// TODO add your handling code here:
        List<BigDecimal> listaLotes = cargarLotesXEstadoTipoYProvincia(cmbProvincia);
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

    }//GEN-LAST:event_cmbProvinciaActionPerformed
 
    

    // Add Joffre Alava
    public void cargarProvincia(JComboBox cmbProvincia) {
        
        this.lstProvincias = base.cargarProvinciasXEstadoYTipoDeLote(ESTADO, TIPO,ESTADO2);
        for (Object[] provincias : lstProvincias) {
            Item provincia = new Item(((BigDecimal)provincias[0]).intValue(),provincias[1].toString());
            cmbProvincia.addItem(provincia); 
        }
    }
    
    public List<BigDecimal> cargarLotesXEstadoTipoYProvincia(JComboBox cmbProvincia) {
        Item provinciaSeleccionada = (Item)cmbProvincia.getSelectedItem();
        Integer idProvincia = provinciaSeleccionada.getId();
        provinciaDAO = new TProvinciaDAO();
        
        this.lstLotes = provinciaDAO.listarLotesXEstadoTipoYProvincia(idProvincia, TIPO, ESTADO,ESTADO2 );
        SpinnerListModel spinnerModelDesde = new SpinnerListModel(lstLotes);
        SpinnerListModel spinnerModelHasta = new SpinnerListModel(lstLotes);
        spnDesde.setModel(spinnerModelDesde);
        spnHasta.setModel(spinnerModelHasta);
        setSpinnerNonEditable(spnDesde);
        setSpinnerNonEditable(spnHasta);

        return this.lstLotes;
        
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerar;
    private javax.swing.JComboBox<String> cmbProvincia;
    private com.toedter.calendar.JDateChooser dchFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner spnDesde;
    private javax.swing.JSpinner spnHasta;
    private javax.swing.JTextField txtEncargadoCNE;
    private javax.swing.JTextField txtEncargadoIGM;
    // End of variables declaration//GEN-END:variables
}
