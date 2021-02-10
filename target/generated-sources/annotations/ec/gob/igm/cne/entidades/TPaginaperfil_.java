package ec.gob.igm.cne.entidades;

import ec.gob.igm.cne.entidades.TPagina;
import ec.gob.igm.cne.entidades.TPaginaperfilPK;
import ec.gob.igm.cne.entidades.TPerfil;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2021-01-31T22:12:07")
@StaticMetamodel(TPaginaperfil.class)
public class TPaginaperfil_ { 

    public static volatile SingularAttribute<TPaginaperfil, TPaginaperfilPK> tPaginaperfilPK;
    public static volatile SingularAttribute<TPaginaperfil, TPagina> tPagina;
    public static volatile SingularAttribute<TPaginaperfil, TPerfil> tPerfil;
    public static volatile SingularAttribute<TPaginaperfil, BigInteger> version;

}