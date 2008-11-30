package ar.uba.fi.tecnicas.tphipodromo.client.vista;

import java.util.Collection;
import java.util.Map;

import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ApuestaDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.JockeyDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;

/**
 * Todas las vistas deben implementar esta interfaz. Por cada
 * vista en el sistema debe haber una interfaz que defina los 
 * eventos que escucha, y esa interfaz debe heredar de 
 * <code>Vista</code>.
 * 
 * Actualmente no tiene métodos, pero supo tenerlos en algún
 * momento y probablemente vuelva a tenerlos a medida que
 * se pula el uframework.
 * 
 * @author Juan Grande
 *
 */
public abstract class Vista {
	
	/**
	 * Muestra la vista.
	 * 
	 * @author Juan
	 */
	public void mostrar() {};
	
	/**
	 * Oculta la vista.
	 * 
	 * @author Juan
	 */
	public void ocultar() {};
	
	public void onErrorRPC(Throwable e) {};
	
	public void onMostrarPrincipal() {};
	
	public void onMostrarABMCaballos() {};
	
	public void onListaCaballosActualizada(Collection<CaballoDTO> lista) {};
	
	public void onMostrarABMApuestas() {};
	
	public void onMostrarHome() {};
	
	public void onMostrarLogin() {};
	
	public void onCaballoBorrado() {};
	
	public void onMostrarDatosCaballo(CaballoDTO caballo, Boolean editable) {}

	public void onMostrarMensajePie(String mensaje) {}
	
	public void onOcultarMensajePie() {}

	public void onMostrarABMJockeys() {}

	public void onListarJockeys(Collection<JockeyDTO> collection) {}

	public void onMostrarJockey(JockeyDTO jockeyDTO, Boolean editable) {}
	
	public void onMostrarABMCarrera(){}
	
	public void onListaCarreraActualizada(Collection<CarreraDTO> lista){}

	public void onCarreraBorrada() {}
	
	public void onMostrarDatosCarrera(CarreraDTO carrera, Boolean editable){}

	public void onMostrarParticipantesCarrera(CarreraDTO carreraDTO,
			Collection<ParticipanteDTO> participantes) {}

	public void onMostrarNuevaApuesta() {};
	
	public void onEditarParticipantesCarrera(CarreraDTO carreraDTO,
			Collection<ParticipanteDTO> participantes) {}
	
	public void onMostrarCaballosParaCarrera(Collection<CaballoDTO> collection) {}

	public void onMostrarJockeysParaCarrera(Collection<JockeyDTO> collection) {}
	
	public void onListaTiposApuestaActualizada(Collection<String> lista) {}
	
	public void onMapaCarrerasActualizado(Map<CarreraDTO, Collection<ParticipanteDTO>> mapa) {}
	
	public void onApuestaCreada() {};
		
	public void onMostrarABMResultados() {};
	
	public void onGetSiguientesEstadosValidos(Collection<String> lista) {};
	
	public void onCambiarEstadoCarrera(CarreraDTO carrera, String estado) {};

	public void onAsignarParticipantes(CarreraDTO carrera, Collection<ParticipanteDTO> lista) {};

	public void onMostrarLiquidacionApuestas() {}

	public void onMostrarApuestaBuscada(ApuestaDTO apuestaDTO) {}

	public void onApuestaLiquidada(Double monto) {}

}
