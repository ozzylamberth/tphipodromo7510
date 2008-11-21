package ar.uba.fi.tecnicas.tphipodromo.servicios.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CaballoDTO implements IsSerializable {
	
	private String nombre;
	
	private Integer edad;
	
	private Integer peso;
	
	public CaballoDTO() {
	}
	
	public CaballoDTO(String nombre, Integer edad, Integer peso) {
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
}
