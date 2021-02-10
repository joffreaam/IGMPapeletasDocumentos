/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.vista;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Interfaz del controlador de la vista del menu principal
 * 
 * @author Mayra Vera
 * @version 1.0
 */
public interface IMenu
{
    /**
     * Carga datos del usuario que ingresó al sistema (Nombre, DNI, permisos)
     * Además a aquellos usuarios no administradores se deshabilita ciertas
     * opciones.
     * 
     * @param txtNombre     Nombre del usuario
     * @param lblPermisos   Permisos del usuario (Usuario o Administrador)
     * @param btnActasP     Botón de generación de actas de papeletas
     * @param btnActasD     Botón de generación de actas de documentos
     */
    public void cargar(JTextField txtNombre,JButton btnEtiquetasP,JButton btnEtiquetasD,JButton btnREtiquetasP,JButton btnREtiquetasD,JButton btnLotesP,JButton btnLotesD,JButton btnActasP,JButton btnActasD);
    
    /**
     * Cierra sesión del usuario actual, y retorna a la ventana de Login.
     */
    public void cerrarSesion();
    
    /**
     * Acceso a la ventana de generación de etiquetas de papeletas.
     */
    public void generaEtiquetasPapeletas();
    
    /**
     * Acceso a la ventana de generación de etiquetas de documentos.
     */
    public void generaEtiquetasDocumentos();
    
     /**
     * Acceso a la ventana de generación de lotes de papeletas.
     */
    public void lotesPapeletas();
    
     /**
     * Acceso a la ventana de generación de lotes de documentos.
     */
    public void lotesDocumentos();
    
    /**
     * Acceso a la ventana de reimpresión de etiquetas de papeletas.
     */
    public void reimprimeEtiquetasPapeletas();
    
     /**
     * Acceso a la ventana de reimpresión de etiquetas de documentos
     */
    public void reimprimeEtiquetasDocumentos();
    
     /**
     * Acceso a la ventana de actas de papeletas.
     */
    public void actasPapeletas();
    
    /**
     * Acceso a la ventana de actas de documentos.
     */
    public void actasDocumentos();
    
    
    /**
     * Acceso a la ventana de Gestión de Usuario.
     */
    public void usuario();
    
}
