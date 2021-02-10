package ec.gob.igm.cne.entidades;

import ec.gob.igm.cne.entidades.TCanton;
import ec.gob.igm.cne.entidades.TDignidad;
import ec.gob.igm.cne.entidades.TParroquia;
import ec.gob.igm.cne.entidades.TPrensas;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2021-01-31T22:12:07")
@StaticMetamodel(TProduccionDisenio.class)
public class TProduccionDisenio_ { 

    public static volatile SingularAttribute<TProduccionDisenio, Integer> idProvincia;
    public static volatile SingularAttribute<TProduccionDisenio, String> estado;
    public static volatile SingularAttribute<TProduccionDisenio, TDignidad> codDignidad;
    public static volatile SingularAttribute<TProduccionDisenio, Long> turno;
    public static volatile SingularAttribute<TProduccionDisenio, BigInteger> cantidadEjecutada;
    public static volatile SingularAttribute<TProduccionDisenio, TParroquia> idParroquia;
    public static volatile SingularAttribute<TProduccionDisenio, TPrensas> idPrensa;
    public static volatile SingularAttribute<TProduccionDisenio, Date> fechaReg;
    public static volatile SingularAttribute<TProduccionDisenio, TCanton> idCanton;
    public static volatile SingularAttribute<TProduccionDisenio, String> usuario;
    public static volatile SingularAttribute<TProduccionDisenio, String> operador;
    public static volatile SingularAttribute<TProduccionDisenio, BigInteger> cantidadPlanificada;
    public static volatile SingularAttribute<TProduccionDisenio, Long> idRegistro;

}