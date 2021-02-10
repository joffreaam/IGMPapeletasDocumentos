/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.dao;

import ec.gob.igm.cne.controladores.TDignidadJpaController;
import ec.gob.igm.cne.entidades.TDignidad;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author desarrollo
 */
public class TDignidadDAO {
    private TDignidadJpaController tdjc;
    private TDignidad dignidad = new TDignidad();
    
    public TDignidadDAO(){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("CNEELECCIONES");
        
        tdjc = new TDignidadJpaController(emf);
    }
    
    public List<TDignidad> listarTodas(){
        return tdjc.findTDignidadEntities();
    }
}
