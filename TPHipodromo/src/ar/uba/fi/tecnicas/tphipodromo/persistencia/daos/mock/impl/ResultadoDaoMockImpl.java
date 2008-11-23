package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Resultado;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.ResultadoDao;

public class ResultadoDaoMockImpl extends DAOGenericoMockImpl<Resultado> implements ResultadoDao {
	
	public ResultadoDaoMockImpl() {
		this.guardar(new Resultado(1, 123));
		this.guardar(new Resultado(2, 230));
		this.guardar(new Resultado(3, 400));
	}
	
}
