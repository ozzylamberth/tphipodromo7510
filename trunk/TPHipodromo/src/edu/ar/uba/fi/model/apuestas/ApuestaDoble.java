package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import edu.ar.uba.fi.exceptions.CantidadParticipantesInvalidaException;
import edu.ar.uba.fi.exceptions.CarreraCerradaAApuestasException;
import edu.ar.uba.fi.model.Participante;

/**
 * Representa las apuestas del tipo Doble, en donde el jugador debe seleccionar
 * 2 caballos para apostar, que deberan llegar primero en 2 carreras
 * consecutivas para ganar la apuesta
 */
public class ApuestaDoble extends Apuesta
{

	public ApuestaDoble()
	{
		super();
	}

	public ApuestaDoble(List<Participante> participantes) throws CantidadParticipantesInvalidaException, CarreraCerradaAApuestasException
	{
		super(participantes);
	}

	public int getCantidadParticipantes()
	{
		return 2;
	}

	public BigDecimal getValorBase()
	{
		return new BigDecimal(2);
	}

	public List<Integer> getPosiblesOrdenesLLegada()
	{
		ArrayList<Integer> ordenesLlegada = new ArrayList<Integer>();
		ordenesLlegada.add(new Integer(1));
		return ordenesLlegada;
	}

}