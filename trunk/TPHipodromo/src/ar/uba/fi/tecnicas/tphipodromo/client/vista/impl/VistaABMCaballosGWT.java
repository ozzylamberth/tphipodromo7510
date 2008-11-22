package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import ar.uba.fi.tecnicas.tphipodromo.client.util.Listado;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class VistaABMCaballosGWT extends VistaGWT {

	VerticalPanel panelPrincipal;

	public VistaABMCaballosGWT(HasWidgets padre) {
		super(padre);
		
		panelPrincipal = new VerticalPanel();
		
		Label lblTitulo = new Label("Caballos");

		panelPrincipal.add(lblTitulo);
		
		Listado<CaballoDTO> listado = new Listado<CaballoDTO>() {
			public String[] getAtributos(CaballoDTO obj) {
				return new String[] {
						obj.getNombre(),
						obj.getEdad().toString(),
						obj.getPeso().toString()
				};
			}

			public String[] getTitulos() {
				return new String[] {"Nombre", "Edad", "Peso"};
			}
		};
		panelPrincipal.add(listado);
	}
	
	public Widget toWidget() {
		return panelPrincipal;
	}
	
	@Override
	public void onMostrarABMCaballos() {
		super.onMostrarABMCaballos();
		this.mostrar();
	}
}
