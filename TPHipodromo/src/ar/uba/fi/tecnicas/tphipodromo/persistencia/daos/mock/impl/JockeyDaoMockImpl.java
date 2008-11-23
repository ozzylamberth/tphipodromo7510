package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl;

import java.math.BigDecimal;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Jockey;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.JockeyDao;

public class JockeyDaoMockImpl extends DAOGenericoMockImpl<Jockey> implements JockeyDao {
	
	public JockeyDaoMockImpl() {
		this.guardar(this.getJockey("Juan", "Grande", new BigDecimal(70)));
		this.guardar(this.getJockey("Tomas", "Lobbe", new BigDecimal(60)));
		this.guardar(this.getJockey("Damian", "Wasserman", new BigDecimal(80)));
		this.guardar(this.getJockey("El", "Facha", new BigDecimal(50)));
	}
	
	private Jockey getJockey(String nombre, String apellido, BigDecimal peso) {
		Jockey jockey = new Jockey();
		jockey.setNombre(nombre);
		jockey.setApellido(apellido);
		jockey.setPeso(peso);
		return jockey;
	}
}
