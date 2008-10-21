package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import edu.ar.uba.fi.exceptions.CarreraCerradaAApuestasException;
import edu.ar.uba.fi.model.Participante;

/**
 * Representa las apuestas del tipo Tercero, en donde el jugador debe
 * seleccionar 1 caballo para apostar en una carrera, que debera llegar en
 * primer, segundo o tercer lugar para ganar la apuesta
 */
public class ApuestaTercero extends Apuesta
{

	public ApuestaTercero()
	{
		super();
	}

	public ApuestaTercero(Participante participante) throws CarreraCerradaAApuestasException
	{
		super();
		ArrayList<Participante> participantes = new ArrayList<Participante>();
		participantes.add(participante);
		this.setParticipantes(participantes);
	}

	public int getCantidadParticipantes()
	{
		return 1;
	}

	public BigDecimal getValorBase()
	{
		return new BigDecimal(1);
	}

	public List<Integer> getPosiblesOrdenesLLegada()
	{
		ArrayList<Integer> ordenesLlegada = new ArrayList<Integer>();
		ordenesLlegada.add(new Integer(1));
		ordenesLlegada.add(new Integer(2));
		ordenesLlegada.add(new Integer(3));
		return ordenesLlegada;
	}

}