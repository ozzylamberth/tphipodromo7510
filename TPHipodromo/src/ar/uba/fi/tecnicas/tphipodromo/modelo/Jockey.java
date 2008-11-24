package ar.uba.fi.tecnicas.tphipodromo.modelo;

import java.math.BigDecimal;

/**
 * Clase que modela un Jinete de un Caballo.
 * 
 * @author Fernando E. Mansilla - 84567
 */
public class Jockey implements Identificable {
	
	private Long id;
	private String apellido;
	private String nombre;
	private BigDecimal peso;

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

	public BigDecimal getPeso() {
		return this.peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}
}