package ar.uba.fi.tecnicas.tphipodromo.client.vista;

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
	
	public void onMostrarPrincipal() {};
	
	public void onMostrarABMCaballos() {};
	
	public void onMostrarABMApuestas() {};
	
	public void onMostrarHome() {};
	
	public void onMostrarLogin() {};
	
}
