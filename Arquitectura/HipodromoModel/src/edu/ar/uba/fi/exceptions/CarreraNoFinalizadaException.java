package edu.ar.uba.fi.exceptions;

//Suppress warnings about missing serialVersionUID
@SuppressWarnings("serial")
public class CarreraNoFinalizadaException extends CarreraException {

	public CarreraNoFinalizadaException(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}