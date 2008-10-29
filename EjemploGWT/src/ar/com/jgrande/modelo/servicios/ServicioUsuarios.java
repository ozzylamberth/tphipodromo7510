package ar.com.jgrande.modelo.servicios;

import ar.com.jgrande.modelo.Usuario;
import ar.com.jgrande.modelo.exception.LoginInvalidoException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Servicio para administrar los usuarios del sistema.
 * 
 * La annotation <code>RemoteServiceRelativePath</code> sirve para
 * informarle a GWT en qué URL tiene que buscar la implementación
 * del servicio para los llamados a procedimiento remoto.
 * 
 * @author Juan
 *
 */
@RemoteServiceRelativePath("ServicioUsuarios")
public interface ServicioUsuarios extends RemoteService {
	
	/**
	 * Verifica si los datos de login son correctos.
	 * 
	 * @param id Nombre de usuario
	 * @param password Contraseña
	 * @return El usuario que ingresó al sistema.
	 * @throws LoginInvalidoException Si los datos son incorrectos.
	 */
	public Usuario login(String id, String password) throws LoginInvalidoException;

}
