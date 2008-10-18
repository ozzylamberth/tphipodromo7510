package edu.ar.uba.fi.model.apuestas;

import edu.ar.uba.fi.model.Participante;

/**
 * Se encarga de la creacion de los distintos tipos de apuestas. Antes de
 * retornar una Apuesta, le asigna su correspondiente Bolsa de Apuestas
 */
public class ApuestaFactory {
	private static final ApuestaFactory instance = new ApuestaFactory();

	public ApuestaFactory() {
	}

	public static ApuestaFactory getInstance() {
		return instance;
	}

	public Apuesta crearApuesta(Participante[] participantes) {
		// TODO: implementar logica
		return null;
	}

}