package ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento;

import java.util.Collection;
import java.util.Map;

import ar.uba.fi.tecnicas.tphipodromo.client.vista.Vista;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ApuestaDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.JockeyDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;

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
	
	public static Evento getOcultarMensaje() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onOcultarMensajePie(); 
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

	public static Evento getMostrarABMJockeys() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onMostrarABMJockeys(); 
			}
		};
	}

	public static Evento getListarJockeys() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onListarJockeys((Collection<JockeyDTO>)args[0]); 
			}
		};
	}

	public static Evento getMostrarJockey() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onMostrarJockey((JockeyDTO)args[0], (Boolean) args[1]); 
			}
		};
	}
	
	public static Evento getListaCaballosActualizada() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onListaCaballosActualizada((Collection<CaballoDTO>)args[0]); 
			}
		};
	}

	public static Evento getListaCarreraActualizada() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onListaCarreraActualizada((Collection<CarreraDTO>)args[0]); 
			}
		};
	}

	public static Evento getCarreraBorrada() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onCarreraBorrada(); 
			}
		};
	}

	public static Evento getMostrarABMCarreras() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onMostrarABMCarrera(); 
			}
		};
	}

	public static Evento getMostrarDatosCarrera() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onMostrarDatosCarrera((CarreraDTO)args[0], (Boolean)args[1]); 
			}
		};
	}

	public static Evento getMostrarParticipantes() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onMostrarParticipantesCarrera((CarreraDTO)args[0], (Collection<ParticipanteDTO>)args[1]); 
			}
		};
	}

	public static Evento getEditarParticipantes() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onEditarParticipantesCarrera((CarreraDTO)args[0], (Collection<ParticipanteDTO>)args[1]); 
			}
		};
	}
	
	public static Evento getMostrarNuevaApuesta() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onMostrarNuevaApuesta(); 
			}
		};
	}
	
	public static Evento getListaTiposApuestaActualizada() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onListaTiposApuestaActualizada((Collection<String>)args[0]); 
			}
		};
	}

	public static Evento getMostrarCaballosParaCarrera() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onMostrarCaballosParaCarrera((Collection<CaballoDTO>)args[0]); 
			}
		};
	}

	public static Evento getMostrarJockeysParaCarrera() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onMostrarJockeysParaCarrera((Collection<JockeyDTO>)args[0]); 
			}
		};
	}

	public static Evento getMostrarLiquidacionApuestas() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onMostrarLiquidacionApuestas(); 
			}
		};
	}
	
	public static Evento getMapaCarrerasActualizado() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onMapaCarrerasActualizado(
						(Map<CarreraDTO, Collection<ParticipanteDTO>>)args[0]); 
			}
		};
	}
	
	public static Evento getApuestaCreada() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onApuestaCreada(); 
			}
		};
	}
	
	public static Evento getMostrarABMResultados() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onMostrarABMResultados(); 
			}
		};
	}
	
	public static Evento getSiguientesEstadosValidos() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onGetSiguientesEstadosValidos((Collection<String>)args[0]); 
			}
		};
	}
	
	public static Evento getCambiarEstadoCarrera() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onCambiarEstadoCarrera((CarreraDTO)args[0], (String)args[1]); 
			}
		};
	}
	
	public static Evento getAsignarParticipantes() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onAsignarParticipantes((CarreraDTO)args[0], (Collection<ParticipanteDTO>)args[1]); 
			}
		};
	}
	
	public static Evento getBuscarCarrerasEnCurso() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onBuscarCarrerasEnCurso((Collection<CarreraDTO>)args[0]); 
			}
		};
	}
	
	public static Evento getApuestaEncontrada() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onMostrarApuestaBuscada((ApuestaDTO)args[0]); 
			}
		};
	}

	public static Evento getApuestaLiquidada() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onApuestaLiquidada((Double)args[0]); 
			}
		};
	}
	
	public static Evento getCarrerasEnInscripcionActualizadas() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onCarrerasEnInscripcionActualizadas(
						(Collection<CarreraDTO>) args[0]); 
			}
		};
	}
	
	public static Evento getMostrarCerrarInscripcion() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onMostrarCerrarInscripcion(); 
			}
		};
	}
	
	public static Evento getCarrerasApostablesActualizadas() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onCarrerasApostablesActualizadas(
						(Collection<CarreraDTO>) args[0]); 
			}
		};
	}
	
	public static Evento getMostrarCerrarApuestas() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onMostrarCerrarApuestas(); 
			}
		};
	}

	public static Evento getApuestaPagada() {
		return new Evento() {
			public void resolver(Vista v, Object[] args) { 
				v.onApuestaPagada(); 
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
