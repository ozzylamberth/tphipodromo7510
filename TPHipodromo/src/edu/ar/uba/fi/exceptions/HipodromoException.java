package edu.ar.uba.fi.exceptions;


public abstract class HipodromoException extends Exception {
	
		
	private ImportanciaExceptions importancia;

	public ImportanciaExceptions getImportancia() {
		return this.importancia;
	}

	public void setImportancia(ImportanciaExceptions importancia) {
		this.importancia = importancia;
	}

	public void visit(VisitorException visitor){
		visitor.visit(this);
	}
	
}