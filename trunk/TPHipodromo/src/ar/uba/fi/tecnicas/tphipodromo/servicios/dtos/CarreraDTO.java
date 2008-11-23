package ar.uba.fi.tecnicas.tphipodromo.servicios.dtos;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CarreraDTO implements IsSerializable {
	
	private Long id = new Long(0);
	private Double distancia = new Double(0);
	private Date fechaYHora = new Date();
	private String nombre = "";
	private int numero = 0;
	private String estado = "";

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getDistancia() {
		return this.distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	public Date getFechaYHora() {
		return this.fechaYHora;
	}

	public void setFechaYHora(Date fechaYHora) {
		this.fechaYHora = fechaYHora;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
