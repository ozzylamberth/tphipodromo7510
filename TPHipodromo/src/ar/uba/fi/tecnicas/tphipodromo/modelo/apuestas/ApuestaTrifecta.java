package ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Participante;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.CantidadParticipantesInvalidaException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.CarreraCerradaAApuestasException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ParticipantesEnDistintasCarrerasException;


/**
 * Representa las apuestas del tipo Trifecta, en donde el jugador debe
 * seleccionar 3 caballos para apostar en una misma carrera, que debera llegar
 * primero, segundo y tercero en orden exacto para ganar la apuesta
 */
public class ApuestaTrifecta extends ApuestaPorPosicionesOrdenadas {
	
	public ApuestaTrifecta() {
		super(TipoApuesta.TRIFECTA);
	}
	
	/** TODO Eliminar este c�digo duplicado en ApuestaExacta, ApuestaTrifecta y ApuestaImperfecta */
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