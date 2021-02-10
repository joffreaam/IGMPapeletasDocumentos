package ec.gob.igm.cne.entidades;

import ec.gob.igm.cne.entidades.TProduccionDocumentosPK;
import ec.gob.igm.cne.entidades.TProvincia;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2021-01-31T22:12:07")
@StaticMetamodel(TProduccionDocumentos.class)
public class TProduccionDocumentos_ { 

    public static volatile SingularAttribute<TProduccionDocumentos, Long> documentos;
    public static volatile SingularAttribute<TProduccionDocumentos, TProvincia> idProvincia;
    public static volatile SingularAttribute<TProduccionDocumentos, String> proceso;
    public static volatile SingularAttribute<TProduccionDocumentos, TProduccionDocumentosPK> tProduccionDocumentosPK;
    public static volatile SingularAttribute<TProduccionDocumentos, Short> idCircunscripcion;
    public static volatile SingularAttribute<TProduccionDocumentos, String> usuario;
    public static volatile SingularAttribute<TProduccionDocumentos, Short> turno;
    public static volatile SingularAttribute<TProduccionDocumentos, BigInteger> idKit;
    public static volatile SingularAttribute<TProduccionDocumentos, String> supervisor;

}