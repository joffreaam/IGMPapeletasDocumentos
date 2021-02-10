/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.vista;

import ec.gob.igm.cne.entidades.TUsuario;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * Controlador del Menu Principal
 * 
 * Carga las opciones correspondientes dependiendo del tipo de usuario y redirige
 * hacia las diferentes vistas correspondientes.
 *  
 * @author Mayra Vera
 * @version 1.0
 */


public class CMenu implements IMenu
{
     private UIMenu ventana;
     private TUsuario usuario;
     
     public CMenu(TUsuario usuario)
    {
        this.usuario = usuario;
        ventana = new UIMenu(this);        
    }

    @Override
    public void cargar(JTextField txtNombre, JButton btnEtiquetasP, JButton btnEtiquetasD, JButton btnREtiquetasP, JButton btnREtiquetasD, JButton btnLotesP, JButton btnREtiquetasP1, JButton btnActasP, JButton btnActasD) {
        //To change body of generated methods, choose Tools | Templates.
        txtNombre.setText(usuario.getNombreCompleto());
//        String permiso = "";

        if(usuario.getPerfil().equals("OPERADOR1"))
        {
            btnEtiquetasD.setEnabled(false);
            btnREtiquetasD.setEnabled(false);
            btnActasD.setEnabled(false);
        }
    }
        
    @Override
    public void cerrarSesion() {
//        new CLogin();
        ventana.dispose();
    }

    @Override
    public void generaEtiquetasPapeletas() {
//        new CGeneraEtiquetaP();
    }

    @Override
    public void generaEtiquetasDocumentos() {
        FrmEtiquetaJuntaDocumentos etiquetaJuntaDocumentos = new FrmEtiquetaJuntaDocumentos(ventana, true, "Voto General", usuario);
        etiquetaJuntaDocumentos.setTitle("Generación de etiquetas para Documentos Electorales");
        etiquetaJuntaDocumentos.setLocationRelativeTo(ventana);
        etiquetaJuntaDocumentos.setVisible(true);
    }

    @Override
    public void lotesPapeletas() {
//        new CLoteP();
    }

    @Override
    public void lotesDocumentos() {
        FrmLoteDocumentos frmLoteDocumentos = new FrmLoteDocumentos(ventana, true);
        frmLoteDocumentos.setTitle("Lote de Documentos");
        frmLoteDocumentos.setLocationRelativeTo(ventana);
        frmLoteDocumentos.setVisible(true);
    }

    @Override
    public void reimprimeEtiquetasPapeletas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reimprimeEtiquetasDocumentos() {
        FrmReimpresionDocumentos reimpresionDocumentos = new FrmReimpresionDocumentos(ventana, true, usuario);
        reimpresionDocumentos.setTitle("Reimpresion de etiquetas para Documentos");
        reimpresionDocumentos.setLocationRelativeTo(ventana);
        reimpresionDocumentos.setVisible(true);
               
        
    }

    @Override
    public void actasPapeletas() {
        
        FrmActasPapeletas actasPaoeletas = new FrmActasPapeletas(ventana, true);
        actasPaoeletas.setLocationRelativeTo(ventana);
        actasPaoeletas.setVisible(true);
        
    }

    @Override
    public void actasDocumentos() {
        FrmActasDocumentos actasDocumentos = new FrmActasDocumentos(ventana, true);
        actasDocumentos.setLocationRelativeTo(ventana);
        actasDocumentos.setVisible(true);
    }

    @Override
    public void usuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
    }
