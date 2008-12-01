package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.tipos;

import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.EstadoApuesta;

public class TipoEstadoApuesta extends EnumUserType<EstadoApuesta> {
	public TipoEstadoApuesta() {
		super(EstadoApuesta.class);
	}
}
