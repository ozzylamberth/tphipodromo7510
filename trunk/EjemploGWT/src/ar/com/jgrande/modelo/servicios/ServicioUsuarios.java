package ar.com.jgrande.modelo.servicios;

import ar.com.jgrande.modelo.Usuario;
import ar.com.jgrande.modelo.exception.LoginInvalidoException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Servicio para administrar los usuarios del sistema.
 * 
 * La annotation <code>RemoteServiceRelativePath</code> sirve para
 * informarle a GWT en qu� URL tiene que buscar la implementaci�n
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
	 * @param password Contrase�a
	 * @return El usuario que ingres� al sistema.
	 * @throws LoginInvalidoException Si los datos son incorrectos.
	 */
	public Usuario login(String id, String password) throws LoginInvalidoException;

}
