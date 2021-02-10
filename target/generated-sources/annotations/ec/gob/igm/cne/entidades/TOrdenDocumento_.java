package ec.gob.igm.cne.entidades;

import ec.gob.igm.cne.entidades.TListadoDocumentos;
import ec.gob.igm.cne.entidades.TOrdenDocumentoPK;
import ec.gob.igm.cne.entidades.TTipoDocumento;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2021-01-31T22:12:07")
@StaticMetamodel(TOrdenDocumento.class)
public class TOrdenDocumento_ { 

    public static volatile SingularAttribute<TOrdenDocumento, BigInteger> tipo;
    public static volatile SingularAttribute<TOrdenDocumento, TListadoDocumentos> tListadoDocumentos;
    public static volatile SingularAttribute<TOrdenDocumento, BigInteger> orden;
    public static volatile SingularAttribute<TOrdenDocumento, TTipoDocumento> tTipoDocumento;
    public static volatile SingularAttribute<TOrdenDocumento, TOrdenDocumentoPK> tOrdenDocumentoPK;

}