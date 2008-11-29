package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl;

public class Secuencia {
	
	private long valorCorriente = 0;
	
	public long getSiguienteValor() {
		valorCorriente++;
		return valorCorriente;
	}
	
}
