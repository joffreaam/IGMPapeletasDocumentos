package ec.gob.igm.cne.entidades;

import ec.gob.igm.cne.entidades.TMenu;
import ec.gob.igm.cne.entidades.TPaginaperfil;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2021-01-31T22:12:07")
@StaticMetamodel(TPagina.class)
public class TPagina_ { 

    public static volatile SingularAttribute<TPagina, Date> fechaModificacion;
    public static volatile SingularAttribute<TPagina, Long> idPagina;
    public static volatile ListAttribute<TPagina, TPaginaperfil> tPaginaperfilList;
    public static volatile SingularAttribute<TPagina, String> nombre;
    public static volatile SingularAttribute<TPagina, BigInteger> version;
    public static volatile ListAttribute<TPagina, TMenu> tMenuList;
    public static volatile SingularAttribute<TPagina, String> url;

}