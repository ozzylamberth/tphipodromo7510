package edu.ar.uba.fi.exceptions;

/**
 * Visitor de Excepciones para pruebas.
 * @author Fernando E. Mansilla - 84567
 *
 */
public class ConsoleVisitor implements ExceptionVisitor {

	@Override
	public void visit(HipodromoException exception) {
		System.out.println(exception.getClass().getSimpleName() + " - "+exception.getImportancia());
		
	}

}
