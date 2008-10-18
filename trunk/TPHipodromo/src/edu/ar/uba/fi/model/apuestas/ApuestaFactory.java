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

	public Apuesta crearApuestaGanador(Participante Participante){
		// TODO: implementar logica
		return null;
	}

	public Apuesta crearApuestaSegundo(Participante Participante){
		// TODO: implementar logica
		return null;
	}

	public Apuesta crearApuestaTercero(Participante Participante){
		// TODO: implementar logica
		return null;
	}

	public Apuesta crearApuestaExacta(Participante Participante){
		// TODO: implementar logica
		return null;
	}

	public Apuesta crearApuestaImperfecta(Participante Participante){
		// TODO: implementar logica
		return null;
	}

	public Apuesta crearApuestaTrifecta(Participante Participante){
		// TODO: implementar logica
		return null;
	}

	public Apuesta crearApuestaDoble(Participante[] participantes){
		// TODO: implementar logica
		return null;
	}

	public Apuesta crearApuestaTriplo(Participante[] participantes){
		// TODO: implementar logica
		return null;
	}

	public Apuesta crearApuestaCuaterna(Participante[] participantes){
		// TODO: implementar logica
		return null;
	}

}