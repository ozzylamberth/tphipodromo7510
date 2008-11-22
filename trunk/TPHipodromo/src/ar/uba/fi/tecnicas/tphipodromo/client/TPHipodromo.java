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
	  
	  VistaLoginGWT vistaLogin = new VistaLoginGWT(ctrlLogin);
	  VistaPrincipalGWT vistaPrincipal = new VistaPrincipalGWT();
	  VistaMenuGWT vistaMenu = new VistaMenuGWT(vistaPrincipal.getPanelIzquierda(), ctrlMenu);
	  VistaHomeGWT vistaHome = new VistaHomeGWT(vistaPrincipal.getPanelCentro());
	  VistaABMCaballosGWT vistaABMCaballos = new VistaABMCaballosGWT(vistaPrincipal.getPanelCentro(), ctrlABMCaballos);
	  VistaABMApuestasGWT vistaABMApuestas = new VistaABMApuestasGWT(vistaPrincipal.getPanelCentro());
	  
	  ctrlPrincipal.addObserver(vistaPrincipal);
	  ctrlPrincipal.addObserver(vistaHome);
	  ctrlPrincipal.addObserver(vistaMenu);
	  ctrlMenu.addObserver(vistaHome);
	  ctrlMenu.addObserver(vistaABMCaballos);
	  ctrlMenu.addObserver(vistaABMApuestas);
	  ctrlABMCaballos.addObserver(vistaABMCaballos);
	  
	  ctrlPrincipal.doMostrarPrincipal();
  }
}