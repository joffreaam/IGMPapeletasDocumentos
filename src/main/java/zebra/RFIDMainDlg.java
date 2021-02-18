/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RFIDSample.java
 *
 * Created on Mar 8, 2011, 5:12:20 PM
 */
package zebra;

import com.mot.rfid.api3.GPI_PORT_STATE;
import com.mot.rfid.api3.InvalidUsageException;
import com.mot.rfid.api3.OperationFailureException;
import com.mot.rfid.api3.RADIO_POWER_STATE;
import com.sun.glass.events.KeyEvent;
import ec.gob.igm.cne.documentos.ILoteDocumentos;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author CFRN78
 */
public class RFIDMainDlg extends javax.swing.JFrame{
    static RFIDBase rfidBase = null;
    
    private ILoteDocumentos interfaz;

   
   public RFIDMainDlg(ILoteDocumentos interfaz){
       this.setVisible(true);
       
        initComponents();
        this.interfaz = interfaz;
       interfaz.cargarProvincia(jcmbProvincia);
        //this.interfaz = interfaz;
        rfidBase = new RFIDBase(this);
        //TUsuario usuario
        //this.usuario = usuario;
        
//        rfidBase = new RFIDBase(this, usuario);

        Component[] myComponent = getComponents();

        // initialize menu item state
        // Generic Menu interface
        updateGenericMenuItems(false);

        // RM Menu interface
        updateRMMenuItems(false);

        // disable the GPI port images
        for (int index = 1; index <= 10; index++) {
            setGPIOnScreen(index, false, false);
        }

        // Create Popup menu (context menu on right click)
//        createPopupMenu();
       
   }
    
    

    /** Creates new form RFIDSample */
    public RFIDMainDlg() {
        initComponents();
        
        rfidBase = new RFIDBase(this);

        Component[] myComponent = getComponents();
        
        // initialize menu item state
        // Generic Menu interface
        updateGenericMenuItems(false);

        // RM Menu interface
        updateRMMenuItems(false);

        // disable the GPI port images
        for (int index = 1; index <= 10; index++) {
            setGPIOnScreen(index, false, false);
        }

        // Create Popup menu (context menu on right click)
//        createPopupMenu();

    }
   

    public void initGPIportImages()
    {
        if (rfidBase.isConnected)
        {
            int gpiPorts = rfidBase.getMyReader().ReaderCapabilities.getNumGPIPorts();
            for (int index = 1; index <= gpiPorts; index++)
            {
                try {
                    if (rfidBase.getMyReader().Config.GPI.getPortState(index) == GPI_PORT_STATE.GPI_PORT_STATE_HIGH) {
                        setGPIOnScreen(index, true, true);
                    } else {
                        setGPIOnScreen(index, true, false);
                    }
                } catch (InvalidUsageException ex) {
                    ex.printStackTrace();
                } catch (OperationFailureException ex) {
                    ex.printStackTrace();
                }
            }
        }
        else
        {
            // disable the GPI port images
            for (int index = 1; index <= 10; index++) {
                setGPIOnScreen(index, false, false);
            }
        }
    }

    public void setGPIOnScreen(int portNo, boolean isVisible, boolean isPortStateHigh)
    {
       /* switch(portNo)
        {
            case 1:
                jLabelGPI1.setVisible(isVisible);
                if (isPortStateHigh)
                    jLabelGPI1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connected.GIF"))); // NOI18N
                else
                    jLabelGPI1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/disconnected.GIF"))); // NOI18N

                break;
            case 2:
                jLabelGPI2.setVisible(isVisible);
                if (isPortStateHigh)
                    jLabelGPI2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connected.GIF"))); // NOI18N
                else
                    jLabelGPI2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/disconnected.GIF"))); // NOI18N
                break;
            case 3:
                jLabelGPI3.setVisible(isVisible);
                if (isPortStateHigh)
                    jLabelGPI3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connected.GIF"))); // NOI18N
                else
                    jLabelGPI3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/disconnected.GIF"))); // NOI18N
                break;
            case 4:
                jLabelGPI4.setVisible(isVisible);
                if (isPortStateHigh)
                    jLabelGPI4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connected.GIF"))); // NOI18N
                else
                    jLabelGPI4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/disconnected.GIF"))); // NOI18N
                break;
            case 5:
                if (isPortStateHigh)
                    jLabelGPI5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connected.GIF"))); // NOI18N
                else
                    jLabelGPI5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/disconnected.GIF"))); // NOI18N
                jLabelGPI5.setVisible(isVisible);
                break;
            case 6:
                if (isPortStateHigh)
                    jLabelGPI6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connected.GIF"))); // NOI18N
                else
                    jLabelGPI6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/disconnected.GIF"))); // NOI18N
                jLabelGPI6.setVisible(isVisible);
                break;
            case 7:
                if (isPortStateHigh)
                    jLabelGPI7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connected.GIF"))); // NOI18N
                else
                    jLabelGPI7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/disconnected.GIF"))); // NOI18N

                jLabelGPI7.setVisible(isVisible);
                break;
            case 8:
                if (isPortStateHigh)
                    jLabelGPI8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connected.GIF"))); // NOI18N
                else
                    jLabelGPI8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/disconnected.GIF"))); // NOI18N

                jLabelGPI8.setVisible(isVisible);
                break;
            case 9:
                if (isPortStateHigh)
                    jLabelGPI9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connected.GIF"))); // NOI18N
                else
                    jLabelGPI9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/disconnected.GIF"))); // NOI18N

                jLabelGPI9.setVisible(isVisible);
                break;
            case 10:
                if (isPortStateHigh)
                    jLabelGPI10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connected.GIF"))); // NOI18N
                else
                    jLabelGPI10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/disconnected.GIF"))); // NOI18N

                jLabelGPI10.setVisible(isVisible);
                break;
        }*/

    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
   // @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenuAccess = new javax.swing.JPopupMenu();
        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        menuBar2 = new java.awt.MenuBar();
        menu3 = new java.awt.Menu();
        menu4 = new java.awt.Menu();
        menuBar3 = new java.awt.MenuBar();
        menu5 = new java.awt.Menu();
        menu6 = new java.awt.Menu();
        menuBar4 = new java.awt.MenuBar();
        menu7 = new java.awt.Menu();
        menu8 = new java.awt.Menu();
        jDialog1 = new javax.swing.JDialog();
        startReadButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jInventoryTable = new javax.swing.JTable();
        jLabelTotalTags = new javax.swing.JLabel();
        jLabelStatus = new javax.swing.JLabel();
        jLabelConnectImage = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jcmbProvincia = new javax.swing.JComboBox<>();
        jtxtLote = new javax.swing.JTextField();
        jtxtTag = new javax.swing.JTextField();
        jtxtCuenta = new javax.swing.JTextField();
        jlblTag = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jlblTotalLote = new javax.swing.JLabel();
        jlblTotal1 = new javax.swing.JLabel();
        jlblRestantesZona = new javax.swing.JLabel();
        jbtnCerrarLote = new javax.swing.JButton();
        jlblNombre = new javax.swing.JLabel();
        jlblNombre1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemConnection = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItemCapabilities = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItemLogin = new javax.swing.JMenuItem();
        jMenuItemAntennaMode = new javax.swing.JMenuItem();
        jMenuItemReadPoint = new javax.swing.JMenuItem();
        jMenuItemSoftwareUpdate = new javax.swing.JMenuItem();
        jMenuItemReboot = new javax.swing.JMenuItem();

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        menu3.setLabel("File");
        menuBar2.add(menu3);

        menu4.setLabel("Edit");
        menuBar2.add(menu4);

        menu5.setLabel("File");
        menuBar3.add(menu5);

        menu6.setLabel("Edit");
        menuBar3.add(menu6);

        menu7.setLabel("File");
        menuBar4.add(menu7);

        menu8.setLabel("Edit");
        menuBar4.add(menu8);

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("J_RFIDHostSample");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusTraversalPolicyProvider(true);
        setPreferredSize(new java.awt.Dimension(1097, 525));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        startReadButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        startReadButton.setText("Start Read");
        startReadButton.setEnabled(false);
        startReadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startReadButtonActionPerformed(evt);
            }
        });

        jInventoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "TAG", "ID_JUNTA", "PROVINCIA", "CANTON", "PARROQUIA", "ID_ZONA", "SEXO", "DIGNIDAD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jInventoryTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jInventoryTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jInventoryTableFocusLost(evt);
            }
        });
        jInventoryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jInventoryTableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jInventoryTableMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jInventoryTableMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jInventoryTable);
        if (jInventoryTable.getColumnModel().getColumnCount() > 0) {
            jInventoryTable.getColumnModel().getColumn(0).setMinWidth(90);
            jInventoryTable.getColumnModel().getColumn(0).setPreferredWidth(180);
        }

        jLabelStatus.setText("Ready");
        jLabelStatus.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelConnectImage.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Provincia:");

        jLabel3.setText("Lote:");

        jLabel4.setText("Documento:");

        jcmbProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbProvinciaActionPerformed(evt);
            }
        });

        jtxtLote.setEditable(false);

        jtxtTag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtTagActionPerformed(evt);
            }
        });

        jtxtCuenta.setEditable(false);
        jtxtCuenta.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                jtxtCuentaVetoableChange(evt);
            }
        });

        jlblTag.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblTag.setText("...");

        jScrollPane2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jScrollPane2KeyTyped(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_JUNTA", "PROVINCIA", "CANTON", "PARROQUIA", "ID_ZONA", "SEXO", "TAG", "DIGNIDAD", "CIRCUNSCRIPCION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setPreferredSize(new java.awt.Dimension(1097, 525));
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jlblTotalLote.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblTotalLote.setText("Total en Lote:");
        jlblTotalLote.setToolTipText("");

        jlblTotal1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblTotal1.setText("...");
        jlblTotal1.setToolTipText("");

        jlblRestantesZona.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblRestantesZona.setText("RESTANTES DE ESTA ZONA:");
        jlblRestantesZona.setToolTipText("");

        jbtnCerrarLote.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnCerrarLote.setText("Cerrar Lote");
        jbtnCerrarLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCerrarLoteActionPerformed(evt);
            }
        });

        jlblNombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jlblNombre1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblNombre1.setText("Bienvenido");

        jMenu1.setMnemonic('r');
        jMenu1.setText("Lector");

        jMenuItemConnection.setMnemonic('n');
        jMenuItemConnection.setText("Conexión");
        jMenuItemConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConnectionActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemConnection);

        jMenuItem1.setText("Antena");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItemCapabilities.setMnemonic('p');
        jMenuItemCapabilities.setText("Capabilities");
        jMenuItemCapabilities.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCapabilitiesActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemCapabilities);
        jMenu1.add(jSeparator1);

        jMenuItem3.setMnemonic('x');
        jMenuItem3.setText("Salir");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu5.setMnemonic('M');

        jMenuItemLogin.setMnemonic('L');
        jMenuItemLogin.setText("Login/Logout...");
        jMenuItemLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLoginActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItemLogin);

        jMenuItemAntennaMode.setMnemonic('A');
        jMenuItemAntennaMode.setText("Antenna Mode...");
        jMenuItemAntennaMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAntennaModeActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItemAntennaMode);

        jMenuItemReadPoint.setText("Read Point...");
        jMenuItemReadPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemReadPointActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItemReadPoint);

        jMenuItemSoftwareUpdate.setMnemonic('U');
        jMenuItemSoftwareUpdate.setText("Software/Firmware Update...");
        jMenuItemSoftwareUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSoftwareUpdateActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItemSoftwareUpdate);

        jMenuItemReboot.setMnemonic('b');
        jMenuItemReboot.setText("Reboot");
        jMenuItemReboot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRebootActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItemReboot);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelConnectImage, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane2))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(197, 197, 197)
                                        .addComponent(jLabelTotalTags)
                                        .addGap(340, 340, 340)
                                        .addComponent(startReadButton)
                                        .addGap(272, 272, 272)
                                        .addComponent(jlblNombre1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(373, 373, 373))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtxtLote, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtTag, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jlblTag, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlblTotalLote, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jlblTotal1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jlblRestantesZona))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxtCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(488, 488, 488)
                                .addComponent(jbtnCerrarLote)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTotalTags, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jlblNombre)
                                .addComponent(jlblNombre1)))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jcmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jtxtTag, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(startReadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addComponent(jlblTag, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(295, 295, 295)
                        .addComponent(jLabelConnectImage, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblTotalLote, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlblRestantesZona)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jtxtCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jbtnCerrarLote, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(180, 180, 180)
                        .addComponent(jLabelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    public JLabel getjLabelStatus() {
        return jLabelStatus;
    }

    private void startReadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startReadButtonActionPerformed
        //None, EPC, TID, RESERVED, USER
        if (startReadButton.getLabel().equals("Start Read")){
            startReadButton.setLabel("Stop Read");
            rfidBase.startRead();
            //jcmbProvincia.setEnabled(true);
            //jtxtTag.setEnabled(true);
        }
        else{
           startReadButton.setLabel("Start Read");
           rfidBase.stopRead();
           //jcmbProvincia.setEnabled(false);
           //jtxtTag.setEnabled(false);
        }
    }//GEN-LAST:event_startReadButtonActionPerformed

    public void StartReadButtonClicked ()
    {
        startReadButton.setLabel("Start Read");
    }
    
    public void updateGenericMenuItems(boolean isConnected)
    {
        //jMenuItemConnection.setEnabled(false);
        jMenuItemCapabilities.setEnabled(isConnected);
        /*jMenuItemTagStorageSettings.setEnabled(isConnected);
        jMenuItemAntenna.setEnabled(isConnected);
        jMenuItemRFModes.setEnabled(isConnected);
        jMenuItemGPIO.setEnabled(isConnected);
        jMenuItemSingulation.setEnabled(isConnected);
        jMenuItemPowerOffRadio.setEnabled(isConnected);
        jMenuItemResetFactoryDefaults.setEnabled(isConnected);
        jMenuItemPreFilter.setEnabled(isConnected);
        jMenuItemPostFilter.setEnabled(isConnected);
        jMenuItemAccessFilter.setEnabled(isConnected);
        jMenuItemReadAccess.setEnabled(isConnected);
        jMenuItemWriteAccess.setEnabled(isConnected);
        jMenuItemLockAccess.setEnabled(isConnected);
        jMenuItemKillAccess.setEnabled(isConnected);
        jMenuItemBlockWrite.setEnabled(isConnected);
        jMenuItemBlockErase.setEnabled(isConnected);
        jMenuItemTriggers.setEnabled(isConnected);
        jMenuItemAntennaInfo.setEnabled(isConnected);*/
        
        // update control
       startReadButton.setEnabled(isConnected);
       //startReadButton.setEnabled(true);
        if (isConnected == false)
            startReadButton.setLabel("Start Read");
        else // connection established
            postNotification("", "");
        //jCheckBoxAutonomousMode.setEnabled(isConnected);
//        jButtonClearReports.setEnabled(isConnected);
        //jComboBoxMemBank.setEnabled(isConnected);
    }

    public void updateRMMenuItems(boolean isConnected)
    {
        jMenuItemAntennaMode.setEnabled(isConnected);
        jMenuItemReadPoint.setEnabled(isConnected);
        jMenuItemSoftwareUpdate.setEnabled(isConnected);
        jMenuItemReboot.setEnabled(isConnected);

    }

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        rfidBase.disconnectReader();
    }//GEN-LAST:event_formWindowClosed

    private void jMenuItemConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConnectionActionPerformed
        ConnectionDlg connectionDlg = new ConnectionDlg(this, rootPaneCheckingEnabled);
        connectionDlg.setLocationRelativeTo(null);
        connectionDlg.show();

    }//GEN-LAST:event_jMenuItemConnectionActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        processEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jInventoryTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jInventoryTableMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jInventoryTableMouseClicked

    private void jMenuItemCapabilitiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCapabilitiesActionPerformed
        CapababilitiesDlg capabilityDlg = new CapababilitiesDlg(this, rootPaneCheckingEnabled);
        capabilityDlg.setLocationRelativeTo(null);
        capabilityDlg.show();

    }//GEN-LAST:event_jMenuItemCapabilitiesActionPerformed

    private void jInventoryTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jInventoryTableMousePressed

    }//GEN-LAST:event_jInventoryTableMousePressed

    private void jInventoryTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jInventoryTableMouseReleased
        if (evt.isPopupTrigger())
        {
            JTable source = (JTable)evt.getSource();
            int row = source.rowAtPoint(evt.getPoint());
            int column = source.columnAtPoint(evt.getPoint());

            if(source.isRowSelected(row))
            {
                source.changeSelection(row, column, rootPaneCheckingEnabled, rootPaneCheckingEnabled);
                jPopupMenuAccess.show(evt.getComponent(), evt.getX(), evt.getY());
            }


        }
    }//GEN-LAST:event_jInventoryTableMouseReleased
	
	private void jMenuLockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu7ActionPerformed
        LockAccessDlg lockAccessDlg = new LockAccessDlg(this, rootPaneCheckingEnabled);
        lockAccessDlg.setLocationRelativeTo(null);
        lockAccessDlg.show();
    }//GEN-LAST:event_jMenu7ActionPerformed

    private void jMenuItemRebootActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRebootActionPerformed
        try {
            rfidBase.rm.restart();
            rfidBase.logOut();
        } catch (InvalidUsageException ex) {
            rfidBase.postStatusNotification(RFIDBase.PARAM_ERROR, ex.getVendorMessage());
        } catch (OperationFailureException ex) {
            rfidBase.postStatusNotification(ex.getStatusDescription(), ex.getVendorMessage());
        }
    }//GEN-LAST:event_jMenuItemRebootActionPerformed

    private void jMenuItemLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLoginActionPerformed
           LoginDlg loginDlg = new LoginDlg(this, rootPaneCheckingEnabled);
           loginDlg.setLocationRelativeTo(null);
           loginDlg.show();
    }//GEN-LAST:event_jMenuItemLoginActionPerformed

    private void jMenuItemAntennaModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAntennaModeActionPerformed
           AntennaModeDlg antennaModeDlg = new AntennaModeDlg(this, rootPaneCheckingEnabled);
           antennaModeDlg.setLocationRelativeTo(null);
           antennaModeDlg.show();
           
    }//GEN-LAST:event_jMenuItemAntennaModeActionPerformed

    private void jMenuItemReadPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemReadPointActionPerformed
           ReadPointDlg readPointDlg = new ReadPointDlg(this, rootPaneCheckingEnabled);
           readPointDlg.setLocationRelativeTo(null);
           readPointDlg.show();
    }//GEN-LAST:event_jMenuItemReadPointActionPerformed

    private void jMenuItemSoftwareUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSoftwareUpdateActionPerformed
           SoftwareUpdateDlg softwareUpdateDlg = new SoftwareUpdateDlg(this, rootPaneCheckingEnabled);
           softwareUpdateDlg.setLocationRelativeTo(null);
           softwareUpdateDlg.show();
    }//GEN-LAST:event_jMenuItemSoftwareUpdateActionPerformed

    private void jcmbProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbProvinciaActionPerformed
        // TODO add your handling code here:
        jtxtTag.setText("");
        jtxtTag.setFocusable(true);
        interfaz.cargarDatosLote(jcmbProvincia, jtxtLote, jTable1, jlblTotal1);
        //if(jcmbProvincia.isEnabled())
        //if(rfidBase!=null){
        //rfidBase.cargarDatosLote(jcmbProvincia, jtxtLote, jTable1,jlblTotal1);
        //rfidBase.startRead();
        //}
        //eventReadNotify(evt);
    }//GEN-LAST:event_jcmbProvinciaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        AntennaConfigDlg antennaConfigDlg = new AntennaConfigDlg(this, rootPaneCheckingEnabled);
        antennaConfigDlg.setLocationRelativeTo(null);
        antennaConfigDlg.show();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jInventoryTableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jInventoryTableFocusLost
        // TODO add your handling code here:
        rfidBase.stopRead();
       // rfidBase.startRead();
       
    }//GEN-LAST:event_jInventoryTableFocusLost

    private void jtxtCuentaVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_jtxtCuentaVetoableChange
        // TODO add your handling code here:
         //rfidBase.startRead();
    }//GEN-LAST:event_jtxtCuentaVetoableChange

    private void jbtnCerrarLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCerrarLoteActionPerformed
       interfaz.cerrarLote(jtxtLote, jtxtTag, jlblTotal1,jTable1,jcmbProvincia,jlblTag);
       //rfidBase.cerrarLote(jtxtLote, jtxtTag, jlblTotal1, jTable1, jcmbProvincia, jlblTag);
    }//GEN-LAST:event_jbtnCerrarLoteActionPerformed

    private void jtxtTagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtTagActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtTagActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_DELETE){
            
            interfaz.eliminar(jTable1);
            limpiarLeidas();
            
        //JOptionPane.showMessageDialog(null,"ESTA SEGURO DE ELIMINAR","ERROR",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jTable1KeyReleased

    private void jScrollPane2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jScrollPane2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2KeyTyped

    public void postNotification(String eventName, String eventValue)
    {
       // labelEventName.setText(eventName);
        //labelEventValue.setText(eventValue);
    }

    public void updateUI()
    {
        if (rfidBase.isConnected == true)
        {
//            jLabelConnectImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connected.GIF")));
        }
        else
        {
            //jLabelConnectImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/disconnected.GIF")));
        }
        initGPIportImages();
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Toolkit tk = Toolkit.getDefaultToolkit();
                Dimension d = tk.getScreenSize();
                RFIDMainDlg rfidSample = new RFIDMainDlg();
                rfidSample.setLocation((int)(d.getWidth() - rfidSample.getWidth())/2, (int)(d.getHeight() - rfidSample.getHeight())/2);
                Image appIcon = tk.getImage("Motorola.GIF");
                rfidSample.setIconImage(appIcon);
                rfidSample.setVisible(true);


            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog jDialog1;
    private javax.swing.JTable jInventoryTable;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelConnectImage;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelTotalTags;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItemAntennaMode;
    private javax.swing.JMenuItem jMenuItemCapabilities;
    private javax.swing.JMenuItem jMenuItemConnection;
    private javax.swing.JMenuItem jMenuItemLogin;
    private javax.swing.JMenuItem jMenuItemReadPoint;
    private javax.swing.JMenuItem jMenuItemReboot;
    private javax.swing.JMenuItem jMenuItemSoftwareUpdate;
    private javax.swing.JPopupMenu jPopupMenuAccess;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbtnCerrarLote;
    private javax.swing.JComboBox<String> jcmbProvincia;
    private javax.swing.JLabel jlblNombre;
    private javax.swing.JLabel jlblNombre1;
    private javax.swing.JLabel jlblRestantesZona;
    private javax.swing.JLabel jlblTag;
    private javax.swing.JLabel jlblTotal1;
    private javax.swing.JLabel jlblTotalLote;
    private javax.swing.JTextField jtxtCuenta;
    private javax.swing.JTextField jtxtLote;
    private javax.swing.JTextField jtxtTag;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.Menu menu3;
    private java.awt.Menu menu4;
    private java.awt.Menu menu5;
    private java.awt.Menu menu6;
    private java.awt.Menu menu7;
    private java.awt.Menu menu8;
    private java.awt.MenuBar menuBar1;
    private java.awt.MenuBar menuBar2;
    private java.awt.MenuBar menuBar3;
    private java.awt.MenuBar menuBar4;
    private javax.swing.JButton startReadButton;
    // End of variables declaration//GEN-END:variables

    public void setjLabelStatus(JLabel jLabelStatus) {
        this.jLabelStatus = jLabelStatus;
    }
    // End of variables declaration


    // End of variables declaration

    public JLabel getjLabelTotalTags() {
        return jLabelTotalTags;
    }
    
     public void setjLabelTotal(JLabel jlblTotal1) {
        this.jlblTotal1 = jlblTotal1;
    }
    // End of variables declaration


    // End of variables declaration

    public JLabel getjLabelTotal() {
        return jlblTotal1;
    }
    

    public JTable getjInventoryTable() {
        return jInventoryTable;
    }
    
    public JTable getjInventoryTable1() {
        return jTable1;
    }
    
     
    
     public JTextField getjTextTags() {
        return jtxtTag;
    }
     
      public void setjjTextTags(JTextField jtxtTag) {
        this.jtxtTag = jtxtTag;
    }
      
      public JTextField getjTextLote() {
        return jtxtLote;
    }
     
      public void setjjTextLote(JTextField jtxtLote) {
        this.jtxtLote = jtxtLote;
    }

      public JComboBox getjComboProvincia() {
        return jcmbProvincia;
    }
     
      public void setjjComboProvincia(JComboBox jcmbProvincia) {
        this.jcmbProvincia = jcmbProvincia;
    }
      
      
      
       public void setjTextCuenta(JTextField jtxtCuenta) {
        this.jtxtCuenta = jtxtCuenta;
        }
       
       public JTextField getjTextCuenta() {
        return jtxtCuenta;
    }
       
       
    // End of variables declaration


    // End of variables declaration

    public JLabel getjLabelTags() {
        return jlblTag;
    }
    
     public void setjLabelTags(JLabel jlblTag) {
        this.jlblTag = jlblTag;
    }
    
  
     public JLabel getjLabelNombre() {
        return jlblNombre;
    }
    
     public void setjLabelNombre(JLabel jlblNombre) {
        this.jlblNombre = jlblNombre;
    }
     
     public void limpiarLeidas() {                                                    
       DefaultTableModel tableModel = (DefaultTableModel)jInventoryTable.getModel();
       synchronized (this)
       {
           tableModel.setRowCount(0);
           rfidBase.tagStore.clear();
           tableModel.setRowCount(25);

           rfidBase.totalTags = 0;
           rfidBase.uniqueTags = 0;
           rfidBase.rowId = 0;
           jLabelTotalTags.setText("");
       }

    }             
    
    
   
    
   
            
            
       
      
      

      
      
      
      
      
}
