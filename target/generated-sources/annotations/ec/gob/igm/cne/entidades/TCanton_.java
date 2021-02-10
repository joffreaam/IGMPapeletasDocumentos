package ec.gob.igm.cne.entidades;

import ec.gob.igm.cne.entidades.TParroquia;
import ec.gob.igm.cne.entidades.TProduccionDisenio;
import ec.gob.igm.cne.entidades.TProduccionPapeletas;
import ec.gob.igm.cne.entidades.TProvincia;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2021-01-31T22:12:07")
@StaticMetamodel(TCanton.class)
public class TCanton_ { 

    public static volatile SingularAttribute<TCanton, TProvincia> idProvincia;
    public static volatile SingularAttribute<TCanton, Integer> idCanton;
    public static volatile SingularAttribute<TCanton, String> canton;
    public static volatile ListAttribute<TCanton, TParroquia> tParroquiaList;
    public static volatile ListAttribute<TCanton, TProduccionPapeletas> tProduccionPapeletasList;
    public static volatile ListAttribute<TCanton, TProduccionDisenio> tProduccionDisenioList;

}