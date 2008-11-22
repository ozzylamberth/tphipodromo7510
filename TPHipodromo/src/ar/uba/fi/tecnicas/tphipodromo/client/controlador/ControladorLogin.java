package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import com.google.gwt.user.client.HistoryListener;

/**
 * Controla los eventos ocurridos en el proceso de login.
 * 
 * @author Juan
 *
 */
public class ControladorLogin extends Controlador implements HistoryListener {
	
	/** Controlador que tomará la posta si el login es satisfactorio. */
	private ControladorPrincipal ctrlPrincipal;
	
	/**
	 * Constructor público.
	 * 
	 * @param ctrlBienvenida Controlador que tomará la posta si el login es satisfactorio.
	 */
	public ControladorLogin(ControladorPrincipal ctrlPrincipal) {
		this.ctrlPrincipal = ctrlPrincipal;
	}

	/**
	 * Informa a todas las vistas que deben pedir los datos de login
	 * al usuario.
	 * 
	 */
	public void doPedirDatos() {
		//notifyObservers(EventoFactory.getEventoPedirDatosLogin());
	}
	
	/**
	 * Verifica los datos de login y notifica a las vistas el resultado.
	 * 
	 * @param usuario Nombre de usuario.
	 * @param contrasenia Contraseña.
	 */
	public void doVerificarDatos(String usuario, String contrasenia) {
		/* 
		 * Le pide a FacadeServicios el ServicioUsuarios y ejecuta el
		 * método login(usuario, password). Como todas las llamadas a
		 * procedimiento remoto son asincrónicas (por la naturaleza
		 * de Javascript), entonces le pasa un objeto AsynCallback
		 * que se encargará de notificar a las vistas el resultado.
		 *  
		 */ 
//		FacadeServicios.get().getServicioUsuarios().login(usuario, contrasenia, 
//			new AsyncCallback<Usuario>() {
//				/**
//				 * Notifica a las vistas que ocurrió un error en el proceso
//				 * de login.
//				 * 
//				 * TODO Pasar el tipo de error a la vista.
//				 * TODO Manejar por separado los errores de conexión de los
//				 *      merrores de dominio.
//				 */
//				public void onFailure(Throwable caught) {
//					notifyObservers(EventoLogin.DATOS_LOGIN_INCORRECTOS);
//				}
//				
//				/**
//				 * Notifica a las vistas que el login fue satisfactorio y
//				 * muestra la pantalla de bienvenida.
//				 * 
//				 * @param result Instancia del usuario que ingresó al sistema.
//				 */
//				public void onSuccess(Usuario result) {
//					notifyObservers(EventoLogin.DATOS_LOGIN_CORRECTOS);
//					ctrlBienvenida.doBienvenida();
//				}
//			}
//		);
		//notifyObservers(EventoLoginFactory.getEventoDatosLoginCorrectos());
		//ctrlPrincipal.doMostrarPrincipal();
	}
	
	/**
	 * @see ControladorBienvenida.onHistoryChanged
	 */
	public void onHistoryChanged(String historyToken) {
		/*
		if( historyToken.equals("login"))
			doPedirDatos();
		*/
	}
}
