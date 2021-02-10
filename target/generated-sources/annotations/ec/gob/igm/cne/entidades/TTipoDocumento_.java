package ec.gob.igm.cne.entidades;

import ec.gob.igm.cne.entidades.TOrdenDocumento;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2021-01-31T22:12:07")
@StaticMetamodel(TTipoDocumento.class)
public class TTipoDocumento_ { 

    public static volatile SingularAttribute<TTipoDocumento, String> descripcion;
    public static volatile ListAttribute<TTipoDocumento, TOrdenDocumento> tOrdenDocumentoList;
    public static volatile SingularAttribute<TTipoDocumento, BigDecimal> idKit;

}