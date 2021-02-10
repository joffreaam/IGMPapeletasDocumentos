package ec.gob.igm.cne.entidades;

import ec.gob.igm.cne.entidades.TDignidadXUsuario;
import ec.gob.igm.cne.entidades.TProduccionDisenio;
import ec.gob.igm.cne.entidades.TProduccionPapeletas;
import ec.gob.igm.cne.entidades.TUsuarios;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2021-01-31T22:12:07")
@StaticMetamodel(TDignidad.class)
public class TDignidad_ { 

    public static volatile SingularAttribute<TDignidad, Integer> cantidadPapel;
    public static volatile SingularAttribute<TDignidad, BigInteger> ordDignidad;
    public static volatile SingularAttribute<TDignidad, Integer> codDignidad;
    public static volatile SingularAttribute<TDignidad, String> formato;
    public static volatile SingularAttribute<TDignidad, String> alias;
    public static volatile ListAttribute<TDignidad, TUsuarios> tUsuariosList;
    public static volatile ListAttribute<TDignidad, TDignidadXUsuario> tDignidadXUsuarioList;
    public static volatile SingularAttribute<TDignidad, String> dignidad;
    public static volatile SingularAttribute<TDignidad, String> nivel;
    public static volatile ListAttribute<TDignidad, TProduccionPapeletas> tProduccionPapeletasList;
    public static volatile ListAttribute<TDignidad, TProduccionDisenio> tProduccionDisenioList;

}