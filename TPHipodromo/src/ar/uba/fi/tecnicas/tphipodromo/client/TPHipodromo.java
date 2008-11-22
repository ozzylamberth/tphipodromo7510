package ar.uba.fi.tecnicas.tphipodromo.client;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMApuestas;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMCaballos;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorLogin;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorMenu;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorPrincipal;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.VistaABMApuestasGWT;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.VistaABMCaballosGWT;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.VistaHomeGWT;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.VistaLoginGWT;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.VistaMenuGWT;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.VistaPrincipalGWT;

import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TPHipodromo implements EntryPoint {
	
  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
	  ControladorPrincipal ctrlPrincipal = new ControladorPrincipal();
	  ControladorMenu ctrlMenu = new ControladorMenu();
	  ControladorLogin ctrlLogin = new ControladorLogin(ctrlPrincipal);
	  ControladorABMCaballos ctrlABMCaballos = new ControladorABMCaballos();
	  ControladorABMApuestas ctrlABMApuestas = new ControladorABMApuestas();
	  
	  VistaMenuGWT vistaMenu = new VistaMenuGWT(ctrlMenu);
	  VistaHomeGWT vistaHome = new VistaHomeGWT();
	  VistaLoginGWT vistaLogin = new VistaLoginGWT(ctrlLogin);
	  VistaPrincipalGWT vistaPrincipal = new VistaPrincipalGWT(vistaMenu);
	  VistaABMCaballosGWT vistaABMCaballos = new VistaABMCaballosGWT();
	  VistaABMApuestasGWT vistaABMApuestas = new VistaABMApuestasGWT();
	  
	  ctrlPrincipal.setVistaHome(vistaHome);
	  ctrlMenu.setVistaABMCaballos(vistaABMCaballos);
	  ctrlMenu.setVistaABMApuestas(vistaABMApuestas);
	  
	  ctrlLogin.addObserver(vistaLogin);
	  ctrlPrincipal.addObserver(vistaPrincipal);
	  ctrlMenu.addObserver(vistaPrincipal);
	  ctrlABMCaballos.addObserver(vistaABMCaballos);
	  ctrlABMApuestas.addObserver(vistaABMApuestas);
	  
	  ctrlPrincipal.doMostrarPrincipal();
  }
}
