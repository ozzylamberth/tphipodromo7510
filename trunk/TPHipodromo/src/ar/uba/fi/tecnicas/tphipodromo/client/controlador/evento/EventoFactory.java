package ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento;

import ar.uba.fi.tecnicas.tphipodromo.client.vista.Vista;


public class EventoFactory {
	
	public static Evento getMostrarPrincipal() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onMostrarPrincipal(); 
			}
		};
	}
	
	public static Evento getMostrarABMCaballos() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onMostrarABMCaballos(); 
			}
		};
	}
	
	public static Evento getMostrarABMApuestas() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onMostrarABMApuestas(); 
			}
		};
	}
	
	public static Evento getMostrarHome() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onMostrarHome(); 
			}
		};
	}
	
	/*
	public static Evento getEventoPedirDatosLogin() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onPedirDatosLogin(); 
				}
		};
	}
	
	public static Evento getEventoDatosLoginCorrectos() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) {
				 v.onDatosLoginCorrectos(); 
			}
		};
	}
	
	public static Evento getEventoDatosLoginIncorrectos() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				 v.onDatosLoginIncorrectos();
			}
		};
	}
	 */
}
