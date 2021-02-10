/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.dao;

import ec.gob.igm.cne.controladores.TUsuarioJpaController;
import ec.gob.igm.cne.entidades.TUsuario;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author desarrollo
 */
public class TUsuarioDAO {

    private TUsuarioJpaController ujc;
    private TUsuario usuario = new TUsuario();
    private String mensaje;
    
    public TUsuarioDAO(){
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("CNEELECCIONES");   
        ujc = new TUsuarioJpaController(emf);
    }

    public String insertarUsuario(String user, String password, String nombreCompleto,
            String perfil, String dignidad, BigInteger bodega, Short idDignidad) {
        try {
//            usuario.setIdUsuario(null);
            usuario.setIdUsuario(BigDecimal.ZERO);
            usuario.setUsuario(user);
            usuario.setPassword(password);
            usuario.setPerfil(perfil);
            usuario.setDignidad(dignidad);
            usuario.setBodega(bodega);
            usuario.setNombreCompleto(nombreCompleto);
            usuario.setIdDignidad(idDignidad);
            ujc.create(usuario);
            mensaje = "Usuario creado correctamente";
        } catch (Exception e) {
            mensaje = "No se pudo crear el usuario: " + e.getMessage();
        }
        return mensaje;
    }

    public String actualizarUsuario(int idUsuario, String user, String password, String nombreCompleto,
            String perfil, String dignidad, BigInteger bodega, Short idDignidad) {
        try {
            usuario.setIdUsuario(BigDecimal.valueOf(idUsuario));
            usuario.setUsuario(user);
            usuario.setPassword(password);
            usuario.setPerfil(perfil);
            usuario.setDignidad(dignidad);
            usuario.setBodega(bodega);
            usuario.setNombreCompleto(nombreCompleto);
            usuario.setIdDignidad(idDignidad);
            ujc.edit(usuario);
            mensaje = "Usuario actualizado correctamente";
        } catch (Exception e) {
            if (e.getMessage() == null || e.getMessage().length() == 0) {
                mensaje = "No se pudo actualizar el usuario";
            } else {
                mensaje = "No se pudo actualizar el usuario: " + e.getMessage();
            }
        }
        return mensaje;
    }

    public String eliminarUsuario(int idUsuario) {
        try {
            ujc.destroy(BigDecimal.valueOf(idUsuario));
            mensaje = "Usuario eliminado correctamente";
        } catch (Exception e) {
            mensaje = "No se pudo eliminar el usuario: " + e.getMessage();
        }
        return mensaje;
    }
    
    public TUsuario buscarPorUsuarioYPassword(String usuario, String password){
        TUsuario resultado;
        EntityManager em = ujc.getEntityManager();
        TypedQuery<TUsuario> consulta = em.createNamedQuery("TUsuario.findByUsuario&Passwod", TUsuario.class);
        consulta.setParameter("usuario", usuario);
        consulta.setParameter("password", password);
        List<TUsuario> lista= consulta.getResultList();
        //String query = "select ";
        //em.createNativeQuery(usuario, resultClass);
        if(lista.isEmpty()){
            resultado = null;            
        }else{
            resultado = lista.get(0);
        }
        em.close();
        return resultado;
    }

    public void listarUsuarios(JTable tabla) {
        DefaultTableModel model;
        String[] cabecera = {"Id", "Usuario", "Nombre", "Contraseña", "Perfil", "Dignidad", "Bodega"};
        model = new DefaultTableModel(null, cabecera) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        List<TUsuario> datos = ujc.findTUsuarioEntities();
        String[] fila = new String[7];
        for (TUsuario usuario : datos) {
            fila[0] = usuario.getIdUsuario() + "";
            fila[1] = usuario.getUsuario() + "";
            fila[2] = usuario.getNombreCompleto() + "";
            fila[3] = usuario.getPassword() + "";
            fila[4] = usuario.getPerfil() + "";
            if (usuario.getDignidad() == null) {
                fila[5] = "";
            } else {
                fila[5] = usuario.getDignidad() + "";
            }
            fila[6] = usuario.getBodega() + "";
            model.addRow(fila);
        }
        tabla.setModel(model);
    }
}
