package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos;

import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.Apuesta;

public interface ApuestaDao extends DAOGenerico<Apuesta> {
	
	public Apuesta buscarPorNroTicket(Long nroTicket);
	
	/**
	 * Retorna el mayor numero de ticket que esta asignado a una apuesta
	 */
	public Long buscarMayorNroTicket();
	
}
