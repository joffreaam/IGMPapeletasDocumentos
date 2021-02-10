package ec.gob.igm.cne.entidades;

import ec.gob.igm.cne.entidades.TDignidad;
import ec.gob.igm.cne.entidades.TPerfil;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2021-01-31T22:12:07")
@StaticMetamodel(TUsuarios.class)
public class TUsuarios_ { 

    public static volatile SingularAttribute<TUsuarios, String> password;
    public static volatile SingularAttribute<TUsuarios, TPerfil> idPerfil;
    public static volatile SingularAttribute<TUsuarios, BigDecimal> idUsuario;
    public static volatile SingularAttribute<TUsuarios, String> usuario;
    public static volatile SingularAttribute<TUsuarios, String> nombreCompleto;
    public static volatile SingularAttribute<TUsuarios, String> dignidad;
    public static volatile SingularAttribute<TUsuarios, BigInteger> bodega;
    public static volatile SingularAttribute<TUsuarios, TDignidad> idDignidad;
    public static volatile SingularAttribute<TUsuarios, String> perfil;

}