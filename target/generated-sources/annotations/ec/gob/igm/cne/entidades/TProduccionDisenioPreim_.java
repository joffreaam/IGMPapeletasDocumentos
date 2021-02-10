package ec.gob.igm.cne.entidades;

import ec.gob.igm.cne.entidades.TDocumentosPreimpresos;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2021-01-31T22:12:07")
@StaticMetamodel(TProduccionDisenioPreim.class)
public class TProduccionDisenioPreim_ { 

    public static volatile SingularAttribute<TProduccionDisenioPreim, Short> idPrensa;
    public static volatile SingularAttribute<TProduccionDisenioPreim, Date> fecha;
    public static volatile SingularAttribute<TProduccionDisenioPreim, String> estado;
    public static volatile SingularAttribute<TProduccionDisenioPreim, TDocumentosPreimpresos> idTipoDocumento;
    public static volatile SingularAttribute<TProduccionDisenioPreim, String> usuario;
    public static volatile SingularAttribute<TProduccionDisenioPreim, Integer> id;
    public static volatile SingularAttribute<TProduccionDisenioPreim, Long> turno;
    public static volatile SingularAttribute<TProduccionDisenioPreim, BigInteger> cantidadEjecutada;
    public static volatile SingularAttribute<TProduccionDisenioPreim, BigInteger> cantidadPlanificada;

}