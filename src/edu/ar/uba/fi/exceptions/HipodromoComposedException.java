package edu.ar.uba.fi.exceptions;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HipodromoComposedException extends HipodromoException {

	private static final long serialVersionUID = 5894673280365057096L;
	List<HipodromoException> excepciones = new LinkedList<HipodromoException>();

	public void add(HipodromoException e) {
		excepciones.add(e);
	}

	@Override
	public void printStackTrace() {
		Iterator<HipodromoException> it = excepciones.iterator();
		int conteo = 1;
		while (it.hasNext()) {
			System.out.println("Excepcion " + conteo);
			it.next().printStackTrace();
		}
	}

	@Override
	public void visit(ExceptionVisitor visitor) {
		Iterator<HipodromoException> it = excepciones.iterator();
		while (it.hasNext()) {
			it.next().visit(visitor);
		}
	}

	public boolean hasExceptions() {
		return !excepciones.isEmpty();
	}

}
