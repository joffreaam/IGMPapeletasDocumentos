package ec.gob.igm.cne.entidades;

import ec.gob.igm.cne.entidades.TMenu;
import ec.gob.igm.cne.entidades.TPagina;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2021-01-31T22:12:07")
@StaticMetamodel(TMenu.class)
public class TMenu_ { 

    public static volatile SingularAttribute<TMenu, TPagina> idPagina;
    public static volatile SingularAttribute<TMenu, Long> idMenu;
    public static volatile SingularAttribute<TMenu, BigInteger> orden;
    public static volatile SingularAttribute<TMenu, String> nombre;
    public static volatile SingularAttribute<TMenu, String> version;
    public static volatile ListAttribute<TMenu, TMenu> tMenuList;
    public static volatile SingularAttribute<TMenu, TMenu> idMenuPadre;

}