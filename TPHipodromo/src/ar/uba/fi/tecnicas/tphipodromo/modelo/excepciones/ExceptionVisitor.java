package ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones;

/**
 * @author Fernando E. Mansilla - 84567
 */
public interface ExceptionVisitor {

	public void visit(HipodromoException exception);
	
}
