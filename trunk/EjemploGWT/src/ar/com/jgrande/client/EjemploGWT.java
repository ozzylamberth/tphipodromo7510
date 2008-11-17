package ar.com.jgrande.client;

import ar.com.jgrande.client.controlador.ControladorBienvenida;
import ar.com.jgrande.client.controlador.ControladorLogin;
import ar.com.jgrande.client.controlador.ControladorPrincipal;
import ar.com.jgrande.client.vista.PantallaPrincipal;
import ar.com.jgrande.client.vista.impl.PantallaPrincipalGWT;
import ar.com.jgrande.client.vista.impl.VistaBienvenidaGWT;
import ar.com.jgrande.client.vista.impl.VistaLoginGWT;

import com.google.gwt.core.client.EntryPoint;

/**
 * Define el módulo GWT.
 */
public class EjemploGWT implements EntryPoint {
	
	/**
	 * Este método se llama al inicializar el módulo GWT.
	 */
	public void onModuleLoad() {
		ControladorPrincipal ctrlPrincipal = new ControladorPrincipal();
		ControladorBienvenida ctrlBienvenida = new ControladorBienvenida(ctrlPrincipal);
		ControladorLogin ctrlLogin = new ControladorLogin(ctrlBienvenida);
		
		ctrlLogin.addObserver(new VistaLoginGWT(ctrlLogin));
		//History.addHistoryListener(ctrlLogin);
		
		ctrlBienvenida.addObserver(new VistaBienvenidaGWT(ctrlBienvenida));
		//History.addHistoryListener(ctrlBienvenida);
		
		ctrlPrincipal.addObserver(new PantallaPrincipalGWT(ctrlPrincipal));
		
		ctrlLogin.doPedirDatos();
//		ctrlBienvenida.onIniciar();
	}
  
}
