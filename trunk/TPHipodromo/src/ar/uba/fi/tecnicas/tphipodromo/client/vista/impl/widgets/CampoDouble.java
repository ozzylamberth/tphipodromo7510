package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets;

public class CampoDouble extends CampoString {

	public CampoDouble(boolean obligatorio) {
		super(obligatorio);
	}
	
	@Override
	public boolean validar() {
		if(!this.getValor().equals("")) {
			try {
				new Double(this.getValor());
			}catch(NumberFormatException e) {
				return false;
			}
		}
		return super.validar();
	}

}
