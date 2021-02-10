package ec.gob.igm.cne.entidades;

import ec.gob.igm.cne.entidades.TCanton;
import ec.gob.igm.cne.entidades.TProduccionDisenio;
import ec.gob.igm.cne.entidades.TProduccionPapeletas;
import ec.gob.igm.cne.entidades.TZona;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2021-01-31T22:12:07")
@StaticMetamodel(TParroquia.class)
public class TParroquia_ { 

    public static volatile ListAttribute<TParroquia, TZona> tZonaList;
    public static volatile SingularAttribute<TParroquia, TCanton> idCanton;
    public static volatile SingularAttribute<TParroquia, String> parroquia;
    public static volatile SingularAttribute<TParroquia, Integer> idParroquia;
    public static volatile ListAttribute<TParroquia, TProduccionPapeletas> tProduccionPapeletasList;
    public static volatile ListAttribute<TParroquia, TProduccionDisenio> tProduccionDisenioList;

}