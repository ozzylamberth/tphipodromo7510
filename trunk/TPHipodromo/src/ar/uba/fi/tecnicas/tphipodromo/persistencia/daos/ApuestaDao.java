package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos;

import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.Apuesta;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.MultiplesObjetosException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;

public interface ApuestaDao extends DAOGenerico<Apuesta> {
	
	public Apuesta buscarPorNroTicket(Long nroTicket) throws MultiplesObjetosException, ObjetoInexistenteException;
	
	/**
	 * Retorna el mayor numero de ticket que esta asignado a una apuesta
	 */
	public Long buscarMayorNroTicket() throws MultiplesObjetosException;
	
}
