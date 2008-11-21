package ar.uba.fi.tecnicas.tphipodromo.client;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorLogin;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorPrincipal;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.PantallaPrincipalGWT;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.VistaLoginGWT;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TPHipodromo implements EntryPoint {
	
	private Mensajes mensajes = GWT.create(Mensajes.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
		ControladorPrincipal ctrlPrincipal = new ControladorPrincipal();
		ControladorLogin ctrlLogin = new ControladorLogin(ctrlPrincipal);
		
		ctrlLogin.addObserver(new VistaLoginGWT(ctrlLogin));
		//History.addHistoryListener(ctrlLogin);
		
		ctrlPrincipal.addObserver(new PantallaPrincipalGWT(ctrlPrincipal));
		
		ctrlLogin.doPedirDatos();
//		ctrlBienvenida.onIniciar();
  }
}
