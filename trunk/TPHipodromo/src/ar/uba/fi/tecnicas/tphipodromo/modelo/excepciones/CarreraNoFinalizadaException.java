package ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones;

//Suppress warnings about missing serialVersionUID
@SuppressWarnings("serial")
public class CarreraNoFinalizadaException extends CarreraException {

	public CarreraNoFinalizadaException(){
		super("La carrera no finalizo");
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}