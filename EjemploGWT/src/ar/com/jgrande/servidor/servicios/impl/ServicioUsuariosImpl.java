package ar.com.jgrande.servidor.servicios.impl;

import ar.com.jgrande.modelo.Usuario;
import ar.com.jgrande.modelo.exception.LoginInvalidoException;
import ar.com.jgrande.modelo.servicios.ServicioUsuarios;

/**
 * Implementación de <code>ServicioUsuarios</code>.
 * 
 * @author Juan
 *
 */
@SuppressWarnings("serial")
public class ServicioUsuariosImpl extends ServicioAbstracto implements
		ServicioUsuarios {

	/**
	 * Implementación mock. Solo es correcta si los datos de login son:
	 * <li>usuario=guest
	 * <li>password=guest
	 * 
	 */
	public Usuario login(String id, String password) throws LoginInvalidoException {
		logger.trace("Usuario " + id + " identificándose.");
		
		if (!id.equals("guest") || !password.equals("guest")) {
			logger.trace("El usuario " + id + " ingresó datos de identificación incorrectos.");
			throw new LoginInvalidoException();
		}
		
		Usuario guest = new Usuario();
		guest.setId("guest");
		guest.setNombre("Usuario");
		guest.setApellido("Invitado");
		guest.setContrasenia("guest");
		
		logger.trace("El usuario " + id + " se identificó correctamente.");
		
		return guest;
	}

}
