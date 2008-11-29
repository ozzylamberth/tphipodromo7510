package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl;

import java.util.HashMap;
import java.util.Map;

import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.Apuesta;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.ApuestaDao;

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

}
