/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.vista;

import ec.gob.igm.cne.dao.TBaseDAO;
import ec.gob.igm.cne.dao.TDignidadDAO;
import ec.gob.igm.cne.dao.TListadoDocumentosDAO;
import ec.gob.igm.cne.dao.TTipoDocumentoDAO;
import ec.gob.igm.cne.dao.TJuntaDAO;
import ec.gob.igm.cne.documentos.CodigoBarras;
import ec.gob.igm.cne.documentos.DatosJunta;
import ec.gob.igm.cne.entidades.TTipoDocumento;
import ec.gob.igm.cne.documentos.Documento;
import ec.gob.igm.cne.documentos.ResultadoVerificacion;
import ec.gob.igm.cne.documentos.Verificador;
import ec.gob.igm.cne.entidades.TDignidad;
import ec.gob.igm.cne.entidades.TUsuario;
import ec.gob.igm.cne.utils.Impresion;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import ec.gob.igm.cne.documentos.Item;

/**
 *
 * @author desarrollo
 */
public class FrmEtiquetaJuntaDocumentos extends javax.swing.JDialog {

    //    Add por Joffre Alava
    private TBaseDAO base;
    static TUsuario usuario;
    private TJuntaDAO junta = new TJuntaDAO();
    private String strUsuario;
    private Integer totalidListado;
    private List<Object[]> lstTipoDocumento;
    //private DefaultTableModel tableModel;

    /*
    Renderer que permite cambiar el color a las filas de la tabla
     */
    private FilaRenderer filaRenderer;

    private List<TDignidad> listaDignidades;
    private DatosJunta datosJuntaActual;
    private List<Object[]> lstListaDocumentosReImpresos;
    /*
    Indicadores de que se han revisado ya todos los documentos del dia del 
    sufragio/escrutinio    
     */
    private boolean esUltimoDocDiaSufragio = false;
    private boolean esUltimoDocDiaEscrutinio = false;
    private boolean esUltimoDocumento = false;
    /*
    Indicador de si la etiqueta a imprimir corresponde al día del sufragio "S"
    o día del escrutinio "E"
     */
    private String escrutinioSufragio = null;
    /*
    Indice de la fila actual
     */
    private int index;

    /*
    NUMERO_ACTUAL_PAGINA, NUMERO TOTAL DE PAGINA e INDICADOR DE CABECERA O PIE DE PAGINA,
    deberán ser los valores correctos contra los que se comparará el codigo de barras leido
     */
    public static final String NUMERO_ACTUAL_PAGINA = null; //la lectura debe hacerse en la pagina 1
    public static final String NUMERO_TOTAL_DE_PAGINA = null; //omitir validacion
    public static final String INDICADOR_CABECERA_PIE_DE_PAGINA = "0"; //en las Actas la lectura hacerse en la cabecera

    /**
     * Crea una nueva instancia de este formulario con el @tipo seleccionado por
     * defecto
     *
     * @param parent
     * @param modal
     * @param tipo indica el Proceso/tipo que está trabajando, que pude ser:
     * Voto General, Voto en casa, Voto PPL mayor a 50 electores, etc.
     * @param usuario
     */
    //    Add por Joffre Alava TUsuario
    public void setUsuario(String usuario) {
        this.strUsuario = usuario;
    }

    public String getUsuario() {
        return strUsuario;
    }

//    public FrmEtiquetaJuntaDocumentos(TUsuario usuario){
//        this.usuario = usuario;
//    }
    public FrmEtiquetaJuntaDocumentos(java.awt.Frame parent, boolean modal, String tipo, TUsuario usuario) {
        super(parent, modal);
        initComponents();

        // Add por JA ---- NO  B O R R A R
        cargarTipoDocumento(cmbTipo);
        this.usuario = usuario;
        /*
        Se llena le cmbTipo con los datos de la tabla TTipo_Documento
         */
//        TTipoDocumentoDAO tddao = new TTipoDocumentoDAO();
//        List<TTipoDocumento> tiposList = tddao.listarTodos();
        TDignidadDAO ddao = new TDignidadDAO();
        /*
        se cargan la informacion de dignidades, cuyos IDs se usaran para validar los documentos,
        al cargarde al abrir la ventana, se evita consultar repetidas por cada documento validado
         */
        listaDignidades = ddao.listarTodas();
//
//        String[] arrayTipos = new String[tiposList.size()];
//        for (int i = 0; i < arrayTipos.length; i++) {
//            arrayTipos[i] = tiposList.get(i).toString();
//        }
//        cmbTipo.setModel(new DefaultComboBoxModel(arrayTipos));

        /*
        Cuando se detecte un ItemEvent.SELECTED, se debe llenar la tabla del
        formulario
         */
        cmbTipo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    //Object item = e.getItem();
                    Item tipoDocuementSelect = (Item) cmbTipo.getSelectedItem();

                    /*
                    Actualizar tabla en base al Tipo seleccionado
                     */
                    if (tipoDocuementSelect != null) {
                        llenarTabla(tipoDocuementSelect.getId());
                        if (!txtCodigoBarras.isEnabled()) {
                            txtCodigoBarras.setEnabled(true);
                        }
                        txtCodigoBarras.requestFocusInWindow();
                        tbListaDocumentos.scrollRectToVisible(tbListaDocumentos.getCellRect(0, 0, true));
                        if (btnGenerarEtiqueta.isEnabled()) {
                            btnGenerarEtiqueta.setEnabled(false);
                        }
                        /*
                        Se elimina el coloreado de las filas de la tabla
                         */
                        if (filaRenderer != null && !filaRenderer.estaListaVacia()) {
                            filaRenderer.vaciarLista();
                        }
                    }
                }
            }
        });
        /*
        Para poder dirparar el evento itemStateChanged, primero se
        deselecciona el combobox, para despues seleccionar un item
         */
        cmbTipo.setSelectedIndex(-1);
        /*
        Se selecciona el item TIPO recibido en el constructor
         */
        cmbTipo.setSelectedItem(tipo);
        /*
        Establecer el foco en el campo de texto del código de barras
         */
        txtCodigoBarras.requestFocusInWindow();

        /*
        Se instancia el renderer y se lo asigna a la tabla (para poder cambiar el color de las filas)
         */
        filaRenderer = new FilaRenderer();
        tbListaDocumentos.setDefaultRenderer(Object.class, filaRenderer);
    }

    ////******* hasta aca
    public void llenarTabla(Integer idKit) {
        //String tipoStr = tipo.substring(2);
        TListadoDocumentosDAO tldDAO = new TListadoDocumentosDAO();
        List<Documento> lista = tldDAO.listarDocumentosXtipo(idKit);

        String[] cabecera = {"#", "Documento", "Tamaño", "Id", "Total"};
        DefaultTableModel tableModel = new DefaultTableModel(null, cabecera) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] fila = new Object[5];
        for (Documento doc : lista) {
            fila[0] = doc.getOrden();
            fila[1] = doc.getNombre();
            fila[2] = doc.getLongCodigo();
            fila[3] = doc.getId();
            fila[4] = "";

            tableModel.addRow(fila);
        }

        tbListaDocumentos.setModel(tableModel);
        setJTableColumnsWidth(tbListaDocumentos, tbListaDocumentos.getSize().width, 6, 81, 7, 6, 10);
        /*
        Se inicia con la primera fila de la tabla seleccionada
         */
        tbListaDocumentos.setRowSelectionInterval(0, 0);

    }

    /**
     * Establece el ancho de las columnas de la tabla en base a los porcentajes
     * dados
     *
     * @param table la tabla cuyos anchos de columna se van a establecer
     * @param tablePreferredWidth ancho total de la tabla en pixels
     * @param percentages porcentajes para cada columna (deben sumar 100%)
     */
    public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth,
            double... percentages) {
        double total = 0;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            total += percentages[i];
        }

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth((int) (tablePreferredWidth * (percentages[i] / total)));
        }
    }

    public void mostrarDatosJunta() {
        if (datosJuntaActual != null) {
            txtProvincia.setText(datosJuntaActual.getProvincia());
            txtCanton.setText(datosJuntaActual.getCanton());
            txtCircunscripcion.setText(datosJuntaActual.getCircunscripcion());
            //txtParroquia.setText(datosJuntaActual.getParroquia() + (datosJuntaActual.getTipoParroquia().equals("U") ? " - URBANA" : " - RURAL"));
            txtParroquia.setText(datosJuntaActual.getParroquia());
             String zona="";
             zona= datosJuntaActual.getZona() == null?"":datosJuntaActual.getZona();
            txtZona.setText(datosJuntaActual.getCodZona() + " - " + zona);
            txtJunta.setText(String.format("%04d", Integer.parseInt(datosJuntaActual.getJunta())) + " - " + datosJuntaActual.getSexo());
        } else {
            //Mostrar mensaje de error: No existe la Junta
        }
    }

    /**
     * TableCellRenderer personalizado que permite pintar las filas de la tabla
     * que se añadan a lista usando el método agregarFila()
     */
    public class FilaRenderer extends DefaultTableCellRenderer {

        private Map<Integer, Boolean> lista;
        private List<Integer> listaReimpresos;

        public void agregarReimpreso(int id) {
            listaReimpresos.add(id);
        }

        public void agregarFila(int fila, boolean resultado) {
            lista.put(fila, resultado);
        }

        public void vaciarLista() {
            lista.clear();
        }

        public FilaRenderer() {
            lista = new HashMap();
            listaReimpresos = new ArrayList<>();
        }

        public boolean estaListaVacia() {
            return lista.isEmpty();
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
//            setBackground(null);
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);

            Object columnValue = table.getValueAt(row, table.getColumnModel().getColumnIndex("Id"));
//            System.out.println((int)columnValue);
            if (selected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());

                if (listaReimpresos.contains((int) columnValue)) {
                    setBackground(Color.CYAN);
                    setForeground(Color.BLACK);
                    txtMensaje.setText("Existen documentos Reimpresos de esta Junta.");
                    //

                }

                if (lista.containsKey(row)) {
//                    setBackground(null);
                    if (lista.get(row)) {
                        setBackground(Color.GREEN);
                        setForeground(Color.BLACK);
                    } else {
                        setBackground(Color.RED);
                        setForeground(Color.BLACK);
                    }
                }

//                if (listaReimpresos.contains((int) columnValue)) {
//                    setBackground(Color.YELLOW);
//                    setForeground(Color.BLACK);
//                }
            }
            return this;
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
        cmbTipo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtCodigoBarras = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtCanton = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtProvincia = new javax.swing.JTextField();
        txtCircunscripcion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtParroquia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtZona = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtJunta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbListaDocumentos = new javax.swing.JTable();
        btnGenerarEtiqueta = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMensaje = new javax.swing.JTextArea();
        label1 = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(750, 750));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Tipo de Proceso:");

        cmbTipo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Código del Documento:");

        txtCodigoBarras.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCodigoBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoBarrasActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Junta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Provincia:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 23));

        txtCanton.setEditable(false);
        txtCanton.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(txtCanton, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 184, -1));

        jLabel4.setText("Cantón");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        txtProvincia.setEditable(false);
        txtProvincia.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(txtProvincia, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 184, -1));

        txtCircunscripcion.setEditable(false);
        txtCircunscripcion.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(txtCircunscripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 184, -1));

        jLabel5.setText("Circunscripción:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 80, -1));

        jLabel6.setText("Parroquia:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        txtParroquia.setEditable(false);
        txtParroquia.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(txtParroquia, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 216, -1));

        jLabel7.setText("Zona:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));

        txtZona.setEditable(false);
        txtZona.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(txtZona, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 216, -1));

        jLabel8.setText("Junta:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, -1, -1));

        txtJunta.setEditable(false);
        txtJunta.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(txtJunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 216, -1));

        tbListaDocumentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbListaDocumentos.setEnabled(false);
        jScrollPane1.setViewportView(tbListaDocumentos);

        btnGenerarEtiqueta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGenerarEtiqueta.setText("Generar Etiqueta");
        btnGenerarEtiqueta.setEnabled(false);
        btnGenerarEtiqueta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarEtiquetaActionPerformed(evt);
            }
        });

        txtMensaje.setBackground(new java.awt.Color(240, 240, 240));
        txtMensaje.setColumns(20);
        txtMensaje.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        txtMensaje.setForeground(new java.awt.Color(255, 0, 0));
        txtMensaje.setLineWrap(true);
        txtMensaje.setRows(5);
        txtMensaje.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtMensaje);

        label1.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        label1.setText("CopyRight IGM 2021 V1.4.2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCodigoBarras)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGenerarEtiqueta)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnGenerarEtiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        label1.getAccessibleContext().setAccessibleName("CopyRight IGM 2021 V1.4.22");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoBarrasActionPerformed
        // TODO add your handling code here:
        //add Joffre A
        Integer idListado = 0;
        this.base = new TBaseDAO();

        if (txtMensaje.getText().length() > 0) {
            //Si ya mostraba un mensaje de error, se lo deja de mostrar
            txtMensaje.setText("");
        }
        String codigoIngresado = txtCodigoBarras.getText().toUpperCase().trim();
        index = tbListaDocumentos.getSelectedRow();
        String nombreDoc = (String) tbListaDocumentos.getModel().getValueAt(index, 1);
        int longitudReal = (int) tbListaDocumentos.getModel().getValueAt(index, 2);
        if (codigoIngresado.length() == longitudReal) {
            Item tipoDocumentSelect = (Item) cmbTipo.getSelectedItem();
            // Update Joffre A
            int idKit = tipoDocumentSelect.getId();// Integer.parseInt(cmbTipo.getSelectedItem().toString().substring(0, 1));

            //add Mercedes J 
            this.lstTipoDocumento = base.cargarTipoDocumento();
            int id_estructura = 0;
            for (Object[] documento : lstTipoDocumento) {
                if (idKit == Integer.valueOf(documento[0].toString())) {
                    id_estructura = Integer.valueOf(documento[2].toString());
                }
            }

            if (index == 0) {
                /*
                Procesamiento del primer documento(listado de materiales)
                 */
                if (id_estructura == 1) {
                    TListadoDocumentosDAO tldDAO = new TListadoDocumentosDAO();
                    CodigoBarras objCodigoBarras = tldDAO.listarComponentesCodigo(nombreDoc, idKit);
                    ResultadoVerificacion resultadoVerificacion = Verificador.validacionParcialBarras(nombreDoc, longitudReal, codigoIngresado, objCodigoBarras);
                    boolean resultado = resultadoVerificacion.calcularResultadoGlobal();
                    System.out.println(nombreDoc.toUpperCase() + ":");
                    System.out.println(resultadoVerificacion);
                    if (resultado) {
                        //Se debe mostrar la información de la junta
                        objCodigoBarras = Verificador.obtenerJunta(nombreDoc, codigoIngresado, objCodigoBarras);
                        System.out.println(objCodigoBarras.getComponentes().toString());
                        datosJuntaActual = objCodigoBarras.obtenerDatosDeJunta();
                        mostrarDatosJunta();

                        // Add por Joffre Alva
                        if (datosJuntaActual.getEstado().equalsIgnoreCase("GENERADA")) {
                            //Mostrar mensaje de error
                            txtMensaje.setText("El documento esta en estado:" + datosJuntaActual.getEstado() + "\nDebe tener un estado: CREADA");
                            this.getSonido();
                        } else {
                            tbListaDocumentos.setRowSelectionInterval(index + 1, index + 1);
                            //                        filaRenderer.agregarReimpreso(9);

                            // Add JA
                            this.lstListaDocumentosReImpresos = base.getListadoDocumentosReImpresos(datosJuntaActual.getJuntaPK().getCodProvincia(), datosJuntaActual.getJuntaPK().getCodCanton(), datosJuntaActual.getJuntaPK().getCodParroquia(), datosJuntaActual.getJuntaPK().getCodZona(), datosJuntaActual.getJuntaPK().getSexo(), datosJuntaActual.getJuntaPK().getJunta(), datosJuntaActual.getJuntaPK().getCodCircunscripcion());
                            for (Object[] lstDocReImpresos : lstListaDocumentosReImpresos) {
                                idListado = Integer.parseInt(lstDocReImpresos[1].toString());  //new Item(((BigDecimal)lstDocReImpresos[0]).intValue(),lstDocReImpresos[1].toString());
                                totalidListado = Integer.parseInt(lstDocReImpresos[0].toString());  //new Item(((BigDecimal)lstDocReImpresos[0]).intValue(),lstDocReImpresos[1].toString());
                                filaRenderer.agregarReimpreso(idListado);
                            }
                            // hasta aca
                            /*
                            Se agrega la fila al renderer para cambiarla de color
                             */
                            //                        filaRenderer.agregarFila(index, true);
                            tbListaDocumentos.scrollRectToVisible(tbListaDocumentos.getCellRect(tbListaDocumentos.getRowCount() - 1, 0, true));
                            tbListaDocumentos.scrollRectToVisible(tbListaDocumentos.getCellRect(index, 0, true));
                            // actualiza los totales
                            updateDocTotales(tbListaDocumentos, lstListaDocumentosReImpresos);
                        }
                    } else {
                        tbListaDocumentos.setRowSelectionInterval(index + 1, index + 1);
                        tbListaDocumentos.setRowSelectionInterval(index, index);
                        filaRenderer.agregarFila(index, false);
                        //Mostrar mensaje de error
                        txtMensaje.setText(resultadoVerificacion.getErrores());
                        this.getSonido();
                    }
                } else if (id_estructura == 3) {
                    TListadoDocumentosDAO tldDAO = new TListadoDocumentosDAO();
                    //Descomente para validar
                    CodigoBarras objCodigoBarras = tldDAO.listarComponentesCodigo(nombreDoc, idKit);
                    ResultadoVerificacion resultadoVerificacion = Verificador.validacionParcialBarras(nombreDoc, longitudReal, codigoIngresado, objCodigoBarras);
                    boolean resultado = resultadoVerificacion.calcularResultadoGlobal();
                    System.out.println(nombreDoc.toUpperCase() + ":");
                    //System.out.println(resultadoVerificacion);

                    if (resultado) {
                        //Se debe mostrar la información de la junta
                        objCodigoBarras = Verificador.obtenerJunta(nombreDoc, codigoIngresado, objCodigoBarras);
                        System.out.println(objCodigoBarras.getComponentes().toString());
                        Integer codigoActa = Integer.valueOf(codigoIngresado.substring(0, 5));
                        //   objCodigoBarras = Verificador.obtenerJuntaxActa(codigoActa);
                        //                System.out.println(junta.obtenerDatosDeJuntaXActa(codigoActa).getEstado());
                        datosJuntaActual = junta.obtenerDatosDeJuntaXActa(codigoActa);
                        System.out.print(datosJuntaActual.getBarras());
                        mostrarDatosJunta();

                        // Add por Joffre Alva
                        if (datosJuntaActual.getEstado().equalsIgnoreCase("GENERADA")) {
                            //Mostrar mensaje de error
                            txtMensaje.setText("El documento esta en estado:" + datosJuntaActual.getEstado() + "\nDebe tener un estado: CREADA");
                            this.getSonido();
                        } else {
                            tbListaDocumentos.setRowSelectionInterval(index + 1, index + 1);
                            //                        filaRenderer.agregarReimpreso(9);

                            // Add JA
                            this.lstListaDocumentosReImpresos = base.getListadoDocumentosReImpresos(datosJuntaActual.getJuntaPK().getCodProvincia(), datosJuntaActual.getJuntaPK().getCodCanton(), datosJuntaActual.getJuntaPK().getCodParroquia(), datosJuntaActual.getJuntaPK().getCodZona(), datosJuntaActual.getJuntaPK().getSexo(), datosJuntaActual.getJuntaPK().getJunta(), datosJuntaActual.getJuntaPK().getCodCircunscripcion());
                            for (Object[] lstDocReImpresos : lstListaDocumentosReImpresos) {
                                idListado = Integer.parseInt(lstDocReImpresos[1].toString());  //new Item(((BigDecimal)lstDocReImpresos[0]).intValue(),lstDocReImpresos[1].toString());
                                totalidListado = Integer.parseInt(lstDocReImpresos[0].toString());  //new Item(((BigDecimal)lstDocReImpresos[0]).intValue(),lstDocReImpresos[1].toString());
                                filaRenderer.agregarReimpreso(idListado);
                            }
                            // hasta aca
                            /*
                            Se agrega la fila al renderer para cambiarla de color
                             */
                            filaRenderer.agregarFila(index, true);
                            tbListaDocumentos.scrollRectToVisible(tbListaDocumentos.getCellRect(tbListaDocumentos.getRowCount() - 1, 0, true));
                            tbListaDocumentos.scrollRectToVisible(tbListaDocumentos.getCellRect(index, 0, true));
                            // actualiza los totales
                            updateDocTotales(tbListaDocumentos, lstListaDocumentosReImpresos);
                        }
                    } else {

                        tbListaDocumentos.setRowSelectionInterval(index + 1, index + 1);
                        tbListaDocumentos.setRowSelectionInterval(index, index);
                        filaRenderer.agregarFila(index, false);
                        //Mostrar mensaje de error
                        // txtMensaje.setText(resultadoVerificacion.getErrores());
                        this.getSonido();
                    }

                }

            } else {
                /*
                Procesamiento del 2do documento en adelante
                 */
 /*
                Validación del codigo de barras.
                 */

                ResultadoVerificacion resultadoVerificacion = Verificador.validarCodigoBarras(nombreDoc,
                        codigoIngresado,
                        datosJuntaActual,
                        listaDignidades,
                        NUMERO_ACTUAL_PAGINA,
                        NUMERO_TOTAL_DE_PAGINA,
                        INDICADOR_CABECERA_PIE_DE_PAGINA,
                        datosJuntaActual.getTipoParroquia(), idKit);
                boolean resultado = resultadoVerificacion.calcularResultadoGlobal();
                if (resultado) {
                    /*
                    Se agrega la fila al renderer para cambiarla de color
                     */
                    filaRenderer.agregarFila(index, true);

                    esUltimoDocumento = (index == (tbListaDocumentos.getModel().getRowCount() - 1));
                    if (cmbTipo.getSelectedItem().toString().toLowerCase().contains("casa")
                            || cmbTipo.getSelectedItem().toString().toLowerCase().contains("ppl")) {
                        /*
                        Si el siguiente documento es el sobre del día del sufragio, o
                        o el sobre del día del escrutinio, entonces
                        tambien se debe  generar la respectiva etiqueta (solo aplicable en voto en casa y ppls)
                         */
                        if (!esUltimoDocumento) {
                            String nombreSiguienteDoc = (String) tbListaDocumentos.getModel().getValueAt(index + 1, 1);
                            esUltimoDocDiaSufragio = nombreSiguienteDoc.toLowerCase().contains("día del escrutinio"); // estaba sobre día del sufragio
                            esUltimoDocDiaEscrutinio = nombreSiguienteDoc.toLowerCase().contains("día del escrutinioOOO"); // estaba sobre día del escrutinio
                        }
                    }

                    //add por Joffre Alava - imprime la etiqueta del SUFRAGIO                    
                    if (esUltimoDocDiaSufragio) { //esUltimoDocDiaSufragio
                        try {
                            Impresion.etiquetaDocumentos(datosJuntaActual, cmbTipo.getSelectedItem().toString(), "S");

                        } catch (PrintException ex) {
                            Logger.getLogger(FrmEtiquetaJuntaDocumentos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    // esUltimoDocDiaSufragio ||
                    if (esUltimoDocumento) { // || esUltimoDocDiaEscrutinio) {
                        /*
                        Se debe generar una etiqueta
                         */
                        //add por Joffre Alava - imprime la etiqueta del ESCRUTINIO                        
                        if (idKit >= 2 && idKit <= 4) { // si es 2. Voto en casa, 3. PPL > 100 4. PPL < 100
                            try {
                                Impresion.etiquetaDocumentos(datosJuntaActual, cmbTipo.getSelectedItem().toString(), "E");
                            } catch (PrintException ex) {
                                Logger.getLogger(FrmEtiquetaJuntaDocumentos.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        tbListaDocumentos.clearSelection();
                        btnGenerarEtiqueta.setEnabled(true);
                        btnGenerarEtiqueta.requestFocusInWindow();
                        txtCodigoBarras.setEnabled(false);
                    } else {
                        tbListaDocumentos.setRowSelectionInterval(index + 1, index + 1);
                        /*
                        Mover el scroll una fila hacia abajo
                         */
                        tbListaDocumentos.scrollRectToVisible(tbListaDocumentos.getCellRect(index + 1, 0, true));

                    }
                } else {
                    tbListaDocumentos.setRowSelectionInterval(index + 1, index + 1);
                    tbListaDocumentos.setRowSelectionInterval(index, index);
                    filaRenderer.agregarFila(index, false);
                    //Mostrar mensaje de error                    
                    txtMensaje.setText(resultadoVerificacion.getErrores());
                    this.getSonido();
                }
            }
        } else {
            tbListaDocumentos.setRowSelectionInterval(index + 1, index + 1);
            tbListaDocumentos.setRowSelectionInterval(index, index);
            filaRenderer.agregarFila(index, false);
            txtMensaje.setText("Longitud erronea de código:" + codigoIngresado.length() + "\nDebe ser:" + longitudReal);
            this.getSonido();
        }
        txtCodigoBarras.setText("");

    }//GEN-LAST:event_txtCodigoBarrasActionPerformed


    private void btnGenerarEtiquetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarEtiquetaActionPerformed
        try {
            // add Joffre Alava
            this.base = new TBaseDAO();

            //Impresión de la etiqueta
            if (esUltimoDocumento) {
                Impresion.etiquetaDocumentos(datosJuntaActual, cmbTipo.getSelectedItem().toString(), null);
            } else if (esUltimoDocDiaSufragio) {
                //Impresion.etiquetaDocumentos(datosJuntaActual, cmbTipo.getSelectedItem().toString(), "S");
            } else {
                //Impresion.etiquetaDocumentos(datosJuntaActual, cmbTipo.getSelectedItem().toString(), "E");
            }

            //TODO: SE DEBE ACTUALIZAR EL ESTADO DE LA ETIQUETA EN T_BASE
//            if(pantalla==1)
            // Add por Joffre Alava
            java.util.Date fecha = new Date();
            base.actualizarEtiqueta("GENERADA", fecha, this.usuario.getUsuario(), datosJuntaActual.getBarras(), "DOCUMENTO");
//            else                    
//              base.ingresarReimpresion(provinciaSeleccionada.getId(), cantonSeleccionado.getId(), parroquiaSeleccionada.getId(), idzona, juntaSeleccionada.getId(),generoSeleccionado.getId(),razonSeleccionado.getDescription(),fecha,usuario.getUsuario(),"PAPELETA",solicitanteSeleccionado.getDescription(),dignidadSeleccionada.getId(),etiquetas[1].toString());            

            btnGenerarEtiqueta.setEnabled(false);
            txtCodigoBarras.setEnabled(true);
            txtCodigoBarras.requestFocusInWindow();
            if (esUltimoDocDiaSufragio || esUltimoDocDiaEscrutinio) {
                tbListaDocumentos.setRowSelectionInterval(index + 1, index + 1);
            }
            if (esUltimoDocumento) {
                filaRenderer.vaciarLista(); //Elimina el coloreado de las filas de la tabla
                Item tipoDocuementSelect = (Item) cmbTipo.getSelectedItem();
                llenarTabla(tipoDocuementSelect.getId());
                tbListaDocumentos.setRowSelectionInterval(0, 0);
                //Se debe mover el scroll hacia el tope
                tbListaDocumentos.scrollRectToVisible(tbListaDocumentos.getCellRect(0, 0, true));
            }

        } catch (PrintException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error durante la impresión: ", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnGenerarEtiquetaActionPerformed

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        //
        
        
        
        try {
            
//            usuario.getIdUsuario()
            
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmEtiquetaJuntaDocumentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEtiquetaJuntaDocumentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEtiquetaJuntaDocumentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEtiquetaJuntaDocumentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmEtiquetaJuntaDocumentos dialog = new FrmEtiquetaJuntaDocumentos(new javax.swing.JFrame(), true, "Voto General", usuario);
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

    // Funcion emite un sonido al error de lectura
    // Creado por: Joffre A
    public void getSonido() {
        try {
            // Open an audio input stream.
            URL url = this.getClass().getClassLoader().getResource("beep-02.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Add por JoffreA
    //for (Object[] lstDocReImpresos : lstListaDocumentosReImpresos) {
    public void updateDocTotales(JTable tbListaDocumentos, List<Object[]> lstDocumentosRI) {
//      setValueAt
        Integer idListado;
        Integer totalidListado;

        for (int i = 0; i < tbListaDocumentos.getRowCount(); i++) {
            for (Object[] lstDocumento : lstDocumentosRI) {

                idListado = Integer.parseInt(lstDocumento[1].toString());  //new Item(((BigDecimal)lstDocReImpresos[0]).intValue(),lstDocReImpresos[1].toString());

                if (idListado == Integer.parseInt(tbListaDocumentos.getValueAt(i, 3).toString())) {
                    //                System.out.println("N°·: " + i + " ID: "+tbListaDocumentos.getValueAt(i, 0) + " DOC: "+tbListaDocumentos.getValueAt(i, 1) + " ESTR: "+tbListaDocumentos.getValueAt(i, 2)+ " ID_LISTADO: " + tbListaDocumentos.getValueAt(i, 3));
                    totalidListado = Integer.parseInt(lstDocumento[0].toString());
                    tbListaDocumentos.setValueAt(totalidListado, i, 4);
                    //tbListaDocumentos.getR
                }
            }
        }

    }

    public void cargarTipoDocumento(JComboBox cmbTipo) {
        this.base = new TBaseDAO();

        cmbTipo.removeAllItems();
        this.lstTipoDocumento = base.cargarTipoDocumento();
        for (Object[] documentos : lstTipoDocumento) {
            Item documento = new Item(Integer.parseInt(documentos[0].toString()), documentos[1].toString());
            cmbTipo.addItem(documento);
        }

    }

    /**
     * Actualiza el estado de las etiquetas impresas
     *
     * @param estado
     * @param fechaEmision
     * @param usuario
     * @param barras
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarEtiqueta;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Label label1;
    private javax.swing.JTable tbListaDocumentos;
    private javax.swing.JTextField txtCanton;
    private javax.swing.JTextField txtCircunscripcion;
    private javax.swing.JTextField txtCodigoBarras;
    private javax.swing.JTextField txtJunta;
    private javax.swing.JTextArea txtMensaje;
    private javax.swing.JTextField txtParroquia;
    private javax.swing.JTextField txtProvincia;
    private javax.swing.JTextField txtZona;
    // End of variables declaration//GEN-END:variables
}
