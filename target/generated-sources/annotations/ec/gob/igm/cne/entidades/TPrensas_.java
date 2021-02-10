package ec.gob.igm.cne.entidades;

import ec.gob.igm.cne.entidades.TProduccionDisenio;
import ec.gob.igm.cne.entidades.TProduccionPapeletas;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2021-01-31T22:12:07")
@StaticMetamodel(TPrensas.class)
public class TPrensas_ { 

    public static volatile SingularAttribute<TPrensas, Short> idPrensa;
    public static volatile SingularAttribute<TPrensas, String> descripcion;
    public static volatile ListAttribute<TPrensas, TProduccionPapeletas> tProduccionPapeletasList;
    public static volatile SingularAttribute<TPrensas, Short> bobinas;
    public static volatile ListAttribute<TPrensas, TProduccionDisenio> tProduccionDisenioList;
    public static volatile SingularAttribute<TPrensas, Short> pliegos;

}