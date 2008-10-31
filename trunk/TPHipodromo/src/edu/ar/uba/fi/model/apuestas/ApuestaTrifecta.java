package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import edu.ar.uba.fi.exceptions.CantidadParticipantesInvalidaException;
import edu.ar.uba.fi.exceptions.CarreraCerradaAApuestasException;
import edu.ar.uba.fi.exceptions.ParticipantesEnDistintasCarrerasException;
import edu.ar.uba.fi.model.Carrera;
import edu.ar.uba.fi.model.Participante;

/**
 * Representa las apuestas del tipo Trifecta, en donde el jugador debe
 * seleccionar 3 caballos para apostar en una misma carrera, que debera llegar
 * primero, segundo y tercero en orden exacto para ganar la apuesta
 */
public class ApuestaTrifecta extends ApuestaPorPosicionesOrdenadas {
	
	/** TODO Eliminar este código duplicado en ApuestaExacta, ApuestaTrifecta y ApuestaImperfecta */
	private void validarMismaCarrera(Collection<Participante> participantes) throws ParticipantesEnDistintasCarrerasException {
		Carrera anterior = null;
		Iterator<Participante> it = participantes.iterator();
		
		if(it.hasNext()) {
			anterior = it.next().getCarrera();
		}
		
		while(it.hasNext()) {
			Participante participante = it.next();
			
			if( !participante.getCarrera().equals(anterior)) {
				throw new ParticipantesEnDistintasCarrerasException();
			}
			
			anterior = participante.getCarrera();
		}
	}

	public int getCantidadParticipantes() {
		return 3;
	}

	public BigDecimal getValorBase() {
		return new BigDecimal(1);
	}

	public List<Integer> getPosiblesOrdenesLLegada() {
		ArrayList<Integer> ordenesLlegada = new ArrayList<Integer>();
		ordenesLlegada.add(new Integer(1));
		ordenesLlegada.add(new Integer(2));
		ordenesLlegada.add(new Integer(3));
		return ordenesLlegada;
	}

	@Override
	public void setParticipantes(Collection<Participante> participantes)
			throws CantidadParticipantesInvalidaException,
			CarreraCerradaAApuestasException,
			ParticipantesEnDistintasCarrerasException {
		this.validarMismaCarrera(participantes);
		super.setParticipantes(participantes);
	}
}