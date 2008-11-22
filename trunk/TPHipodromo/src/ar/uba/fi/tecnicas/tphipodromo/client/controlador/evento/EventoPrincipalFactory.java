package ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento;

import ar.uba.fi.tecnicas.tphipodromo.client.vista.Vista;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.VistaPrincipal;

public class EventoPrincipalFactory {
	
	public static Evento<VistaPrincipal> getEventoMostrar() {
		return new Evento<VistaPrincipal>() {
			public void resolver(VistaPrincipal v, Object[] args) {
				v.mostrar();
			}
		};
	}
	
	public static Evento<VistaPrincipal> getEventoCambiarContenido() {
		return new Evento<VistaPrincipal>() {
			public void resolver(VistaPrincipal v, Object[] args) {
				v.onCambiarContenido((Vista)args[0]);
			}
		};
	}

}
