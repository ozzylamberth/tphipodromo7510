package ar.com.jgrande.client.controlador.eventos;

import ar.com.jgrande.client.controlador.ControladorLogin;
import ar.com.jgrande.client.vista.VistaLogin;

/**
 * Eventos lanzados por el controlador <code>ControladorLogin</code>.
 * 
 * @see ControladorLogin
 * @author Juan
 *
 */
public enum EventoLogin implements Evento<VistaLogin> {
	
	/** @see VistaLogin.onPedirDatosLogin */
	PEDIR_DATOS_LOGIN { public void resolver(VistaLogin v, Object[] args) { v.onPedirDatosLogin(); } },
	
	/** @see VistaLogin.onDatosLoginCorrectos */
	DATOS_LOGIN_CORRECTOS { public void resolver(VistaLogin v, Object[] args) { v.onDatosLoginCorrectos(); } },
	
	/** @see VistaLogin.onDatosLoginIncorrectos */
	DATOS_LOGIN_INCORRECTOS { public void resolver(VistaLogin v, Object[] args) { v.onDatosLoginIncorrectos(); } };
	
}
