package ar.uba.fi.tecnicas.tphipodromo.client.vista;

/**
 * Vista para mostrarle al usuario el menú principal luego del login.
 * 
 * @author Juan
 *
 */
public interface VistaBienvenida extends Vista {
	
	/** Evento disparado cuando se quiere mostrar la pantalla de bienvenida. */
	public void onBienvenida();

	public void onIniciar();

}
