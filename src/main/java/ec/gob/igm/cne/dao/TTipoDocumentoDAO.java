/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.dao;

import ec.gob.igm.cne.controladores.TTipoDocumentoJpaController;
import ec.gob.igm.cne.entidades.TTipoDocumento;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author desarrollo
 */
public class TTipoDocumentoDAO {
    private TTipoDocumentoJpaController ttdjc;
    
    public TTipoDocumentoDAO(){
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("CNEELECCIONES");
       // EntityManagerFactory emf = Persistence.createEntityManagerFactory("ELECCIONES");
        ttdjc = new TTipoDocumentoJpaController(emf);
    }
    
    public List<TTipoDocumento> listarTodos(){
        return ttdjc.findTTipoDocumentoEntities();
    }
}
