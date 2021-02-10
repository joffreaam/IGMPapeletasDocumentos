package ec.gob.igm.cne.entidades;

import ec.gob.igm.cne.entidades.TCanton;
import ec.gob.igm.cne.entidades.TDignidad;
import ec.gob.igm.cne.entidades.TParroquia;
import ec.gob.igm.cne.entidades.TPrensas;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2021-01-31T22:12:07")
@StaticMetamodel(TProduccionPapeletas.class)
public class TProduccionPapeletas_ { 

    public static volatile SingularAttribute<TProduccionPapeletas, Integer> idProvincia;
    public static volatile SingularAttribute<TProduccionPapeletas, BigDecimal> bobina;
    public static volatile SingularAttribute<TProduccionPapeletas, Long> cantidadReal;
    public static volatile SingularAttribute<TProduccionPapeletas, String> formato;
    public static volatile SingularAttribute<TProduccionPapeletas, TDignidad> codDignidad;
    public static volatile SingularAttribute<TProduccionPapeletas, String> formatoHoja;
    public static volatile SingularAttribute<TProduccionPapeletas, Short> idCircunscripcion;
    public static volatile SingularAttribute<TProduccionPapeletas, Long> turno;
    public static volatile SingularAttribute<TProduccionPapeletas, BigInteger> materialNoconforme;
    public static volatile SingularAttribute<TProduccionPapeletas, Long> cantidadPlacas;
    public static volatile SingularAttribute<TProduccionPapeletas, Long> pliegos;
    public static volatile SingularAttribute<TProduccionPapeletas, Long> papeletas;
    public static volatile SingularAttribute<TProduccionPapeletas, TCanton> idCanton;
    public static volatile SingularAttribute<TProduccionPapeletas, String> tipoPapel;
    public static volatile SingularAttribute<TProduccionPapeletas, Long> papeletasMaculatura;
    public static volatile SingularAttribute<TProduccionPapeletas, TParroquia> idParroquia;
    public static volatile SingularAttribute<TProduccionPapeletas, Long> armado;
    public static volatile SingularAttribute<TProduccionPapeletas, TPrensas> idPrensa;
    public static volatile SingularAttribute<TProduccionPapeletas, Short> lppl;
    public static volatile SingularAttribute<TProduccionPapeletas, Date> fechaReg;
    public static volatile SingularAttribute<TProduccionPapeletas, Long> nPalet;
    public static volatile SingularAttribute<TProduccionPapeletas, String> observaciones;
    public static volatile SingularAttribute<TProduccionPapeletas, String> usuario;
    public static volatile SingularAttribute<TProduccionPapeletas, String> responsableDiseno;
    public static volatile SingularAttribute<TProduccionPapeletas, String> operador;
    public static volatile SingularAttribute<TProduccionPapeletas, Long> idRegistro;

}