package ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones;

@SuppressWarnings("serial")
public class ImposibleFabricarApuestaException extends ApuestaException {
	
	public ImposibleFabricarApuestaException() {
		
	}
	
	public ImposibleFabricarApuestaException(Throwable cause) {
		this.initCause(cause);
	}

}
