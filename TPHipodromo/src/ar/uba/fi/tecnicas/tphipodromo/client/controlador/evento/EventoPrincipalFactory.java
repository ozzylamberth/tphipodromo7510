package ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento;

import ar.uba.fi.tecnicas.tphipodromo.client.vista.VistaPrincipal;

public class EventoPrincipalFactory {
	
	public static Evento<VistaPrincipal> getEventoMostrar() {
		return new Evento<VistaPrincipal>() {
			public void resolver(VistaPrincipal v, Object[] args) {
				v.onMostrar();
			}
		};
	}
	
	public static Evento<VistaPrincipal> getEventoApuestasABM() {
		return new Evento<VistaPrincipal>() {
			public void resolver(VistaPrincipal v, Object[] args) {
				v.onMostrarApuestasAbm();
			}
		};
	}
	
	public static Evento<VistaPrincipal> getEventoCobrarApuestas() {
		return new Evento<VistaPrincipal>() {
			public void resolver(VistaPrincipal v, Object[] args) {
				v.onMostrarCobrarApuestas();
			}
		};
	}

	public static Evento<VistaPrincipal> getEventoCaballosABM() {
		return new Evento<VistaPrincipal>() {
			public void resolver(VistaPrincipal v, Object[] args) {
				v.onMostrarCaballosAbm();
			}
		};
	}

}
