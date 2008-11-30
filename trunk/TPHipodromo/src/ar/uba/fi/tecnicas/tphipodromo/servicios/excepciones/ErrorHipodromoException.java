package ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ErrorHipodromoException extends Exception implements Serializable {
	
	public ErrorHipodromoException() {
		super("Se produjo un error inesperado en el hipodromo");
	}
}
