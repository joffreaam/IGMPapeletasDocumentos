package ec.gob.igm.cne.entidades;

import ec.gob.igm.cne.entidades.TPaginaperfil;
import ec.gob.igm.cne.entidades.TUsuarios;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2021-01-31T22:12:07")
@StaticMetamodel(TPerfil.class)
public class TPerfil_ { 

    public static volatile SingularAttribute<TPerfil, String> descripcion;
    public static volatile ListAttribute<TPerfil, TPaginaperfil> tPaginaperfilList;
    public static volatile SingularAttribute<TPerfil, BigDecimal> idperfil;
    public static volatile ListAttribute<TPerfil, TUsuarios> tUsuariosList;

}