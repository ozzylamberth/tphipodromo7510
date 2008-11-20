package ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones;

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
