/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.dao;

import ec.gob.igm.cne.controladores.TPerfilJpaController;
import ec.gob.igm.cne.entidades.TPerfil;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author desarrollo
 */
public class TPerfilDAO {
    private TPerfilJpaController tpjc;
    private TPerfil perfil = new TPerfil();
    
    public TPerfilDAO(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CNEELECCIONES");
      
        tpjc = new TPerfilJpaController(emf);
    }
    
    public List<TPerfil> listarTodos(){
        return tpjc.findTPerfilEntities();
    }
}
