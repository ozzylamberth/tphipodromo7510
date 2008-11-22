package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.EventoPrincipalFactory;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.VistaHome;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.VistaPrincipal;

public class ControladorPrincipal extends Controlador<VistaPrincipal> {
	
	private VistaHome vistaHome;
	
	public void doMostrarPrincipal() {
		this.notifyObservers(EventoPrincipalFactory.getEventoMostrar());
		this.notifyObservers(EventoPrincipalFactory.getEventoCambiarContenido(),
				vistaHome);
	}

	public void setVistaHome(VistaHome vistaHome) {
		this.vistaHome = vistaHome;
	}
	
}
