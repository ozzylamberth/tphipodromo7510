package edu.ar.uba.fi.exceptions;

//Suppress warnings about missing serialVersionUID
@SuppressWarnings("serial")
public abstract class HipodromoException extends Exception {
	private ImportanciaExceptions importancia;

	public ImportanciaExceptions getImportancia() {
		return this.importancia;
	}

	public void setImportancia(ImportanciaExceptions importancia) {
		this.importancia = importancia;
	}

}