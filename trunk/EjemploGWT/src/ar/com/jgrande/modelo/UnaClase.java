package ar.com.jgrande.modelo;

/**
 * Esta clase es arbitraria para testear <code>DaoGenerico</code>.
 * 
 * @author Juan
 *
 */
public class UnaClase implements Identificable<Long> {
	
	/** Identificador del objeto. */
	public Long id;
	
	/** Descripción */
	public String descripcion;

	public Long getId() {
		return id;
	}

	public void setId(Long idNuevo) {
		this.id = idNuevo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcionNueva) {
		this.descripcion = descripcionNueva;
	}
}
