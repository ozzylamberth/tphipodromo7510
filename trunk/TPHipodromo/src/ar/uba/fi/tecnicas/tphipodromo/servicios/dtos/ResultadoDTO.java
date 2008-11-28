package ar.uba.fi.tecnicas.tphipodromo.servicios.dtos;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ResultadoDTO implements IsSerializable {
	
	private Long id = new Long(0);
	private int ordenLlegada = 0;
	private long tiempo = 0;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getOrdenLlegada() {
		return this.ordenLlegada;
	}

	public void setOrdenLlegada(int ordenLlegada) {
		this.ordenLlegada = ordenLlegada;
	}

	public long getTiempo() {
		return this.tiempo;
	}

	public void setTiempo(long tiempo) {
		this.tiempo = tiempo;
	}
}
