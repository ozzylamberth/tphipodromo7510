package ar.uba.fi.tecnicas.tphipodromo.client.vista;

/**
 * Vista para solicitarle al usuario los datos de login.
 * 
 * @author Juan
 *
 */
public interface VistaLogin extends Vista {
	
	/** Evento disparado cuando se desean pedir los datos de login al usuario. */
	public void onPedirDatosLogin();
	
	/** Evento disparado cuando el login se concretó satisfactoriamente. */
	public void onDatosLoginCorrectos();
	
	/** Evento disparado cuando ocurre algún error en el proceso de login. */
	public void onDatosLoginIncorrectos();

}
