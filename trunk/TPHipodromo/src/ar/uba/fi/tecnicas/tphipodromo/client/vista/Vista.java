package ar.uba.fi.tecnicas.tphipodromo.client.vista;

import java.util.Collection;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.JockeyDTO;

/**
 * Todas las vistas deben implementar esta interfaz. Por cada
 * vista en el sistema debe haber una interfaz que defina los 
 * eventos que escucha, y esa interfaz debe heredar de 
 * <code>Vista</code>.
 * 
 * Actualmente no tiene m�todos, pero supo tenerlos en alg�n
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
	
	public void onListarCaballos(Collection<CaballoDTO> lista) {};
	
	public void onMostrarABMApuestas() {};
	
	public void onMostrarHome() {};
	
	public void onMostrarLogin() {};
	
	public void onCaballoBorrado() {};
	
	public void onMostrarDatosCaballo(CaballoDTO caballo, Boolean editable) {}

	public void onMostrarMensajePie(String mensaje) {}

	public void onCrearCaballo() {}

	public void onMostrarABMJockeys() {}

	public void onListarJockeys(Collection<JockeyDTO> collection) {}

	public void onMostrarJockey(JockeyDTO jockeyDTO, Boolean editable) {}
	
}
