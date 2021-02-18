/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.documentos;


import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author VERA_MAYRA
 */
public interface ILoteDocumentos {
    public void cargarProvincia(JComboBox cmbProvincia);
    public void cargarDatosLote(JComboBox cmbProvincia,JTextField jtxtLote,JTable tblJuntas,JLabel jlblTotal);
    public int leer(JTextField jtxtLote, JTextField jtxtTag,JComboBox cmbProvincia,JTextField jtxtCuenta,JLabel jlblTag,JTable tblJuntas,JLabel jlblTotal);
    public void cerrarLote(JTextField jtxtLote,JTextField jtxtTag,JLabel jlblTotal,JTable tblJuntas,JComboBox cmbProvincia,JLabel jlblTag);
    public void eliminar(JTable tblJuntas);
    //public String codigoBarras( JTextField jtxtTagDocumento,JTextField jtxtTag);
}
