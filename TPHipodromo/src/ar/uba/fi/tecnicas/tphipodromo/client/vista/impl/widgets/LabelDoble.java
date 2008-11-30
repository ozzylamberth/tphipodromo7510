package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
/**
 * No es mas que un panel que tiene:
 * 		variable: valor
 * @author El Tolo
 *
 */
public class LabelDoble extends SimplePanel {
	
	private Label labelVariable;
	
	private Label labelValor;

	public LabelDoble(String variable, String valor){
		super();
		HorizontalPanel panel = new HorizontalPanel();
		labelValor = new Label(variable + ": ");
		labelValor.setStyleName("label_valor");
		labelVariable = new Label(valor);
		labelVariable.setStyleName("label_variable");
		panel.add(labelVariable);
		panel.add(labelValor);
	}
	
	public void setVariable(String variable) {
		this.labelVariable.setText(variable + ": ");
	}
	
	public void setValor(String valor) {
		this.labelValor.setText(valor);
	}
}
