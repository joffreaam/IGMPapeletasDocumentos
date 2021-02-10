/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.dao;

import ec.gob.igm.cne.controladores.TJuntaJpaController;
import ec.gob.igm.cne.documentos.DatosJunta;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ec.gob.igm.cne.entidades.TJuntaPK;

/**
 *
 * @author desarrollo
 */
public class TJuntaDAO {

    private TJuntaJpaController tjjp;
    
    public TJuntaDAO(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CNEELECCIONES");
     
        tjjp = new TJuntaJpaController(emf);
    }

    public DatosJunta obtenerDatosDeJunta(int idProvincia, int idCanton, int idParroquia, int idZona, int idJunta, String codCricunscripcion, String sexo) {
        String query = "select PROVINCIA, CANTON, NOM_CIRCUNSCRIPCION, PARROQUIA, ZONA, ID_JUNTA, VSEXO, TIPO_PARROQUIA, BARRAS, TAG, COD_ACTA_JUNTA, COD1, COD7, COD8, COD9, ESTADO "
                + "from T_BASE "
                + "where ID_JUNTA=?1 "
                + "and ID_PROVINCIA=?2 "
                + "and ID_CANTON=?3 "
                + "and COD_CIRCUNSCRIPCION=?4 "
                + "and ID_PARROQUIA=?5 "
                + "and ID_ZONA=?6 "
                + "and SEXO=?7 "
                + "and TIPO='DOCUMENTO'";
        Query nativeQuery = tjjp.getEntityManager().createNativeQuery(query);
        nativeQuery.setParameter(1, idJunta);
        nativeQuery.setParameter(2, idProvincia);
        nativeQuery.setParameter(3, idCanton);
        nativeQuery.setParameter(4, codCricunscripcion);
        nativeQuery.setParameter(5, idParroquia);
        nativeQuery.setParameter(6, idZona);
        nativeQuery.setParameter(7, sexo);
        Object[] result = (Object[]) nativeQuery.getSingleResult();
        Map<String, String> codigos = new HashMap<>();
        codigos.put("COD_ACTA_JUNTA",String.valueOf((BigDecimal) result[10]));
        System.out.println("COD_ACTA_JUNTA->"+String.valueOf((BigDecimal) result[10]));
        codigos.put("COD1",(String)result[11]);
        System.out.println("COD1->"+(String)result[11]);
        codigos.put("COD7",(String)result[12]);
        System.out.println("COD7"+ (String)result[12]);
        codigos.put("COD8",(String)result[13]);
        System.out.println("COD8"+ (String) result[13]);
        codigos.put("COD9",(String)result[14]);
        System.out.println("COD9"+ (String) result[14]);
        //add por Joffre Alava
        codigos.put("ESTADO",(String)result[15]);
        System.out.println("ESTADO"+ (String) result[15]);
        //hasta aqui --> en la siguiente linea se add un otro item
        return new DatosJunta((String)result[0], (String)result[1], (String)result[3], (String)result[4], (String)result[6], (String)result[2], (String)result[7], String.valueOf(result[5]), idZona, (String)result[8], (String)result[9], (String)result[15], codigos);
    }
    public DatosJunta obtenerDatosDeJuntaXActa(Integer codigoActa) {
        String query = "select PROVINCIA, CANTON, NOM_CIRCUNSCRIPCION, PARROQUIA, ZONA, ID_JUNTA, VSEXO, TIPO_PARROQUIA, BARRAS, TAG, COD_ACTA_JUNTA, COD1, COD7, COD8, COD9, ESTADO, ID_ZONA, ID_PROVINCIA, COD_CIRCUNSCRIPCION , ID_CANTON, ID_PARROQUIA, SEXO "
                + "from T_BASE "
                + " where COD_ACTA_JUNTA=?1 "
                + " and TIPO='DOCUMENTO'";
        Query nativeQuery = tjjp.getEntityManager().createNativeQuery(query);
        nativeQuery.setParameter(1, codigoActa);    
        Object[] result = (Object[]) nativeQuery.getSingleResult();
        Map<String, String> codigos = new HashMap<>();
        codigos.put("COD_ACTA_JUNTA",String.valueOf((BigDecimal) result[10]));
        System.out.println("COD_ACTA_JUNTA->"+String.valueOf((BigDecimal) result[10]));
        codigos.put("COD1",(String)result[11]);
        System.out.println("COD1->"+(String)result[11]);
        codigos.put("COD7",(String)result[12]);
        System.out.println("COD7"+ (String)result[12]);
        codigos.put("COD8",(String)result[13]);
        System.out.println("COD8"+ (String) result[13]);
        codigos.put("COD9",(String)result[14]);
        System.out.println("COD9"+ (String) result[14]);
        //add por Joffre Alava
        codigos.put("ESTADO",(String)result[15]);
        System.out.println("ESTADO"+ (String) result[15]);
        codigos.put("ID_ZONA",String.valueOf((BigDecimal) result[16]));
        
        
        TJuntaPK juntaPK = new TJuntaPK();        
        
        juntaPK.setCodProvincia(Short.parseShort(String.valueOf((BigDecimal) result[17]))); 

        juntaPK.setCodCanton(Short.parseShort(String.valueOf((BigDecimal) result[19]))); 

        juntaPK.setCodParroquia(Short.parseShort(String.valueOf((BigDecimal) result[20]))); 

        juntaPK.setCodZona(Short.parseShort(String.valueOf((BigDecimal) result[16]))); 

        juntaPK.setJunta(Short.parseShort(String.valueOf((BigDecimal) result[5]))); 

        juntaPK.setCodCircunscripcion(String.valueOf((BigDecimal) result[18]));

        juntaPK.setSexo((String)result[21]);
            
            
         //DatosJunta datosJunta= new DatosJunta(juntaPK);
        
        //hasta aqui --> en la siguiente linea se add un otro item
        return new DatosJunta(juntaPK, (String)result[0], (String)result[1], (String)result[3], (String)result[4], (String)result[6], (String)result[2], (String)result[7], String.valueOf(result[5]),Integer.parseInt(String.valueOf((BigDecimal) result[10])),(String)result[8], (String)result[9], (String)result[15], codigos);
    }
}
