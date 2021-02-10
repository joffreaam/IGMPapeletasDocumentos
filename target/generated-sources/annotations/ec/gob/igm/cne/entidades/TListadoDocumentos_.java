package ec.gob.igm.cne.entidades;

import ec.gob.igm.cne.entidades.TDocumentoReimpresos;
import ec.gob.igm.cne.entidades.TOrdenDocumento;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2021-01-31T22:12:07")
@StaticMetamodel(TListadoDocumentos.class)
public class TListadoDocumentos_ { 

    public static volatile SingularAttribute<TListadoDocumentos, String> descripcion;
    public static volatile SingularAttribute<TListadoDocumentos, String> codigoTipodoc;
    public static volatile SingularAttribute<TListadoDocumentos, Character> estado;
    public static volatile ListAttribute<TListadoDocumentos, TOrdenDocumento> tOrdenDocumentoList;
    public static volatile SingularAttribute<TListadoDocumentos, BigDecimal> idListado;
    public static volatile CollectionAttribute<TListadoDocumentos, TDocumentoReimpresos> tDocumentoReimpresosCollection;
    public static volatile SingularAttribute<TListadoDocumentos, Character> leebarras;

}