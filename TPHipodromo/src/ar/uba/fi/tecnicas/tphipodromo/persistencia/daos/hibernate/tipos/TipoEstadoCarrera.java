package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.tipos;

import ar.uba.fi.tecnicas.tphipodromo.modelo.EstadoCarrera;

public class TipoEstadoCarrera extends EnumUserType<EstadoCarrera>{
	public TipoEstadoCarrera() {
		super(EstadoCarrera.class);
	}
}
