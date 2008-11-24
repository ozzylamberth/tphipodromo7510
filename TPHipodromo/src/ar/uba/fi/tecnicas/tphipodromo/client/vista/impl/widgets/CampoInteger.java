package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets;

public class CampoInteger extends CampoString {
	
	public CampoInteger(boolean obligatorio) {
		super(obligatorio);
	}
	
	@Override
	public boolean validar() {
		if(!getValor().matches("[0123456789]*")) {
			return false;
		}
		
		return super.validar();
	}

}
