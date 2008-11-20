package edu.ar.uba.fi.exceptions;

@SuppressWarnings("serial")
public class ImposibleFabricarApuestaException extends ApuestaException {
	
	public ImposibleFabricarApuestaException() {
		
	}
	
	public ImposibleFabricarApuestaException(Throwable cause) {
		this.initCause(cause);
	}

}
