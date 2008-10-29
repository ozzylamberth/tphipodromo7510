package ar.com.jgrande.modelo;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Un usuario del sistema.
 * 
 * @author Juan
 *
 */
public class Usuario implements IsSerializable {
	
	/** Identificador del usuario (corresponde al nombre de usuario) */
	private String id;
	
	/** Nombre del usuario */
	private String nombre;
	
	/** Apellido del usuario */
	private String apellido;
	
	/** Contraseña del usuario */
	private String contrasenia;

	public String getId() {
		return id;
	}

	public void setId(String idNuevo) {
		this.id = idNuevo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombreNuevo) {
		this.nombre = nombreNuevo;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellidoNuevo) {
		this.apellido = apellidoNuevo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contraseniaNueva) {
		this.contrasenia = contraseniaNueva;
	}

}
