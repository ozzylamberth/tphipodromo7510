package ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorLogin;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.VistaLogin;

/**
 * Eventos lanzados por el controlador <code>ControladorLogin</code>.
 * 
 * @see ControladorLogin
 * @author Juan
 *
 */
public class EventoLoginFactory {
	
	public static Evento<VistaLogin> getEventoPedirDatosLogin() {
		return new Evento<VistaLogin>() {
			public void resolver(VistaLogin v, Object[] args) { 
				v.onPedirDatosLogin(); 
				}
		};
	}
	
	public static Evento<VistaLogin> getEventoDatosLoginCorrectos() {
		return new Evento<VistaLogin>() {
			public void resolver(VistaLogin v, Object[] args) {
				 v.onDatosLoginCorrectos(); 
			}
		};
	}
	
	public static Evento<VistaLogin> getEventoDatosLoginIncorrectos() {
		return new Evento<VistaLogin>() {
			public void resolver(VistaLogin v, Object[] args) { 
				 v.onDatosLoginIncorrectos();
			}
		};
	}
}
