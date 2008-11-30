package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.tipos;

import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.EstadoApuesta;

public class TipoApuesta extends EnumUserType<EstadoApuesta> {
	public TipoApuesta(){
		super(EstadoApuesta.class);
	}
}
