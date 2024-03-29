package ar.uba.fi.tecnicas.tphipodromo.client;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMApuestas;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMCaballos;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMCarreras;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMJockey;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMResultados;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorInscripcion;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorLiquidacionApuestas;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorLogin;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorMenu;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorPrincipal;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.VistaABMApuestasGWT;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.VistaABMCaballosGWT;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.VistaABMCarreras;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.VistaABMJockeysGWT;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.VistaABMResultados;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.VistaCaballoGWT;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.VistaCarreraGWT;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.VistaCerrarApuestasGWT;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.VistaCerrarInscripcionGWT;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.VistaHomeGWT;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.VistaJockeyGWT;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.VistaLiquidacionApuestas;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.VistaLoginGWT;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.VistaMenuGWT;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.VistaNuevaApuestaGWT;
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
	  ControladorABMJockey ctrlABMJockey = new ControladorABMJockey();
	  ControladorABMApuestas ctrlABMApuestas = new ControladorABMApuestas();
	  ControladorABMCarreras ctrlABMCarreras = new ControladorABMCarreras();
	  ControladorABMResultados ctrlABMResultados = new ControladorABMResultados();
	  ControladorLiquidacionApuestas controladorLiquidacion = new ControladorLiquidacionApuestas();
	  ControladorInscripcion ctrlInscripcion = new ControladorInscripcion();
	  
	  VistaLoginGWT vistaLogin = new VistaLoginGWT(ctrlLogin);
	  VistaPrincipalGWT vistaPrincipal = new VistaPrincipalGWT();
	  VistaMenuGWT vistaMenu = new VistaMenuGWT(vistaPrincipal.getPanelIzquierda(), ctrlMenu);
	  VistaHomeGWT vistaHome = new VistaHomeGWT(vistaPrincipal.getPanelCentro());
	  VistaABMCaballosGWT vistaABMCaballos = new VistaABMCaballosGWT(vistaPrincipal.getPanelCentro(), ctrlABMCaballos);
	  VistaCaballoGWT vistaCaballo = new VistaCaballoGWT(ctrlABMCaballos);
	  VistaABMApuestasGWT vistaABMApuestas = new VistaABMApuestasGWT(vistaPrincipal.getPanelCentro());
	  VistaABMCarreras vistaABMCarreras = new VistaABMCarreras(vistaPrincipal.getPanelCentro(), ctrlABMCarreras);
	  VistaCarreraGWT vistaCarreraGWT = new VistaCarreraGWT(ctrlABMCarreras);
	  VistaABMJockeysGWT vistaABMJockeys = new VistaABMJockeysGWT(vistaPrincipal.getPanelCentro(), ctrlABMJockey);
	  VistaJockeyGWT vistaJockey = new VistaJockeyGWT(ctrlABMJockey);
	  VistaNuevaApuestaGWT vistaApostar = new VistaNuevaApuestaGWT(vistaPrincipal.getPanelCentro(), ctrlABMApuestas);
	  VistaABMResultados vistaABMResultados = new VistaABMResultados(vistaPrincipal.getPanelCentro(), ctrlABMResultados);
	  VistaLiquidacionApuestas vistaLiquidacion = new VistaLiquidacionApuestas(vistaPrincipal.getPanelCentro(), controladorLiquidacion);
	  VistaCerrarInscripcionGWT vistaCerrarInscripcion = new VistaCerrarInscripcionGWT(vistaPrincipal.getPanelCentro(), ctrlInscripcion);
	  VistaCerrarApuestasGWT vistaCerrarApuestas = new VistaCerrarApuestasGWT(vistaPrincipal.getPanelCentro(), ctrlInscripcion);
	  
	  ctrlPrincipal.addObserver(vistaPrincipal);
	  ctrlPrincipal.addObserver(vistaHome);
	  ctrlPrincipal.addObserver(vistaMenu);
	  
	  ctrlMenu.addObserver(vistaHome);
	  ctrlMenu.addObserver(vistaABMCaballos);
	  ctrlMenu.addObserver(vistaABMApuestas);
	  ctrlMenu.addObserver(vistaABMJockeys);
	  ctrlMenu.addObserver(vistaPrincipal);
	  ctrlMenu.addObserver(vistaABMCarreras);
	  ctrlMenu.addObserver(vistaApostar);
	  ctrlMenu.addObserver(vistaABMResultados);
	  ctrlMenu.addObserver(vistaLiquidacion);
	  ctrlMenu.addObserver(vistaCerrarInscripcion);
	  ctrlMenu.addObserver(vistaCerrarApuestas);
	  
	  ctrlABMCaballos.addObserver(vistaABMCaballos);
	  ctrlABMCaballos.addObserver(vistaCaballo);
	  ctrlABMCaballos.addObserver(vistaPrincipal);
	  
	  ctrlABMJockey.addObserver(vistaABMJockeys);
	  ctrlABMJockey.addObserver(vistaJockey);
	  ctrlABMJockey.addObserver(vistaPrincipal);
	  
	  ctrlABMCarreras.addObserver(vistaABMCarreras);
	  ctrlABMCarreras.addObserver(vistaPrincipal);
	  ctrlABMCarreras.addObserver(vistaCarreraGWT);
	  
	  ctrlABMApuestas.addObserver(vistaPrincipal);
	  ctrlABMApuestas.addObserver(vistaApostar);
	  
	  ctrlABMResultados.addObserver(vistaABMResultados);
	  ctrlABMResultados.addObserver(vistaPrincipal);
	  
	  ctrlInscripcion.addObserver(vistaPrincipal);
	  ctrlInscripcion.addObserver(vistaCerrarInscripcion);
	  ctrlInscripcion.addObserver(vistaCerrarApuestas);
	  
	  controladorLiquidacion.addObserver(vistaLiquidacion);
	  controladorLiquidacion.addObserver(vistaPrincipal);
	  
	  ctrlPrincipal.doMostrarPrincipal();
  }
}
