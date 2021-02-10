package ec.gob.igm.cne.entidades;

import ec.gob.igm.cne.entidades.TCanton;
import ec.gob.igm.cne.entidades.TProduccionDocumentos;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2021-01-31T22:12:07")
@StaticMetamodel(TProvincia.class)
public class TProvincia_ { 

    public static volatile SingularAttribute<TProvincia, BigInteger> prelacion;
    public static volatile SingularAttribute<TProvincia, Integer> idProvincia;
    public static volatile ListAttribute<TProvincia, TCanton> tCantonList;
    public static volatile SingularAttribute<TProvincia, String> provincia;
    public static volatile ListAttribute<TProvincia, TProduccionDocumentos> tProduccionDocumentosList;

}