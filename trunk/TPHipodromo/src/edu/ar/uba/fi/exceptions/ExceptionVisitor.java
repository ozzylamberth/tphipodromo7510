package edu.ar.uba.fi.exceptions;

/**
 * @author Fernando E. Mansilla - 84567
 */
public interface ExceptionVisitor {

	public void visit(HipodromoException exception);
	
}