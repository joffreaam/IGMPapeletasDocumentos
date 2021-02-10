/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.dao;

import ec.gob.igm.cne.controladores.TBodegaJpaController;
import ec.gob.igm.cne.entidades.TBodega;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author desarrollo
 */
public class TBodegaDAO {
    private TBodegaJpaController tbjc;
    private TBodega bodega= new TBodega();
    
    public TBodegaDAO(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CNEELECCIONES");        
        tbjc = new TBodegaJpaController(emf);
    }
    
    public List<TBodega> listarTodas(){
        return tbjc.findTBodegaEntities();
    }
}
