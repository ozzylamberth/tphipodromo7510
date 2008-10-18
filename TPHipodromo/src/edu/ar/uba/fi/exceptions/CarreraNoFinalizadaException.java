package edu.ar.uba.fi.exceptions;

public class CarreraNoFinalizadaException extends CarreraException {

	public CarreraNoFinalizadaException(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}