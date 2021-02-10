package ec.gob.igm.cne.entidades;

import ec.gob.igm.cne.entidades.TDocumentosPreimpresos;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2021-01-31T22:12:07")
@StaticMetamodel(TProduccionPreimpresos.class)
public class TProduccionPreimpresos_ { 

    public static volatile SingularAttribute<TProduccionPreimpresos, String> estado;
    public static volatile SingularAttribute<TProduccionPreimpresos, Long> cantidadConforme;
    public static volatile SingularAttribute<TProduccionPreimpresos, String> formato;
    public static volatile SingularAttribute<TProduccionPreimpresos, String> formatoHoja;
    public static volatile SingularAttribute<TProduccionPreimpresos, Long> materialMaculatura;
    public static volatile SingularAttribute<TProduccionPreimpresos, Long> turno;
    public static volatile SingularAttribute<TProduccionPreimpresos, BigInteger> materialNoconforme;
    public static volatile SingularAttribute<TProduccionPreimpresos, Long> cantidadPlacas;
    public static volatile SingularAttribute<TProduccionPreimpresos, Long> armado;
    public static volatile SingularAttribute<TProduccionPreimpresos, Short> idPrensa;
    public static volatile SingularAttribute<TProduccionPreimpresos, Date> fecha;
    public static volatile SingularAttribute<TProduccionPreimpresos, Long> nPalet;
    public static volatile SingularAttribute<TProduccionPreimpresos, TDocumentosPreimpresos> idTipoDocumento;
    public static volatile SingularAttribute<TProduccionPreimpresos, Long> pliegosBobinas;
    public static volatile SingularAttribute<TProduccionPreimpresos, String> observaciones;
    public static volatile SingularAttribute<TProduccionPreimpresos, String> usuario;
    public static volatile SingularAttribute<TProduccionPreimpresos, Integer> id;
    public static volatile SingularAttribute<TProduccionPreimpresos, String> responsableDiseno;
    public static volatile SingularAttribute<TProduccionPreimpresos, String> operador;
    public static volatile SingularAttribute<TProduccionPreimpresos, String> formatosPapel;

}