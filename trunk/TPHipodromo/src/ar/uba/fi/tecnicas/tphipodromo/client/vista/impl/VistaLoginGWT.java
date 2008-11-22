package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorLogin;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.VistaLogin;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Implementación en GWT de <code>VistaLogin</code>.
 * 
 * @see VistaLogin
 * @author Juan
 *
 */
public class VistaLoginGWT extends VistaGWT implements
		VistaLogin {
	
	/** Textbox donde se guarda el nombre de usuario. */
	private TextBox txtUsuario = new TextBox();
	
	/** Textbox donde se guarda el password. */
	private PasswordTextBox txtContrasenia = new PasswordTextBox();
	
	/** Panel principal de la vista. */
	private VerticalPanel pnlRaiz = new VerticalPanel();
	
	/**
	 * Constructor público. Crea los controles y paneles.
	 * 
	 * @param ctrlLogin Controlador de eventos de login.
	 */
	public VistaLoginGWT(final ControladorLogin ctrlLogin) {
	    Image imgTitulo = new Image("lock_48.png");
	    
	    Label lblTitulo = new Label("Login");
	    lblTitulo.addStyleName("lbl-large");
	    
	    HorizontalPanel pnlTitulo = new HorizontalPanel();
	    pnlTitulo.setVerticalAlignment(HorizontalPanel.ALIGN_BOTTOM);
	    pnlTitulo.setSpacing(10);
	    pnlTitulo.add(imgTitulo);
	    pnlTitulo.add(lblTitulo);
	    
	    Label lblUsuario = new Label("Usuario");
	    
	    Label lblContrasenia = new Label("Contraseña");
	    
	    Grid pnlForm = new Grid(2, 2);
	    pnlForm.setCellSpacing(5);
	    pnlForm.setWidget(0, 0, lblUsuario);
	    pnlForm.setWidget(0, 1, txtUsuario);
	    pnlForm.setWidget(1, 0, lblContrasenia);
	    pnlForm.setWidget(1, 1, txtContrasenia);
	    
	    final Button btnAceptar = new Button("Aceptar");
	    btnAceptar.addClickListener(new ClickListener() {
	    	public void onClick(Widget sender) {
	    		ctrlLogin.doVerificarDatos(txtUsuario.getText(),
	    				txtContrasenia.getText());
	    	}
	    });
	    
	    /* 
	     * Esto lo agregué medio oscuramente para que el form
	     * dispare el evento brtAceptar.click() cuando se teclea ENTER.
	     * TODO Ver una forma de hacerlo más genérico.
	     */
	    FocusPanel pnlFormFocus = new FocusPanel();
	    pnlFormFocus.add(pnlForm);
	    pnlFormFocus.addKeyboardListener(new KeyboardListenerAdapter() {
	    	@Override
	    	public void onKeyPress(Widget sender, char keyCode, int modifiers) {
	    		if (keyCode == 13) {
	    			btnAceptar.click();
	    		}
	    	}
	    });
	    
	    pnlRaiz.setWidth("100%");
	    pnlRaiz.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
	    pnlRaiz.setSpacing(20);
	    pnlRaiz.add(pnlTitulo);
	    pnlRaiz.add(pnlFormFocus);
	    pnlRaiz.add(btnAceptar);
	}
	
	public void onPedirDatosLogin() {
		History.newItem("login");
		txtUsuario.setText("");
		txtContrasenia.setText("");
		getRoot().clear();
		getRoot().add(pnlRaiz);
	}
	
	public void onDatosLoginIncorrectos() {
		Window.alert("Error en el login");
	}
	
	public void onDatosLoginCorrectos() {
		pnlRaiz.removeFromParent();
	}
	
	public Widget getWidgetPrincipal() {
		return null;
	}
}
