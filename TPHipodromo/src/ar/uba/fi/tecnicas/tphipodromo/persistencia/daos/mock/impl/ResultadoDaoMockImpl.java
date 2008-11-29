package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl;

import java.util.HashMap;
import java.util.Map;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Resultado;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.ResultadoDao;

public class ResultadoDaoMockImpl extends DAOGenericoMockImpl<Resultado> implements ResultadoDao {
	
	private static Map<Long, Resultado> resultados = null;
	private static Secuencia secuencia = new Secuencia();
	
	public ResultadoDaoMockImpl() {
		if (resultados == null) {
			resultados = new HashMap<Long, Resultado>();
			this.guardar(new Resultado(1, 123));
			this.guardar(new Resultado(2, 230));
			this.guardar(new Resultado(3, 400));
		}
	}

	@Override
	protected Map<Long, Resultado> getDBMap() {
		return resultados;
	}
	
	@Override
	protected Secuencia getSecuencia() {
		return secuencia;
	}
	
}
