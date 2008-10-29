package ar.com.jgrande.modelo.servicios;

import ar.com.jgrande.modelo.Usuario;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Interfaz asincrónica de <code>ServicioUsuarios</code>.
 * 
 * Es necesaria en GWT para permitir que los llamados a los servicios
 * tengan respuesta asincrónica. Se agrega un parámetro <code>AsyncCallback</code>
 * que se encarga de procesar la respuesta.
 * 
 * Las aplicaciones GWT programan contra esta interfaz, pero los servicios
 * deben implementar la interfaz que no tiene el <code>Async</code> en el
 * nombre.
 * 
 * @author Juan
 *
 */
public interface ServicioUsuariosAsync {
	
	public void login(String id, String password, AsyncCallback<Usuario> callback);

}
