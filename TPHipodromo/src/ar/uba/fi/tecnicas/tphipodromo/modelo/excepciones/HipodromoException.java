package ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones;

@SuppressWarnings("serial")
public abstract class HipodromoException extends Exception {
	
	private ImportanciaExceptions importancia;
	
	public HipodromoException(String msg) {
		super(msg);
	}

	public ImportanciaExceptions getImportancia() {
		return this.importancia;
	}

	public void setImportancia(ImportanciaExceptions importancia) {
		this.importancia = importancia;
	}

	public void visit(ExceptionVisitor visitor){
		visitor.visit(this);
	}
	
}