package edu.ar.uba.fi.model.apuestas;

import java.util.Iterator;
import java.util.List;

import edu.ar.uba.fi.exceptions.CantidadParticipantesInvalidaException;
import edu.ar.uba.fi.exceptions.CarreraCerradaAApuestasException;
import edu.ar.uba.fi.model.Participante;

/**
 * Representa a las apuestas que son ganadas cuando los participantes
 * llegan en determinadas posiciones teniendo en cuenta el orden de
 * los mismos. Es decir, el primer participante tiene que salir en la
 * primera posicion determinada, el segundo en la segunda, y asi
 * sucesivamente
 */
public abstract class ApuestaPorPosicionesOrdenadas extends Apuesta
{

	public ApuestaPorPosicionesOrdenadas()
	{
	}

	public ApuestaPorPosicionesOrdenadas(List<Participante> participantes) throws CantidadParticipantesInvalidaException, CarreraCerradaAApuestasException
	{
		super(participantes);
		if (participantes.size() != this.getCantidadParticipantes())
		{
			throw new CantidadParticipantesInvalidaException();
		}
	}

	/**
	 * Se sobreescribe el metodo isAcertada de la clase Apuesta para que
	 * se consideren los posibles ordenes de llegada en el orden exacto
	 */
	public boolean isAcertada()
	{
		int ordenLLegadaIndex = 0;
		Iterator<Participante> it = this.getParticipantes().iterator();
		while (it.hasNext())
		{
			Participante participante = (Participante)it.next();
			int ordenLLegada = participante.getResultado().getOrdenLlegada();
			if (!this.getPosiblesOrdenesLLegada().get(ordenLLegadaIndex).equals(new Integer(ordenLLegada)))
			{
				return false;
			}
			ordenLLegadaIndex++;
		}
		return true;
	}

}
