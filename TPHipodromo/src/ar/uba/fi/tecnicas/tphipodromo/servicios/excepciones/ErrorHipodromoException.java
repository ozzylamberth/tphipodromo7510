package ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones;

@SuppressWarnings("serial")
public class ErrorHipodromoException extends Exception {
	
	public ErrorHipodromoException() {
		super("Se produjo un error inesperado en el hipodromo");
	}
}
