package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.Apuesta;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.ApuestaDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.MultiplesObjetosException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;

public class ApuestaDaoMockImpl extends DAOGenericoMockImpl<Apuesta> implements ApuestaDao {
	
	private static Map<Long, Apuesta> apuestas = new HashMap<Long, Apuesta>();
	private static Secuencia secuencia = new Secuencia();

	@Override
	protected Map<Long, Apuesta> getDBMap() {
		return apuestas;
	}

	@Override
	protected Secuencia getSecuencia() {
		return secuencia;
	}

	@Override
	public Apuesta buscarPorNroTicket(Long nroTicket) throws ObjetoInexistenteException, MultiplesObjetosException {
		Iterator<Apuesta> it = this.buscarTodos().iterator();
		while (it.hasNext()) {
			Apuesta apuesta = (Apuesta) it.next();
			if (apuesta.getNroTicket() == nroTicket.longValue()) {
				return apuesta;
			}
		}
		throw new ObjetoInexistenteException();
	}

	@Override
	public Long buscarMayorNroTicket() {
		Long mayorNroTicket = new Long("0");
		Iterator<Apuesta> it = this.buscarTodos().iterator();
		while (it.hasNext()) {
			Apuesta apuesta = (Apuesta) it.next();
			if (mayorNroTicket.compareTo(apuesta.getNroTicket()) < 0) {
				mayorNroTicket = apuesta.getNroTicket();
			}
		}
		return mayorNroTicket;
	}

}
