package ec.gob.igm.cne.entidades;

import ec.gob.igm.cne.entidades.TProduccionDisenioPreim;
import ec.gob.igm.cne.entidades.TProduccionPreimpresos;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2021-01-31T22:12:07")
@StaticMetamodel(TDocumentosPreimpresos.class)
public class TDocumentosPreimpresos_ { 

    public static volatile SingularAttribute<TDocumentosPreimpresos, Long> total;
    public static volatile SingularAttribute<TDocumentosPreimpresos, String> documento;
    public static volatile ListAttribute<TDocumentosPreimpresos, TProduccionPreimpresos> tProduccionPreimpresosList;
    public static volatile SingularAttribute<TDocumentosPreimpresos, Integer> id;
    public static volatile ListAttribute<TDocumentosPreimpresos, TProduccionDisenioPreim> tProduccionDisenioPreimList;

}