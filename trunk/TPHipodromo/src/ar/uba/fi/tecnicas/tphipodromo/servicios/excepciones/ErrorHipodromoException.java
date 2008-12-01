package ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class ErrorHipodromoException extends Exception implements IsSerializable {
	
	public ErrorHipodromoException() {
		super("Se produjo un error inesperado en el hipodromo");
	}
	
	public ErrorHipodromoException(String msg) {
		super(msg);
	}
}
