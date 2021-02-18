/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zebra;

import com.mot.rfid.api3.*;
import com.mot.rfid.api3.PreFilters.PreFilter;
import ec.gob.igm.cne.dao.TBaseDAO;
import ec.gob.igm.cne.dao.TLoteDAO;
import ec.gob.igm.cne.dao.TUsuarioDAO;
import ec.gob.igm.cne.entidades.TLote;
import ec.gob.igm.cne.entidades.TLotePK;
import ec.gob.igm.cne.entidades.TProvincia;
import ec.gob.igm.cne.entidades.TUsuario;
import ec.gob.igm.cne.documentos.ILoteDocumentos;
import ec.gob.igm.cne.documentos.Item;
//import ec.gob.igm.cne.papeletas.Item;
//import ec.gob.igm.cne.papeletas.LotePapeletas;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Hashtable;
import javax.swing.JLabel;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;


/**
 *
 * @author CFRN78
 */
public class RFIDBase implements ILoteDocumentos {

    /**
     * @return the tLotePK
     */
    public TLotePK gettLotePK() {
        return tLotePK;
    }

    /**
     * @param tLotePK the tLotePK to set
     */
    public void settLotePK(TLotePK tLotePK) {
        this.tLotePK = tLotePK;
    }

    /**
     * @return the tLote
     */
    public TLote gettLote() {
        return tLote;
    }

    /**
     * @param tLote the tLote to set
     */
    public void settLote(TLote tLote) {
        this.tLote = tLote;
    }

    /**
     * @return the tLoteDao
     */
    public TLoteDAO gettLoteDao() {
        return tLoteDao;
    }

    /**
     * @param tLoteDao the tLoteDao to set
     */
    public void settLoteDao(TLoteDAO tLoteDao) {
        this.tLoteDao = tLoteDao;
    }

    /**
     * @return the lstProvincias
     */
    public List<Object[]> getLstProvincias() {
        return lstProvincias;
    }

    /**
     * @param lstProvincias the lstProvincias to set
     */
    public void setLstProvincias(List<Object[]> lstProvincias) {
        this.lstProvincias = lstProvincias;
    }

    /**
     * @return the lstPapeletas
     */
    public List<Object[]> getLstPapeletas() {
        return lstPapeletas;
    }

    /**
     * @param lstPapeletas the lstPapeletas to set
     */
    public void setLstPapeletas(List<Object[]> lstPapeletas) {
        this.lstPapeletas = lstPapeletas;
    }

    /**
     * @return the usuario
     */
    public TUsuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(TUsuario usuario) {
        this.usuario = usuario;
    }
private List<Object[]>lstPapeletas;
private List<Object[]>lstPapeletasLeidas;
 private TUsuario usuario;
 private List<Object[]>lstProvincias;
    private List<Object[]>lstBase;
    private List<Object[]>lstDatosTag;
    private List<Object[]>lstDatosTagEmpacados;
    private List<Object[]>lstDignidades;
    private TBaseDAO base;
    private int intidLote;
    private TLote tLote;
    private TLotePK tLotePK;
    private TProvincia provincia;
    private TLoteDAO tLoteDao;
    private String vid_papeleta;
    private int intlargo_papeleta;
    private String papeleta;
    private String tmpParroquia;
    private String tmpZona;
    private String tmpLote;
    private String tmpSexo;
    private String tmpJunta;
    private int intiddignidad;
 
 
 
    /**
     * @return the papeleta
     */
    public String getPapeleta() {
        return papeleta;
    }

    /**
     * @param papeleta the papeleta to set
     */
    public void setPapeleta(String papeleta) {
        this.papeleta = papeleta;
    }

    /**
     * @return the intlargo_papeleta
     */
    public int getIntlargo_papeleta() {
        return intlargo_papeleta;
    }

    /**
     * @param intlargo_papeleta the intlargo_papeleta to set
     */
    public void setIntlargo_papeleta(int intlargo_papeleta) {
        this.intlargo_papeleta = intlargo_papeleta;
    }

    /**
     * @return the vid_papeleta
     */
    public String getVid_papeleta() {
        return vid_papeleta;
    }

    /**
     * @param vid_papeleta the vid_papeleta to set
     */
    public void setVid_papeleta(String vid_papeleta) {
        this.vid_papeleta = vid_papeleta;
    }

    /**
     * @return the base
     */
    public TBaseDAO getBase() {
        return base;
    }

    /**
     * @param base the base to set
     */
    public void setBase(TBaseDAO base) {
        this.base = base;
    }
       // Generic Interface
       RFIDReader myReader = null;

       RFIDMainDlg mainApp = null;
       public Hashtable tagStore = null;
       
       public static final String API_SUCCESS = "Function Succeeded";
       public static final String PARAM_ERROR = "Parameter Error";
       final String APP_NAME = "J_RFIDHostSample";
       public boolean isConnected;
       public String hostName = null;
       public int port = 5084;

       public boolean isAccessSequenceRunning = false;
       String[] memoryBank = new String[] {"Reserved", "EPC", "TID", "USER"};

       String[] tagState = new String[] {"New", "Gone", "Back", "Moving", "Stationary", "None"};

       // To display tag read count
       public long uniqueTags = 0;
       public long totalTags = 0;

       // Access Filter
       public AccessFilter accessFilter = null;
       public boolean isAccessFilterSet = false;

       // Post Filter
       public PostFilter postFilter = null;
       public boolean isPostFilterSet = false;

       // Antenna Info
       public AntennaInfo antennaInfo = null;

       // Pre Filter
       public PreFilters preFilters = null;

       public PreFilters.PreFilter preFilter1 = null;
       public PreFilters.PreFilter preFilter2 = null;

       public String preFilterTagPattern1 = null;
       public String preFilterTagPattern2 = null;

       public boolean isPreFilterSet1 = false;
       public boolean isPreFilterSet2 = false;
       public int preFilterActionIndex1 = 0;
       public int preFilterActionIndex2 = 0;
       
       public TriggerInfo triggerInfo = null;

       // Reader Management Interface
       ReaderManagement rm = null;
       public boolean isRmConnected = false;
       public LoginInfo loginInfo;
       public int readerTypeIndex = 1;

       public int rowId = 0;
       
       public	String m_strClientCertFilePath;
       public 	String m_strClientKeyFilePath;
       public 	String m_strRootCertFilePath;
       public 	String m_strKeyPassword;
       boolean  m_bSecureConnection;
       boolean  m_bValidatePeer;

       private boolean tagStorageSettingsPhasInfoEnabled = false;
       

    public RFIDReader getMyReader() {
        return myReader;
    }
    
    public RFIDBase(TUsuario usuario)
    { 
       this.base=new TBaseDAO();
        this.usuario=usuario;
        this.lstBase=new ArrayList();
        this.lstDatosTag=new ArrayList();
        this.lstPapeletas=new ArrayList();
        this.lstDatosTagEmpacados=new ArrayList();
        this.lstDignidades=new ArrayList();
        this.lstProvincias=new ArrayList();
        RFIDMainDlg  rFIDMainDlg=new RFIDMainDlg(this);
       // this.lstDignidades=this.base.cargarDignidades(usuario.getUsuario());
        /*for(Object[] dignidades: lstDignidades){
            intiddignidad=((BigDecimal)dignidades[0]).intValue();
        }*/
      JLabel nombreUsuario=rFIDMainDlg.getjLabelNombre();
      nombreUsuario.setText(usuario.getUsuario());
         //JLabel nombreUsuario=new RFIDMainDlg(this).getjLabelNombre();
       // new RFIDMainDlg(this).setjLabelNombre.(nombreUsuario).;
    }

    public RFIDBase(RFIDMainDlg rfidSample)
    {
         //JLabel nombreUsuario=new RFIDMainDlg(this).getjLabelNombre();
       // nombreUsuario.setText(usuario.getUsuario());
        /*this.base=new TBaseDAORES();
        this.usuario=new TUsuario();
        this.usuario.setUsuario("josepacheco");
       // if(pantalla==1)
        //ventana = new FrmLotePapeletas(this);
        this.lstProvincias=new ArrayList();
        this.lstBase=new ArrayList();
        this.lstDatosTag=new ArrayList();
        this.lstPapeletas=new ArrayList();
        this.lstDatosTagEmpacados=new ArrayList();
        this.lstDignidades=new ArrayList();
        this.lstDignidades=this.base.cargarDignidades(usuario.getUsuario());
        for(Object[] dignidades: lstDignidades){
            intiddignidad=((BigDecimal)dignidades[0]).intValue();
        }
         JComboBox cmbProvincia=rfidSample.getjComboProvincia();
        
        cargarProvincia(cmbProvincia);
        if(this.lstProvincias.size()>0)
        cargarDatosLote(cmbProvincia, rfidSample.getjTextLote(), rfidSample.getjInventoryTable(),rfidSample.getjLabelTotal());*/
        /* Generic Reader Inteface */

        // Create Reader Object
        
        myReader = new RFIDReader();

        mainApp = rfidSample;

        // Hash table to hold the tag data
        tagStore = new Hashtable();
        isAccessSequenceRunning = false;

        // Create the Access Filter
        accessFilter = new AccessFilter();

        // create the post filter
        postFilter = new PostFilter();

        // Create Antenna Info
        antennaInfo = new AntennaInfo();

        // Create Pre Filter
        preFilters = new PreFilters();

        preFilter1 = preFilters.new PreFilter();
        preFilter2 = preFilters.new PreFilter();

        triggerInfo = new TriggerInfo();

        triggerInfo.StartTrigger.setTriggerType(START_TRIGGER_TYPE.START_TRIGGER_TYPE_IMMEDIATE);
        triggerInfo.StopTrigger.setTriggerType(STOP_TRIGGER_TYPE.STOP_TRIGGER_TYPE_IMMEDIATE);

        triggerInfo.TagEventReportInfo.setReportNewTagEvent(TAG_EVENT_REPORT_TRIGGER.MODERATED);
        triggerInfo.TagEventReportInfo.setNewTagEventModeratedTimeoutMilliseconds((short)500);

        triggerInfo.TagEventReportInfo.setReportTagInvisibleEvent(TAG_EVENT_REPORT_TRIGGER.MODERATED);
        triggerInfo.TagEventReportInfo.setTagInvisibleEventModeratedTimeoutMilliseconds((short)500);

        triggerInfo.TagEventReportInfo.setReportTagBackToVisibilityEvent(TAG_EVENT_REPORT_TRIGGER.MODERATED);
        triggerInfo.TagEventReportInfo.setTagBackToVisibilityModeratedTimeoutMilliseconds((short)500);

        triggerInfo.setTagReportTrigger(1);
        
        /* Reader Management Interface */
        rm = new ReaderManagement();
        loginInfo = new LoginInfo();
        loginInfo.setUserName("admin");
       

        m_strRootCertFilePath = m_strClientKeyFilePath = m_strClientCertFilePath = "";
        m_bSecureConnection = m_bValidatePeer = false;
    }


    public boolean connectToReader(String readerHostName, int readerPort)
    {
        boolean retVal = false;
        hostName = readerHostName;
        port = readerPort;
        myReader.setHostName(hostName);
        loginInfo.setHostName(hostName);
        myReader.setPort(port);
        mainApp.setTitle("J_RFIDHostSample");
        mainApp.setCursor(Cursor.WAIT_CURSOR);

        try {
            myReader.connect();
            
            myReader.Events.setInventoryStartEvent(true);
            myReader.Events.setInventoryStopEvent(true);
            myReader.Events.setAccessStartEvent(true);
            myReader.Events.setAccessStopEvent(true);
            myReader.Events.setAntennaEvent(true);
            myReader.Events.setGPIEvent(true);
            myReader.Events.setBufferFullEvent(true);
            myReader.Events.setBufferFullWarningEvent(true);
            myReader.Events.setReaderDisconnectEvent(true);
            myReader.Events.setReaderExceptionEvent(true);
            myReader.Events.setTagReadEvent(true);
            
            myReader.Events.setAttachTagDataWithReadEvent(false);
            myReader.Events.setTemperatureAlarmEvent(true);

            myReader.Events.addEventsListener(new EventsHandler(mainApp));
            retVal = true;
            isConnected = true;
            mainApp.setTitle("Connected to " + hostName);
            postStatusNotification(API_SUCCESS, null);

            //update Menu Items
            mainApp.updateGenericMenuItems(true);
            
            int antennaID = 1;
            antennaID += 1;
           Antennas.Config antennaConfig = RFIDMainDlg.rfidBase.getMyReader().Config.Antennas.getAntennaConfig(antennaID);
           // antennaConfig.setReceiveSensitivityIndex((short)1000);
            //short txFreqIndex = (short)jComboBoxTransmitFrequency.getSelectedIndex();
            //txFreqIndex++;
            //antennaConfig.setTransmitFrequencyIndex(txFreqIndex);
             //antennaConfig.setTransmitPowerIndex((short)100);
             RFIDMainDlg.rfidBase.getMyReader().Config.Antennas.setAntennaConfig(antennaID, antennaConfig);
             RFIDMainDlg.rfidBase.postStatusNotification(RFIDBase.API_SUCCESS, null);
            
            
            

        }        
        catch (InvalidUsageException ex) {
            postStatusNotification(PARAM_ERROR, ex.getVendorMessage());
        } catch (OperationFailureException ex) {
            postStatusNotification(ex.getStatusDescription(), ex.getVendorMessage());
        }
        
        mainApp.setCursor(Cursor.DEFAULT_CURSOR);
        mainApp.updateUI();
        return retVal;
    }

    public void disconnectReader()
    {
        try {
                myReader.disconnect();
                isConnected = false;

            //update Menu Items
            mainApp.updateGenericMenuItems(false);

            postStatusNotification(API_SUCCESS, null);
            mainApp.updateUI();

            mainApp.setTitle("J_RFIDHostSample");
            
        } catch (InvalidUsageException ex) {
            postStatusNotification(PARAM_ERROR, ex.getVendorMessage());
            
        } catch (OperationFailureException ex) {
            postStatusNotification(ex.getStatusDescription(), ex.getVendorMessage());

        }
    }

    public void startRead()
    {
        //mainApp.getjComboProvincia().enable(false);
        PostFilter myPostFilter = null;
        AntennaInfo myAntennInfo = null;
        AccessFilter myAccessFilter = null;

        // Set the Antenna Info
        if (antennaInfo.getAntennaID() != null)
            myAntennInfo = antennaInfo;

        // set the post filter
        if (isPostFilterSet)
            myPostFilter = postFilter;

        // set the access filter
        if (isAccessFilterSet)
            myAccessFilter = accessFilter;
        
        try {

            // Access Sequence Operation - Read the specified Memory Bank
            int memoryBankSelected = 0;
            if ( memoryBankSelected > 0)
            {
                TagAccess tagaccess = new TagAccess();
                MEMORY_BANK memoryBank = MEMORY_BANK.MEMORY_BANK_EPC;
                TagAccess.Sequence opSequence = tagaccess.new Sequence(tagaccess);
                TagAccess.Sequence.Operation op1 = opSequence.new Operation();
                op1.setAccessOperationCode(ACCESS_OPERATION_CODE.ACCESS_OPERATION_READ);

                switch (--memoryBankSelected)
                {
                    case 0:
                        memoryBank = MEMORY_BANK.MEMORY_BANK_RESERVED;
                        break;
                    case 1:
                        memoryBank = MEMORY_BANK.MEMORY_BANK_EPC;
                        break;
                    case 2:
                        memoryBank = MEMORY_BANK.MEMORY_BANK_TID;
                        break;
                    case 3:
                        memoryBank = MEMORY_BANK.MEMORY_BANK_USER;
                        break;
                }
                op1.ReadAccessParams.setMemoryBank(memoryBank);
                op1.ReadAccessParams.setByteCount(0);
                op1.ReadAccessParams.setByteOffset(0);
                op1.ReadAccessParams.setAccessPassword(0);
                myReader.Actions.TagAccess.OperationSequence.deleteAll();
                myReader.Actions.TagAccess.OperationSequence.add(op1);
                myReader.Actions.purgeTags();
                myReader.Actions.TagAccess.OperationSequence.performSequence(myAccessFilter, triggerInfo, myAntennInfo);

                isAccessSequenceRunning = true;

            }
            else // Simple Inventory
            {
                myReader.Actions.purgeTags();
                myReader.Actions.Inventory.perform(myPostFilter, triggerInfo, myAntennInfo);
            }
            postStatusNotification(API_SUCCESS, null);

        } catch (InvalidUsageException ex) {
            postStatusNotification(PARAM_ERROR, ex.getVendorMessage());
        } catch (OperationFailureException ex) {
                postStatusNotification(ex.getStatusDescription(), ex.getVendorMessage());
        }
        
    }

    public void stopRead()
    {
        try {
            if (isAccessSequenceRunning)
            {
                myReader.Actions.TagAccess.OperationSequence.stopSequence();
                myReader.Actions.TagAccess.OperationSequence.deleteAll();
                isAccessSequenceRunning = false;
            }
            else
            {
                myReader.Actions.Inventory.stop();
            }
            postStatusNotification(API_SUCCESS, null);

        } catch (InvalidUsageException ex) {
            postStatusNotification(PARAM_ERROR, ex.getVendorMessage());
        } catch (OperationFailureException ex) {
            postStatusNotification(ex.getStatusDescription(), ex.getVendorMessage());
        }

    }

    public boolean logIn(READER_TYPE readerType)
    {
        boolean retVal = false;
        mainApp.setCursor(Cursor.WAIT_CURSOR);

        try {
            rm.login(loginInfo, readerType);
            retVal = true;
            isRmConnected = true;
            postStatusNotification(API_SUCCESS, null);

            //update Menu Items
            mainApp.updateRMMenuItems(true);


        } catch (InvalidUsageException ex) {
            postStatusNotification(PARAM_ERROR, ex.getVendorMessage());
        } catch (OperationFailureException ex) {
            postStatusNotification(ex.getStatusDescription(), ex.getVendorMessage());
        }
        mainApp.setCursor(Cursor.DEFAULT_CURSOR);
        return retVal;
    }

    public void logOut() {
        try {
            rm.logout();
            postStatusNotification(API_SUCCESS, null);
        } catch (InvalidUsageException ex) {
            postStatusNotification(PARAM_ERROR, ex.getVendorMessage());

        } catch (OperationFailureException ex) {
            postStatusNotification(ex.getStatusDescription(), ex.getVendorMessage());
        }
        isRmConnected = false;
        //update Menu Items
        mainApp.updateRMMenuItems(false);
        
    }



    public void postStatusNotification(String statusMsg, String vendorMsg)
    {
        String status;
        if (vendorMsg != null &&
            !vendorMsg.isEmpty())
            status = statusMsg + "[" + vendorMsg + "]";
        else
            status = statusMsg;

        mainApp.getjLabelStatus().setText(status);
    }

    public String getSelectedTagID()
    {
        Object tagID = null;
        TableModel tableModel = mainApp.getjInventoryTable().getModel();
        int selectedRow = mainApp.getjInventoryTable().getSelectedRow();
        if (selectedRow >= 0)
        {
            tagID = ((DefaultTableModel)tableModel).getValueAt(selectedRow, 0);
        }
        return String.valueOf(tagID);
        
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
    public static String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    public MEMORY_BANK getMemoryBankEnum(int index)
    {
        MEMORY_BANK memBank = MEMORY_BANK.MEMORY_BANK_EPC;
        switch(index)
        {
            case 0:
                memBank = MEMORY_BANK.MEMORY_BANK_RESERVED;
                break;
            case 1:
                memBank = MEMORY_BANK.MEMORY_BANK_EPC;
                break;
            case 2:
                memBank = MEMORY_BANK.MEMORY_BANK_TID;
                break;
            case 3:
                memBank = MEMORY_BANK.MEMORY_BANK_USER;
                break;
        }
        return memBank;
    }

    public void setTagStorageSettingsPhasInfoEnabled(boolean value)
    {
       tagStorageSettingsPhasInfoEnabled = value;
    }

    public boolean getTagStorageSettingsPhasInfoEnabled()
    {
       return tagStorageSettingsPhasInfoEnabled;
    }
    
   
     
    

      /*
    Carga las provincias que ya se encuentran impresas las etiquetas RFID, estado GENERADA
    */
    @Override
    public void cargarProvincia(JComboBox cmbProvincia) {
        //this.usuario.setUsuario("josepacheco");
        //this.usuario.setIdDignidad(Short.parseShort("1"));
       this.base=new TBaseDAO();
       
        this.lstProvincias.addAll(base.cargarProvincia("GENERADA"));
       //this.setLstProvincias(base.cargarProvincia(getUsuario().getIdDignidad(),"GENERADA"));
       cmbProvincia.removeAllItems();
       Item provincia1 = new Item(0,"Seleccione una provincia");
       cmbProvincia.addItem(provincia1);
       for (Object[] provincias : getLstProvincias()) {
            Item provincia = new Item(((BigDecimal)provincias[0]).intValue(),provincias[1].toString());
            cmbProvincia.addItem(provincia);
        }
       
    }
    
    
    @Override
    public void cargarDatosLote(JComboBox cmbProvincia,JTextField jtxtLote,JTable tblJuntas,JLabel jlblTotal) {
       jtxtLote.setText("");
        this.base=new TBaseDAO();
       this.settLoteDao(new TLoteDAO());
       this.settLote(new TLote());
       this.provincia=new TProvincia();
       
       Item provinciaSeleccionada = (Item)cmbProvincia.getSelectedItem();
       if(provinciaSeleccionada.getId()>0)
       {
           
       provincia.setIdProvincia(provinciaSeleccionada.getId());
       int idLote=this.gettLoteDao().cargarLote(provinciaSeleccionada.getId(),"ABIERTO",getUsuario().getUsuario());
       if(idLote>0){
       jtxtLote.setText(String.valueOf(idLote));
       this.lstBase=this.base.cargarDatosLote(idLote, provinciaSeleccionada.getId(),"EMPACADA");
       jlblTotal.setText(String.valueOf(lstBase.size()));
       DefaultTableModel model = (DefaultTableModel) tblJuntas.getModel();
       model.setRowCount(0);

                for (Object[] base : lstBase)
                {
                    model.addRow(new Object[]   {
                        base[0],
                        base[1],
                        base[2],
                        base[3],
                        base[4],
                        base[6],
                        base[7],
                        base[8],
                        base[9]
                        
                                                });
                }
              
       }
       else
       {
           idLote=this.gettLoteDao().cargarMaximoLote()>0?this.gettLoteDao().cargarMaximoLote():1;
           jtxtLote.setText(String.valueOf(idLote));
           this.gettLote().setEstado("ABIERTO");
           this.gettLote().setFechaCreacion(new Date());
           //this.usuario.setBodega(BigInteger.ONE);
           Short bodega=new Short(getUsuario().getBodega().shortValue());
            settLotePK(new TLotePK());
           this.gettLote().setIdBodega(bodega);
           this.gettLotePK().setIdLote(idLote);
           this.gettLotePK().setTipo("DOCUMENTO");
           this.gettLote().setTLotePK(gettLotePK());
           this.gettLote().setIdProvincia(provincia);
           this.gettLote().setUsuarioCreacion(getUsuario().getUsuario());
           try {
               this.gettLoteDao().insertar(this.gettLote());
           } catch (Exception ex) {
               Logger.getLogger(RFIDBase.class.getName()).log(Level.SEVERE, null, ex);
           }
           this.lstBase=this.base.cargarDatosLote(idLote, provinciaSeleccionada.getId(),"EMPACADA");
       DefaultTableModel model = (DefaultTableModel) tblJuntas.getModel();
       model.setRowCount(0);

                for (Object[] base : lstBase)
                {
                    model.addRow(new Object[]   {
                        base[0],
                        base[1],
                        base[2],
                        base[3],
                        base[4],
                        base[6],
                        base[7],
                        base[8],
                        base[9]
                       
                                                });
                }
                
       }
       }
       
    }
    
  
//    @Override
    public List<Object[]> leerPapeletas(JTextField jtxtLote, JTextField jtxtTag,JComboBox cmbProvincia,JTextField jtxtCuenta,JLabel jlblTag,JTable tblJuntas,JLabel jlblTotal) {
        String vProvinciaTag,vIdParroquia,vIdZona,vIdJunta,vTipoTag,vTag,vIdLote,vIdSexo,vPaquete,vDignidadTag;
       // int contador
             
        BigDecimal conta,contador,contaP,cero,resultado;
        String lote;
        lote=jtxtLote.getText();

        int resultadomensaje=0;

        Item provinciaSeleccionada = (Item)cmbProvincia.getSelectedItem();
        cero=new BigDecimal("0");
        this.base=new TBaseDAO();
        setVid_papeleta(jtxtTag.getText().toUpperCase().trim());
        setPapeleta("");
        setIntlargo_papeleta(getVid_papeleta().length());
        if(getIntlargo_papeleta()==23)
            setPapeleta(getVid_papeleta().concat("0"));
        else 
            if(getIntlargo_papeleta()>=24)
            setPapeleta(getVid_papeleta().substring(0,24));
        this.lstDatosTag=this.base.cargarDatosTag(getPapeleta());
        if(lstDatosTag.size()>0)
        {
            for (Object[] datosTag : lstDatosTag){
                 vProvinciaTag=datosTag[0].toString();
                 if(Integer.parseInt(vProvinciaTag)!=provinciaSeleccionada.getId()){
                     JOptionPane.showMessageDialog(null, "EL PAQUETE NO PERTENECE A ESTA PROVINCIA", "ERROR", JOptionPane.ERROR_MESSAGE);
                 jtxtTag.setText("");
                 jtxtTag.setFocusable(true);
                 contador=new BigDecimal(0);
                 //contador=0;
                 //resultadomensaje=1;
                 //return resultadomensaje;
                         }
                 this.tmpParroquia=datosTag[4].toString();
                 this.tmpZona=datosTag[6].toString();
                 this.tmpJunta=datosTag[7].toString();
                 this.tmpSexo=datosTag[11].toString();
                  vDignidadTag=datosTag[14].toString();
                 if(Integer.parseInt(vDignidadTag)!=getUsuario().getIdDignidad()){
                 JOptionPane.showMessageDialog(null, "EL PAQUETE NO PERTENECE A ESTA DIGNIDAD", "ERROR", JOptionPane.ERROR_MESSAGE);
                 jtxtTag.setText("");
                 jtxtTag.setFocusable(true);
                 //contador=0;
                 contador=new BigDecimal(0);
                 resultadomensaje=2;
                 //return resultadomensaje;
                 }
                 
                 if(datosTag[10]!=null)
                 this.tmpLote=datosTag[10].toString();
                 else
                     this.tmpLote="";
                
                 /*if(tmpLote!="")
                 {
                 contador=this.base.generarTotalJunta(Integer.parseInt(tmpParroquia),Integer.parseInt(tmpZona),Integer.parseInt(tmpJunta),intiddignidad);
                 conta=this.base.generarTotalJuntaNoEmpacados(Integer.parseInt(tmpParroquia),Integer.parseInt(tmpZona),Integer.parseInt(tmpJunta),intiddignidad);
                 }*/
                 contador=this.base.generarTotalJunta(Integer.parseInt(tmpParroquia),Integer.parseInt(tmpZona),Integer.parseInt(tmpJunta));
                 conta=this.base.generarTotalJuntaNoEmpacados(Integer.parseInt(tmpParroquia),Integer.parseInt(tmpZona),Integer.parseInt(tmpJunta));
                /* else
                 {
                     contador=new BigDecimal(0);
                     conta=new BigDecimal(0);
                 }*/
                 resultado=contador.subtract(conta);
                 jtxtCuenta.setText(String.valueOf(resultado));
                 vIdParroquia=datosTag[4].toString();
                 vIdZona=datosTag[6].toString();
                 vIdJunta=datosTag[7].toString();
                 vTipoTag=datosTag[8].toString();
                 vTag=datosTag[9].toString();
                  if(datosTag[10]!=null)
                 vIdLote=datosTag[10].toString();
                  else
                      vIdLote="";
                 vIdSexo=datosTag[11].toString();
                 
                 if(!vTipoTag.equals("PAPELETA"))
                 {
                     JOptionPane.showMessageDialog(null, "EL PAQUETE NO ES UNA PAPELETA", "ERROR", JOptionPane.ERROR_MESSAGE);
                     this.tmpJunta="";
                     this.tmpParroquia="";
                     this.tmpZona="";
                     contador=new BigDecimal(0);
                     jtxtTag.setText("");
                     jtxtTag.setFocusable(true);
                     resultadomensaje=3;
                 //return resultadomensaje;
                 }
                 
                 if(vIdLote!=""){
                     JOptionPane.showMessageDialog(null, "EL PAQUETE PERTENECE AL LOTE NRO. " + vIdLote, "ERROR", JOptionPane.ERROR_MESSAGE);
                     this.tmpJunta="";
                     this.tmpParroquia="";
                     this.tmpZona="";
                     contador=new BigDecimal(0);
                     jtxtTag.setText("");
                     jtxtTag.setFocusable(true);
                     resultadomensaje=4;
                 //return resultadomensaje;
                 }
                 contaP=this.base.generarTotalJuntaNoEmpacadosTag(jtxtTag.getText());
                 
                 if(contaP.compareTo(cero)==1)
                 {
                     JOptionPane.showMessageDialog(null, "...ESTA ETIQUETA DE PAPELETA YA FUE REGISTRADA EN EL LOTE .." + vIdLote, "ERROR", JOptionPane.ERROR_MESSAGE);
                     jtxtTag.setText("");
                     jtxtTag.setFocusable(true);
                     resultadomensaje=5;
                 //return resultadomensaje;
                 }
                 
                 vPaquete=datosTag[1].toString().trim()+ " - "+datosTag[3].toString().trim()+ " - "
                          +datosTag[5].toString().trim()+" - "+datosTag[6].toString().trim()+" - "
                          +datosTag[8].toString().trim()+" - "+datosTag[7].toString().trim()+" - ";
                 jlblTag.setText(vPaquete);
                 
                 this.base.actualizarEstadoTag("EMPACADA", Integer.parseInt(jtxtLote.getText()), new Date(), getUsuario().getUsuario(), vTag);
                 this.setLstPapeletas(this.base.cargarDatosLoteFechaPega(Integer.parseInt(jtxtLote.getText()), provinciaSeleccionada.getId(), "EMPACADA"));
                  DefaultTableModel model = (DefaultTableModel) tblJuntas.getModel();
                  model.setRowCount(0);
                /*  for (Object[] base : getLstPapeletas()){
model.insertRow(rowId, new Object[] {base[0],base[1], base[2], base[3], base[4], base[5],base[6],base[7], base[8],base[9]});
                  }*/
                  
                for (Object[] base : getLstPapeletas())
                {
                    model.addRow(new Object[]   {
                        base[0],
                        base[1],
                        base[2],
                        base[3],
                        base[4],
                        base[6],
                        base[7],
                        base[8],
                        base[9]
                       
                                                });
                }
                
                jlblTotal.setText(String.valueOf(getLstPapeletas().size()));
                jtxtTag.setText("");
                jtxtTag.setFocusable(true);

            }
    }
           
        jtxtTag.setText("");
        jtxtTag.setFocusable(true);    
                 return this.lstPapeletas; 
    }
 
    public int leerPapeleta(JTextField jtxtLote, JTextField jtxtTag,JComboBox cmbProvincia,JTextField jtxtCuenta,JLabel jlblTag,JTable tblJuntas,JLabel jlblTotal,JButton jButton1) {
      /*  jButton1.addActionListener(new ActionListener() {
			
  public void actionPerformed(ActionEvent e) {
    jlblTotal.setText("Has pulsado el botón ");			
  }	
});*/
       /*Action sendAction = new AbstractAction("Send") {
       public void actionPerformed(ActionEvent e) {
            startRead();
       }
};*/

            
            
           // jButton1.setAction(sendAction);
        String vProvinciaTag,vIdParroquia,vIdZona,vIdJunta,vTipoTag,vTag,vIdLote,vIdSexo,vPaquete,vDignidadTag;
       // int contador
             
        BigDecimal conta,contador,contaP,cero,resultado;

String lote;
        lote=jtxtLote.getText();
        int resultadomensaje=0;

        Item provinciaSeleccionada = (Item)cmbProvincia.getSelectedItem();
        cero=new BigDecimal("0");
        this.base=new TBaseDAO();
        setVid_papeleta(jtxtTag.getText().toUpperCase().trim());
        setPapeleta("");
        setIntlargo_papeleta(getVid_papeleta().length());
        if(getIntlargo_papeleta()==23)
            setPapeleta(getVid_papeleta().concat("0"));
        else 
            if(getIntlargo_papeleta()>=24)
            setPapeleta(getVid_papeleta().substring(0,24));
        this.lstDatosTag=this.base.cargarDatosTag(getPapeleta());
        if(lstDatosTag.size()>0)
        {
            for (Object[] datosTag : lstDatosTag){
                 vProvinciaTag=datosTag[0].toString();
                 if(Integer.parseInt(vProvinciaTag)!=provinciaSeleccionada.getId()){
                     //JOptionPane.showMessageDialog(null, "EL PAQUETE NO PERTENECE A ESTA PROVINCIA", "ERROR", JOptionPane.ERROR_MESSAGE);
                 jtxtTag.setText("");
                 jtxtTag.setFocusable(true);
                 contador=new BigDecimal(0);
                 //contador=0;
                 //resultadomensaje=1;
                 //return resultadomensaje;
                         }
                 this.tmpParroquia=datosTag[4].toString();
                 this.tmpZona=datosTag[6].toString();
                 this.tmpJunta=datosTag[7].toString();
                 this.tmpSexo=datosTag[11].toString();
                  vDignidadTag=datosTag[14].toString();
                 if(Integer.parseInt(vDignidadTag)!=getUsuario().getIdDignidad()){
                 //JOptionPane.showMessageDialog(null, "EL PAQUETE NO PERTENECE A ESTA DIGNIDAD", "ERROR", JOptionPane.ERROR_MESSAGE);
                 jtxtTag.setText("");
                 jtxtTag.setFocusable(true);
                 //contador=0;
                 contador=new BigDecimal(0);
                 resultadomensaje=2;
                 //return resultadomensaje;
                 }
                 
                 if(datosTag[10]!=null)
                 this.tmpLote=datosTag[10].toString();
                 else
                     this.tmpLote="";
                
                 /*if(tmpLote!="")
                 {
                 contador=this.base.generarTotalJunta(Integer.parseInt(tmpParroquia),Integer.parseInt(tmpZona),Integer.parseInt(tmpJunta),intiddignidad);
                 conta=this.base.generarTotalJuntaNoEmpacados(Integer.parseInt(tmpParroquia),Integer.parseInt(tmpZona),Integer.parseInt(tmpJunta),intiddignidad);
                 }*/
                 contador=this.base.generarTotalJunta(Integer.parseInt(tmpParroquia),Integer.parseInt(tmpZona),Integer.parseInt(tmpJunta));
                 conta=this.base.generarTotalJuntaNoEmpacados(Integer.parseInt(tmpParroquia),Integer.parseInt(tmpZona),Integer.parseInt(tmpJunta));
                /* else
                 {
                     contador=new BigDecimal(0);
                     conta=new BigDecimal(0);
                 }*/
                 resultado=contador.subtract(conta);
                 jtxtCuenta.setText(String.valueOf(resultado));
                 vIdParroquia=datosTag[4].toString();
                 vIdZona=datosTag[6].toString();
                 vIdJunta=datosTag[7].toString();
                 vTipoTag=datosTag[8].toString();
                 vTag=datosTag[9].toString();
                  if(datosTag[10]!=null)
                 vIdLote=datosTag[10].toString();
                  else
                      vIdLote="";
                 vIdSexo=datosTag[11].toString();
                 
                 if(!vTipoTag.equals("PAPELETA"))
                 {
                     //JOptionPane.showMessageDialog(null, "EL PAQUETE NO ES UNA PAPELETA", "ERROR", JOptionPane.ERROR_MESSAGE);
                     this.tmpJunta="";
                     this.tmpParroquia="";
                     this.tmpZona="";
                     contador=new BigDecimal(0);
                     jtxtTag.setText("");
                     jtxtTag.setFocusable(true);
                     resultadomensaje=3;
                 //return resultadomensaje;
                 }
                 
                 if(vIdLote!=""){
                     //JOptionPane.showMessageDialog(null, "EL PAQUETE PERTENECE AL LOTE NRO. " + vIdLote, "ERROR", JOptionPane.ERROR_MESSAGE);
                     this.tmpJunta="";
                     this.tmpParroquia="";
                     this.tmpZona="";
                     contador=new BigDecimal(0);
                     jtxtTag.setText("");
                     jtxtTag.setFocusable(true);
                     resultadomensaje=4;
                 //return resultadomensaje;
                 }
                 contaP=this.base.generarTotalJuntaNoEmpacadosTag(jtxtTag.getText());
                 
                 if(contaP.compareTo(cero)==1)
                 {
                     //JOptionPane.showMessageDialog(null, "...ESTA ETIQUETA DE PAPELETA YA FUE REGISTRADA EN EL LOTE .." + vIdLote, "ERROR", JOptionPane.ERROR_MESSAGE);
                     jtxtTag.setText("");
                     jtxtTag.setFocusable(true);
                     resultadomensaje=5;
                 //return resultadomensaje;
                 }
                 
                 vPaquete=datosTag[1].toString().trim()+ " - "+datosTag[3].toString().trim()+ " - "
                          +datosTag[5].toString().trim()+" - "+datosTag[6].toString().trim()+" - "
                          +datosTag[8].toString().trim()+" - "+datosTag[7].toString().trim()+" - ";
                 jlblTag.setText(vPaquete);
                 
                 this.base.actualizarEstadoTag("EMPACADA", Integer.parseInt(lote), new Date(), getUsuario().getUsuario(), vTag);
                 this.setLstPapeletas(this.base.cargarDatosLoteFechaPega(Integer.parseInt(lote), provinciaSeleccionada.getId(), "EMPACADA"));
                  DefaultTableModel model = (DefaultTableModel) tblJuntas.getModel();
                  model.setRowCount(0);
                /*  for (Object[] base : getLstPapeletas()){
model.insertRow(rowId, new Object[] {base[0],base[1], base[2], base[3], base[4], base[5],base[6],base[7], base[8],base[9]});
                  }*/
                  
                for (Object[] base : getLstPapeletas())
                {
                    model.addRow(new Object[]   {
                        base[0],
                        base[1],
                        base[2],
                        base[3],
                        base[4],
                        base[6],
                        base[7],
                        base[8],
                        base[9]
                       
                                                });
                }
                
                jlblTotal.setText(String.valueOf(getLstPapeletas().size()));
                jtxtTag.setText("");
                jtxtTag.setFocusable(true);
                
              

            }
    }
           
        jtxtTag.setText("");
        jtxtTag.setFocusable(true);
         try {
            myReader.Actions.Inventory.stop();
        } catch (InvalidUsageException ex) {
            Logger.getLogger(RFIDBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OperationFailureException ex) {
            Logger.getLogger(RFIDBase.class.getName()).log(Level.SEVERE, null, ex);
        }
                 return 0; 
    }
    
    @Override
    public void eliminar(JTable tblJuntas){
        this.base=new TBaseDAO();
        DefaultTableModel tb = (DefaultTableModel) tblJuntas.getModel();
        int fila=tblJuntas.getSelectedRow();
        String tag;
        tag=tblJuntas.getValueAt(fila, 6).toString();
         if (fila >= 0) {
             this.base.actualizarEstadoGeneradaDocumento("GENERADA","DOCUMENTO",tag);
             tb.removeRow(fila);
           /*int []filasselec  = tblJuntas.getSelectedRows();
           for (int i=0; i<filasselec.length;i++)
           {
            tb.removeRow(filasselec.length);
           }*/
         }
    
    }

    
     @Override
    public int leer(JTextField jtxtLote, JTextField jtxtTag,JComboBox cmbProvincia,JTextField jtxtCuenta,JLabel jlblTag,JTable tblJuntas,JLabel jlblTotal) {
        String vProvinciaTag,vIdParroquia,vIdZona,vIdJunta,vTipoTag,vTag,vIdLote,vIdSexo,vPaquete;
        BigDecimal conta,contador,contaP,cero,resultado;
        String lote;
        lote=jtxtLote.getText();
        int resultadomensaje=0;
        Item provinciaSeleccionada = (Item)cmbProvincia.getSelectedItem();
        cero=new BigDecimal("0");
        this.base=new TBaseDAO();
        setVid_papeleta(jtxtTag.getText().toUpperCase().trim());
        setPapeleta("");
        setIntlargo_papeleta(getVid_papeleta().length());
       
        if(getIntlargo_papeleta()==23)
            setPapeleta(getVid_papeleta().concat("0"));
        else {
            if(getIntlargo_papeleta()>=24)
            setPapeleta(getVid_papeleta().substring(0,24));
            else if (getIntlargo_papeleta() < 23)
                 setPapeleta(getVid_papeleta().concat("00"));
                 else{
                 setPapeleta(getVid_papeleta().substring(0,24)); 
                 }
        }
        
        this.lstDatosTag=this.base.cargarDatosTag(getPapeleta());
        if(lstDatosTag.size()>0)
        {
            for (Object[] datosTag : lstDatosTag){
                 vProvinciaTag=datosTag[0].toString();
                 if(Integer.parseInt(vProvinciaTag)!=provinciaSeleccionada.getId()){
                     //JOptionPane.showMessageDialog(null, "EL PAQUETE NO PERTENECE A ESTA PROVINCIA", "ERROR", JOptionPane.ERROR_MESSAGE);
                 jtxtTag.setText("");
                 jtxtTag.setFocusable(true);
                 contador=new BigDecimal(0);
                 //contador=0;
                 resultadomensaje=1;
                 return resultadomensaje;
                         }
                 this.tmpParroquia=datosTag[4].toString();
                 this.tmpZona=datosTag[6].toString();
                 this.tmpJunta=datosTag[7].toString();
                 this.tmpSexo=datosTag[11].toString();
                 // vDignidadTag=datosTag[14].toString();
                  System.out.print(this.usuario.getIdDignidad());
                  System.out.print(this.usuario.getIdUsuario());
                  System.out.print(this.usuario.getBodega());
                  
                 /*if(Integer.parseInt(vDignidadTag)!=this.usuario.getIdDignidad()){
                 //JOptionPane.showMessageDialog(null, "EL PAQUETE NO PERTENECE A ESTA DIGNIDAD", "ERROR", JOptionPane.ERROR_MESSAGE);
                 jtxtTag.setText("");
                 jtxtTag.setFocusable(true);
                 //contador=0;
                 contador=new BigDecimal(0);
                 resultadomensaje=2;
                 //return resultadomensaje;
                 }*/
                 
                 if(datosTag[10]!=null)
                 this.tmpLote=datosTag[10].toString();
                 else
                     this.tmpLote="";
                
                 /*if(tmpLote!="")
                 {
                 contador=this.base.generarTotalJunta(Integer.parseInt(tmpParroquia),Integer.parseInt(tmpZona),Integer.parseInt(tmpJunta),intiddignidad);
                 conta=this.base.generarTotalJuntaNoEmpacados(Integer.parseInt(tmpParroquia),Integer.parseInt(tmpZona),Integer.parseInt(tmpJunta),intiddignidad);
                 }*/
                 contador=this.base.generarTotalJunta(Integer.parseInt(tmpParroquia),Integer.parseInt(tmpZona),Integer.parseInt(tmpJunta));
                 conta=this.base.generarTotalJuntaNoEmpacados(Integer.parseInt(tmpParroquia),Integer.parseInt(tmpZona),Integer.parseInt(tmpJunta));
                /* else
                 {
                     contador=new BigDecimal(0);
                     conta=new BigDecimal(0);
                 }*/
                 resultado=contador.subtract(conta);
                 jtxtCuenta.setText(String.valueOf(resultado));
                 vIdParroquia=datosTag[4].toString();
                 vIdZona=datosTag[6].toString();
                 vIdJunta=datosTag[7].toString();
                 vTipoTag=datosTag[8].toString();
                 vTag=datosTag[9].toString();
                  if(datosTag[10]!=null)
                 vIdLote=datosTag[10].toString();
                  else
                      vIdLote="";
                 vIdSexo=datosTag[11].toString();
                 
                 if(!vTipoTag.equals("DOCUMENTO"))
                 {
                     //JOptionPane.showMessageDialog(null, "EL PAQUETE NO ES UNA PAPELETA", "ERROR", JOptionPane.ERROR_MESSAGE);
                     this.tmpJunta="";
                     this.tmpParroquia="";
                     this.tmpZona="";
                     contador=new BigDecimal(0);
                     jtxtTag.setText("");
                     jtxtTag.setFocusable(true);
                     resultadomensaje=3;
                 return resultadomensaje;
                 }
                 
                 if(vIdLote!=""){
                     //JOptionPane.showMessageDialog(null, "EL PAQUETE PERTENECE AL LOTE NRO. " + vIdLote, "ERROR", JOptionPane.ERROR_MESSAGE);
                     this.tmpJunta="";
                     this.tmpParroquia="";
                     this.tmpZona="";
                     contador=new BigDecimal(0);
                     jtxtTag.setText("");
                     jtxtTag.setFocusable(true);
                     resultadomensaje=4;
                 return resultadomensaje;
                 }
                 contaP=this.base.generarTotalJuntaNoEmpacadosTag(jtxtTag.getText());
                 
                 if(contaP.compareTo(cero)==1)
                 {
                     //JOptionPane.showMessageDialog(null, "...ESTA ETIQUETA DE PAPELETA YA FUE REGISTRADA EN EL LOTE .." + vIdLote, "ERROR", JOptionPane.ERROR_MESSAGE);
                     jtxtTag.setText("");
                     jtxtTag.setFocusable(true);
                     resultadomensaje=5;
                 return resultadomensaje;
                 }
                 
                 vPaquete=datosTag[1].toString().trim()+ " - "+datosTag[3].toString().trim()+ " - "
                          +datosTag[5].toString().trim()+" - "+datosTag[6].toString().trim()+" - "
                          +datosTag[8].toString().trim()+" - "+datosTag[7].toString().trim()+" - ";
                 jlblTag.setText(vPaquete);
                 
                 this.base.actualizarEstadoTag("EMPACADA", Integer.parseInt(lote), new Date(), getUsuario().getUsuario(), vTag);
                 this.setLstPapeletas(this.base.cargarDatosLoteFechaPega(Integer.parseInt(lote), provinciaSeleccionada.getId(), "EMPACADA"));
                  DefaultTableModel model = (DefaultTableModel) tblJuntas.getModel();
                  model.setRowCount(0);
                /*  for (Object[] base : getLstPapeletas()){
model.insertRow(rowId, new Object[] {base[0],base[1], base[2], base[3], base[4], base[5],base[6],base[7], base[8],base[9]});
                  }*/
                  
                for (Object[] base : getLstPapeletas())
                {
                    model.addRow(new Object[]   {
                        base[0],
                        base[1],
                        base[2],
                        base[3],
                        base[4],
                        base[6],
                        base[7],
                        base[8],
                        base[9]
                       
                                                });
                }
                
                jlblTotal.setText(String.valueOf(getLstPapeletas().size()));
                jtxtTag.setText("");
                jtxtTag.setFocusable(true);
               

            }
    }
           
        jtxtTag.setText("");
        jtxtTag.setFocusable(true);
        //jlblTotal.setText("stop");
        /*try {
            myReader.Actions.Inventory.stop();
        } catch (InvalidUsageException ex) {
            Logger.getLogger(RFIDBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OperationFailureException ex) {
            Logger.getLogger(RFIDBase.class.getName()).log(Level.SEVERE, null, ex);
        }*/
                 return resultadomensaje; 
    }

      @Override
    public void cerrarLote(JTextField jtxtLote,JTextField jtxtTag,JLabel jlblTotal,JTable tblJuntas,JComboBox cmbProvincia, JLabel jlblTag) {
        this.base=new TBaseDAO();
        if(jtxtLote.getText()!="")
        this.lstDatosTagEmpacados=this.base.cargarDatosLoteEmpacados(Integer.parseInt(jtxtLote.getText()),"DOCUMENTO");
        if(this.lstDatosTagEmpacados.size()>0)
        {
        this.base.actualizarEstadoTagCerrado("CERRADO", Integer.parseInt(jtxtLote.getText()), new Date(), getUsuario().getUsuario(),"DOCUMENTO");
        if (JOptionPane.showConfirmDialog(null, "Lote cerrado con éxito..., ¿desea abrir otro lote?",null, JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        
            cargarDatosLote(cmbProvincia,jtxtLote,tblJuntas,jlblTotal);
         
        
        else
            jtxtLote.setText("");
        //JOptionPane.showMessageDialog(null, "Lote cerrado con éxito...", "GUARDADO", JOptionPane.OK_OPTION);
       
         
        }
        else{
            
           JOptionPane.showMessageDialog(null, "No se cerró el lote...", "ERROR", JOptionPane.ERROR_MESSAGE); 
            
        }
        jtxtTag.setText("");
         jlblTotal.setText("...");
         jlblTag.setText("....");
         this.lstDatosTagEmpacados=null;
         this.limpiarJTable(tblJuntas);
           
    }    
    
    /**
     * Limpia la tabla de juntas
     * @param tblJuntas 
     */
    public void limpiarJTable(JTable tblJuntas){
        DefaultTableModel tb = (DefaultTableModel) tblJuntas.getModel();
        int a = tblJuntas.getRowCount()-1;
        for (int i = a; i >= 0; i--) {          
        tb.removeRow(tb.getRowCount()-1);
        }
        //cargaTicket();
    }

    public class EventsHandler implements RfidEventsListener
    {
        RFIDMainDlg inventoryUI = null;
        

        public EventsHandler(RFIDMainDlg rfidSample){
            inventoryUI = rfidSample;
            JLabel nombreUsuario=inventoryUI.getjLabelNombre();
            TUsuarioDAO tuDAO = new TUsuarioDAO();
            usuario = tuDAO.buscarPorUsuario(nombreUsuario.getText());
        }
        
       
        
        
        
        
        
        /*public void eventReadNotify(RfidReadEvents e){
        // Recommended to use new method getReadTagsEx for better performance in case of large
        // tag population
       
        TagData[] myTags = myReader.Actions.getReadTags(1);
        //Tag tagdata=myReader.Actions.getReadTags(1);
        TableModel tableModel = inventoryUI.getjInventoryTable().getModel();
            JTable tblJuntas=inventoryUI.getjInventoryTable();
            //JLabel totalTagsLabel = inventoryUI.getjLabelTotalTags();
            JTextField jtxtTag=inventoryUI.getjTextTags();
            JTextField jtxtLote=inventoryUI.getjTextLote();
            JComboBox cmbProvincia=inventoryUI.getjComboProvincia();
            JTextField jtxtCuenta=inventoryUI.getjTextCuenta();
            JLabel jlblTag=inventoryUI.getjLabelTags();
            JLabel jlblTotal=inventoryUI.getjLabelTotal();
            JButton jButton1=inventoryUI.getjBotonTags();
           
            
        if (myTags != null) {
            for (int index = 0; index < myTags.length;index++)
            {
                String tagE=new String("H"+myTags[0].getTagID().substring(0, myTags[0].getTagID().length()-1));
                System.out.println("Tag ID " + myTags[0].getTagID());
                jtxtTag.setText(tagE);
               //leerPapeleta(jtxtLote,jtxtTag,cmbProvincia,jtxtCuenta,jlblTag,tblJuntas,jlblTotal,jButton1);
               leerPapeleta(jtxtLote,jtxtTag,cmbProvincia,jtxtCuenta,jlblTag,tblJuntas,jlblTotal);
                if (myTags[index].getOpCode() == ACCESS_OPERATION_CODE.ACCESS_OPERATION_READ
                    && myTags[index].getOpStatus() == ACCESS_OPERATION_STATUS.ACCESS_SUCCESS)
                {
                    if (myTags[index].getMemoryBankData().length() > 0) {
                        System.out.println(" Mem Bank Data " +
                        myTags[index].getMemoryBankData());
                    }
                }
            }
        }
       /* try {
            myReader.Actions.Inventory.stop();
        } catch (InvalidUsageException ex) {
            Logger.getLogger(RFIDBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OperationFailureException ex) {
            Logger.getLogger(RFIDBase.class.getName()).log(Level.SEVERE, null, ex);
        }*/
   // }
        
   public void eventReadNotify(RfidReadEvents rre) {
           // System.out.println("Read Event Signalled");
            TableModel tableModel = inventoryUI.getjInventoryTable().getModel();
            TableModel tableModel1 = inventoryUI.getjInventoryTable1().getModel();
            JLabel totalTagsLabel = inventoryUI.getjLabelTotalTags();
            JTable tblJuntas=inventoryUI.getjInventoryTable1();
            //JLabel totalTagsLabel = inventoryUI.getjLabelTotalTags();
            JTextField jtxtTag=inventoryUI.getjTextTags();
            JTextField jtxtLote=inventoryUI.getjTextLote();
            JComboBox cmbProvincia=inventoryUI.getjComboProvincia();
            JTextField jtxtCuenta=inventoryUI.getjTextCuenta();
            JLabel jlblTag=inventoryUI.getjLabelTags();
            JLabel jlblTotal=inventoryUI.getjLabelTotal();
           // JButton jButton1=inventoryUI.getjBotonTags();

  
            // Read tags
            TagData[] myTags = myReader.Actions.getReadTags(100);
            if (myTags != null)
            {
                for (int index = 0; index < myTags.length; index++){

                    TagData tag = myTags[index];
                    String key = tag.getTagID();
                    String memBank = new String ();
                    
                    if (tag.getOpCode() != ACCESS_OPERATION_CODE.ACCESS_OPERATION_NONE)
                    {
                        key += tag.getMemoryBank().toString();
                        if (tag.getOpStatus() != ACCESS_OPERATION_STATUS.ACCESS_SUCCESS)
                            continue;
                    }
                   
                    if (tagStore.containsKey(key))
                    {
                        Integer rowIndex = (Integer)tagStore.get(key);
                        Object seenCount = ((DefaultTableModel)tableModel).getValueAt(rowIndex.intValue(), 3);

                        // Update Seen Count
                        Long seenCountTemp = Long.valueOf(seenCount.toString());
                     
                        long seenCountTmp = (long)tag.getTagSeenCount() + seenCountTemp.longValue();
                        Long seenCountNew = Long.valueOf(seenCountTmp);

                       // Update Tag State
                       ((DefaultTableModel)tableModel).setValueAt(tagState[tag.getTagEvent().getValue() - 1], rowIndex.intValue(), 1);

                       // Update Antenna ID
                       ((DefaultTableModel)tableModel).setValueAt(Short.valueOf(tag.getAntennaID()), rowIndex.intValue(), 2);

                       // Update Seen Count
                       ((DefaultTableModel)tableModel).setValueAt(seenCountNew, rowIndex.intValue(), 3);

                       // Update Peak RSSI
                       ((DefaultTableModel)tableModel).setValueAt(Short.valueOf(tag.getPeakRSSI()), rowIndex.intValue(), 4);

                       // Update Phase
                       double dPhase = 0;
                       if(tagStorageSettingsPhasInfoEnabled) 
                       {
                            dPhase = (180.0/32767)*tag.getPhase();
                       }
                       DecimalFormat fPhase =  new DecimalFormat("##.##");
                       ((DefaultTableModel)tableModel).setValueAt(Double.valueOf(fPhase.format(dPhase)), rowIndex.intValue(), 5);
                       
                       // Update PC bits
                       ((DefaultTableModel)tableModel).setValueAt(Integer.toHexString(tag.getPC()), rowIndex.intValue(), 6);

                        if (tag.getOpCode() != ACCESS_OPERATION_CODE.ACCESS_OPERATION_NONE)
                        {
                           // Update Memory Bank Data
                           ((DefaultTableModel)tableModel).setValueAt(tag.getMemoryBankData(), rowIndex.intValue(), 7);

                           // Update Memory Bank Type
                           ((DefaultTableModel)tableModel).setValueAt(memoryBank[tag.getMemoryBank().getValue()], rowIndex.intValue(), 8);

                           // Update offset
                           ((DefaultTableModel)tableModel).setValueAt(Integer.valueOf(tag.getMemoryBankDataOffset()), rowIndex.intValue(), 9);
                        }
                    }
                    else
                    {
                        if (tag.getOpCode() != ACCESS_OPERATION_CODE.ACCESS_OPERATION_NONE)
                        {
                            memBank = memoryBank[tag.getMemoryBank().getValue()];
                        }
                        double dPhase = 0;
                        if(tagStorageSettingsPhasInfoEnabled) 
                        {
                            dPhase = (180.0/32767)*tag.getPhase();
                        }
                        DecimalFormat fPhase =  new DecimalFormat("##.##");
                       ((DefaultTableModel)tableModel).insertRow(rowId, new Object[] {tag.getTagID(), tagState[tag.getTagEvent().getValue() - 1], Short.valueOf(tag.getAntennaID()), Short.valueOf(tag.getTagSeenCount()), Short.valueOf(tag.getPeakRSSI()), Double.valueOf(fPhase.format(dPhase)),Integer.toHexString(tag.getPC()), tag.getMemoryBankData(), memBank, Integer.valueOf(tag.getMemoryBankDataOffset())});
                         String tagE=new String("H"+myTags[0].getTagID().substring(0, myTags[0].getTagID().length()-1));
                        System.out.println("Tag ID " + myTags[0].getTagID());
                        jtxtTag.setText(tagE); 
                        leer(jtxtLote,jtxtTag,cmbProvincia,jtxtCuenta,jlblTag,tblJuntas,jlblTotal);
                        tagStore.put(key, Integer.valueOf(rowId));
                        rowId++;
                        uniqueTags++;
                    }
                    totalTags+=tag.getTagSeenCount();

                    totalTagsLabel.setText(String.valueOf(uniqueTags) + "(" + String.valueOf(totalTags) + ")");
                }
                
            }
        }
        
        
        

        public void eventStatusNotify(RfidStatusEvents rse) {

            STATUS_EVENT_TYPE statusType = rse.StatusEventData.getStatusEventType();

            if (statusType == STATUS_EVENT_TYPE.INVENTORY_START_EVENT)
            {
                mainApp.postNotification(statusType.toString(), "");
            }
            else if(statusType == STATUS_EVENT_TYPE.INVENTORY_STOP_EVENT)
            {
                mainApp.StartReadButtonClicked();
                mainApp.postNotification(statusType.toString(), "");
            }
            else if (statusType == STATUS_EVENT_TYPE.ACCESS_START_EVENT)
            {
                mainApp.postNotification(statusType.toString(), "");
            }
            else if (statusType == STATUS_EVENT_TYPE.ACCESS_STOP_EVENT)
            {
                mainApp.postNotification(statusType.toString(), "");
            }
            else if (statusType == STATUS_EVENT_TYPE.ANTENNA_EVENT)
            {
                int antennaID = rse.StatusEventData.AntennaEventData.getAntennaID();

                String antennaEventValue = "";
                if (rse.StatusEventData.AntennaEventData.getAntennaEvent() == ANTENNA_EVENT_TYPE.ANTENNA_CONNECTED)
                    antennaEventValue = String.valueOf(antennaID)+ " Connected";
                else if(rse.StatusEventData.AntennaEventData.getAntennaEvent() == ANTENNA_EVENT_TYPE.ANTENNA_DISCONNECTED)
                    antennaEventValue = String.valueOf(antennaID)+" Disconnected";

                mainApp.postNotification(statusType.toString(), antennaEventValue);

            }
            else if (statusType == STATUS_EVENT_TYPE.READER_EXCEPTION_EVENT)
            {
                mainApp.postNotification(statusType.toString(), rse.StatusEventData.ReaderExceptionEventData.getReaderExceptionEventInfo());
            }
            else if (statusType == STATUS_EVENT_TYPE.BUFFER_FULL_WARNING_EVENT)
            {
                mainApp.postNotification(statusType.toString(), "");
            }
            else if (statusType == STATUS_EVENT_TYPE.BUFFER_FULL_EVENT)
            {
                mainApp.postNotification(statusType.toString(), "");
            }
            else if (statusType == STATUS_EVENT_TYPE.DISCONNECTION_EVENT)
            {
                String disconnectionEventData = "";

                if (rse.StatusEventData.DisconnectionEventData.getDisconnectionEvent() == DISCONNECTION_EVENT_TYPE.READER_INITIATED_DISCONNECTION)
                    disconnectionEventData = "Reader Initiated Disconnection";
                else if ((rse.StatusEventData.DisconnectionEventData.getDisconnectionEvent() == DISCONNECTION_EVENT_TYPE.CONNECTION_LOST))
                    disconnectionEventData = "Connection Lost";
                else if ((rse.StatusEventData.DisconnectionEventData.getDisconnectionEvent() == DISCONNECTION_EVENT_TYPE.READER_EXCEPTION))
                    disconnectionEventData = "Reader Exception";

                mainApp.postNotification(statusType.toString(), disconnectionEventData);
//                BackgroundDisconnectThread disconnetThread = new BackgroundDisconnectThread(mainApp.rfidBase);
                //disconnetThread.start();
            }
            else if (statusType == STATUS_EVENT_TYPE.GPI_EVENT)
            {
                mainApp.postNotification(statusType.toString(), "");
                mainApp.setGPIOnScreen(rse.StatusEventData.GPIEventData.getGPIPort(), true, rse.StatusEventData.GPIEventData.getGPIEventState() ? true : false);
            }
            else if (statusType == STATUS_EVENT_TYPE.TEMPERATURE_ALARM_EVENT)
            {
                mainApp.postNotification(statusType.toString(), "Temperature " + rse.StatusEventData.TemperatureAlarmData.getTemperatureSource() +", " 
                        +rse.StatusEventData.TemperatureAlarmData.getCurrentTemperature() + ", Level" + 
                        rse.StatusEventData.TemperatureAlarmData.getAlarmLevel().toString());
            }

        }
    }
        
        
}
class BackgroundDisconnectThread extends Thread
{
    RFIDBase rfidBase;
    public BackgroundDisconnectThread(RFIDBase _rfidBase) {
        rfidBase = _rfidBase;
    }


    public void run()
    {
        // invoke Disconnect reader
        rfidBase.disconnectReader();
    }

}
