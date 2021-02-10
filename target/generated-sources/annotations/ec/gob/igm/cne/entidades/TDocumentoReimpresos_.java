package ec.gob.igm.cne.entidades;

import ec.gob.igm.cne.entidades.TListadoDocumentos;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2021-01-31T22:12:07")
@StaticMetamodel(TDocumentoReimpresos.class)
public class TDocumentoReimpresos_ { 

    public static volatile SingularAttribute<TDocumentoReimpresos, BigDecimal> idSeq;
    public static volatile SingularAttribute<TDocumentoReimpresos, Short> codParroquia;
    public static volatile SingularAttribute<TDocumentoReimpresos, Short> codProvincia;
    public static volatile SingularAttribute<TDocumentoReimpresos, String> codCircunscripcion;
    public static volatile SingularAttribute<TDocumentoReimpresos, BigInteger> codOrden;
    public static volatile SingularAttribute<TDocumentoReimpresos, TListadoDocumentos> idListado;
    public static volatile SingularAttribute<TDocumentoReimpresos, Short> codCanton;
    public static volatile SingularAttribute<TDocumentoReimpresos, String> sexo;
    public static volatile SingularAttribute<TDocumentoReimpresos, Short> junta;
    public static volatile SingularAttribute<TDocumentoReimpresos, Short> codZona;

}