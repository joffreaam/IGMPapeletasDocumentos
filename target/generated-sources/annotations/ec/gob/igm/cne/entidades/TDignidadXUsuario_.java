package ec.gob.igm.cne.entidades;

import ec.gob.igm.cne.entidades.TDignidad;
import ec.gob.igm.cne.entidades.TUsuario;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2021-01-31T22:12:07")
@StaticMetamodel(TDignidadXUsuario.class)
public class TDignidadXUsuario_ { 

    public static volatile SingularAttribute<TDignidadXUsuario, TUsuario> idUsuario;
    public static volatile SingularAttribute<TDignidadXUsuario, BigDecimal> id;
    public static volatile SingularAttribute<TDignidadXUsuario, String> dignidad;
    public static volatile SingularAttribute<TDignidadXUsuario, TDignidad> idDignidad;

}