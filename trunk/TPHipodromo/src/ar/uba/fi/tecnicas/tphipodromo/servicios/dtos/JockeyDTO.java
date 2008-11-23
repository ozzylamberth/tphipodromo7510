package ar.uba.fi.tecnicas.tphipodromo.servicios.dtos;

import com.google.gwt.user.client.rpc.IsSerializable;

public class JockeyDTO implements IsSerializable {
	
	private Long id = new Long(0);
	private String apellido = "";
	private String nombre = "";
	private Double peso = new Double(0);

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPeso() {
		return this.peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
}
