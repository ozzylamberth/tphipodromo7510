package ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.client.vista.Vista;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;

@SuppressWarnings("unchecked")
public class EventoFactory {
	
	public static Evento getCaballoBorrado() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onCaballoBorrado(); 
			}
		};
	}
	
	public static Evento getMostrarDatosCaballo() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onMostrarDatosCaballo((CaballoDTO)args[0], (Boolean)args[1]); 
			}
		};
	}
	
	public static Evento getErrorRPC() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onErrorRPC((Throwable)args[0]); 
			}
		};
	}
	
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
	
	public static Evento getListarCaballos() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onListarCaballos((Collection<CaballoDTO>)args[0]); 
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

	public static Evento getMostrarMensaje() {
		
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onMostrarMensajePie((String)args[0]); 
			}
		};
	}

	public static Evento getCrearCaballo() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onCrearCaballo(); 
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
