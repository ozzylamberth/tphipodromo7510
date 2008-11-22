package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.EventoPrincipalFactory;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.VistaABMApuestas;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.VistaABMCaballos;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.VistaPrincipal;

public class ControladorMenu extends Controlador<VistaPrincipal> {
	
	VistaABMCaballos vistaABMCaballos;
	
	VistaABMApuestas vistaABMApuestas;
	
	public void doABMCaballos() {
		notifyObservers(EventoPrincipalFactory.getEventoCambiarContenido(),
				vistaABMCaballos);
	}
	
	public void doABMApuestas() {
		notifyObservers(EventoPrincipalFactory.getEventoCambiarContenido(),
				vistaABMApuestas);
	}

	public void setVistaABMCaballos(VistaABMCaballos vistaABMCaballos) {
		this.vistaABMCaballos = vistaABMCaballos;
	}

	public void setVistaABMApuestas(VistaABMApuestas vistaABMApuestas) {
		this.vistaABMApuestas = vistaABMApuestas;
	}
	
}
