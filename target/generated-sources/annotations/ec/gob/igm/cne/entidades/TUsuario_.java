package ec.gob.igm.cne.entidades;

import ec.gob.igm.cne.entidades.TDignidadXUsuario;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2021-01-31T22:12:07")
@StaticMetamodel(TUsuario.class)
public class TUsuario_ { 

    public static volatile SingularAttribute<TUsuario, String> password;
    public static volatile SingularAttribute<TUsuario, BigDecimal> idUsuario;
    public static volatile SingularAttribute<TUsuario, String> usuario;
    public static volatile ListAttribute<TUsuario, TDignidadXUsuario> tDignidadXUsuarioList;
    public static volatile SingularAttribute<TUsuario, String> nombreCompleto;
    public static volatile SingularAttribute<TUsuario, String> dignidad;
    public static volatile SingularAttribute<TUsuario, Short> idDignidad;
    public static volatile SingularAttribute<TUsuario, BigInteger> bodega;
    public static volatile SingularAttribute<TUsuario, String> perfil;

}