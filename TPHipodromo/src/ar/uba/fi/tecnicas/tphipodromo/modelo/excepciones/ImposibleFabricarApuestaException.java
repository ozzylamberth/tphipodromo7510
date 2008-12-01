package ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones;

@SuppressWarnings("serial")
public class ImposibleFabricarApuestaException extends ApuestaException {
	
	public ImposibleFabricarApuestaException() {
		super("");
	}
	
	public ImposibleFabricarApuestaException(Throwable cause) {
		super("");
		this.initCause(cause);
	}

}
