package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.tipos;

import ar.uba.fi.tecnicas.tphipodromo.modelo.EstadoParticipante;

public class TipoEstadoParticipante extends EnumUserType<EstadoParticipante>{
	
	public TipoEstadoParticipante() {
		super(EstadoParticipante.class);
	}
}
