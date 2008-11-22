package ar.uba.fi.tecnicas.tphipodromo.servicios.dtos;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CaballoDTO implements IsSerializable {
	
	private String nombre = "";
	
	private Integer edad = new Integer(0);
	
	private Double peso = new Double(0);
	
	public CaballoDTO() {
	}
	
	public CaballoDTO(String nombre, Integer edad, Double peso) {
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

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
}
