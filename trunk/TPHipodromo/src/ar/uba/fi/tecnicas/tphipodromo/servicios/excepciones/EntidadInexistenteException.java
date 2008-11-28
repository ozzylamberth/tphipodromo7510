package ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class EntidadInexistenteException extends Exception implements IsSerializable {
	
	public EntidadInexistenteException() {
		super("La entidad buscada no existe");
	}
	
}
