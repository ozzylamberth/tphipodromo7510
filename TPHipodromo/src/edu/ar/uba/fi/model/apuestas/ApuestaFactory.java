package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ar.uba.fi.exceptions.CantidadParticipantesInvalidaException;
import edu.ar.uba.fi.exceptions.CarreraCerradaAApuestasException;
import edu.ar.uba.fi.exceptions.ParticipantesEnDistintasCarrerasException;
import edu.ar.uba.fi.model.Carrera;
import edu.ar.uba.fi.model.Participante;

/**
 * Se encarga de la creacion de los distintos tipos de apuestas. Antes de
 * retornar una Apuesta, le asigna su correspondiente Bolsa de Apuestas
 */
public class ApuestaFactory
{
	private static final ApuestaFactory instance = new ApuestaFactory();

	public ApuestaFactory()
	{
	}

	public static ApuestaFactory getInstance()
	{
		return instance;
	}

	public Apuesta crearApuestaGanador(Participante participante, BigDecimal montoApostado) throws CarreraCerradaAApuestasException
	{
		ApuestaGanador apuestaGanador = new ApuestaGanador(participante);
		apuestaGanador.setMontoApostado(montoApostado);
		this.addApuestaToBolsaApuestas(apuestaGanador, TipoBolsaApuestas.BOLSA_APUESTAS_GANADOR, this.getCarrera(participante));
		return apuestaGanador;
	}

	public Apuesta crearApuestaSegundo(Participante participante, BigDecimal montoApostado) throws CarreraCerradaAApuestasException
	{
		ApuestaSegundo apuestaSegundo = new ApuestaSegundo(participante);
		apuestaSegundo.setMontoApostado(montoApostado);
		this.addApuestaToBolsaApuestas(apuestaSegundo, TipoBolsaApuestas.BOLSA_APUESTAS_SEGUNDO, this.getCarrera(participante));
		return apuestaSegundo;
	}

	public Apuesta crearApuestaTercero(Participante participante, BigDecimal montoApostado) throws CarreraCerradaAApuestasException
	{
		ApuestaTercero apuestaTercero = new ApuestaTercero(participante);
		apuestaTercero.setMontoApostado(montoApostado);
		this.addApuestaToBolsaApuestas(apuestaTercero, TipoBolsaApuestas.BOLSA_APUESTAS_TERCERO, this.getCarrera(participante));
		return apuestaTercero;
	}

	public Apuesta crearApuestaExacta(List<Participante> participantes, BigDecimal montoApostado) throws CantidadParticipantesInvalidaException, ParticipantesEnDistintasCarrerasException, CarreraCerradaAApuestasException
	{
		ApuestaExacta apuestaExacta = new ApuestaExacta(participantes);
		apuestaExacta.setMontoApostado(montoApostado);
		this.addApuestaToBolsaApuestas(apuestaExacta, TipoBolsaApuestas.BOLSA_APUESTAS_EXACTA, this.getCarreras(participantes));
		return apuestaExacta;
	}

	public Apuesta crearApuestaImperfecta(List<Participante> participantes, BigDecimal montoApostado) throws CantidadParticipantesInvalidaException, ParticipantesEnDistintasCarrerasException, CarreraCerradaAApuestasException
	{
		ApuestaImperfecta apuestaImperfecta = new ApuestaImperfecta(participantes);
		apuestaImperfecta.setMontoApostado(montoApostado);
		this.addApuestaToBolsaApuestas(apuestaImperfecta, TipoBolsaApuestas.BOLSA_APUESTAS_IMPERFECTA, this.getCarreras(participantes));
		return apuestaImperfecta;
	}

	public Apuesta crearApuestaTrifecta(List<Participante> participantes, BigDecimal montoApostado) throws CantidadParticipantesInvalidaException, ParticipantesEnDistintasCarrerasException, CarreraCerradaAApuestasException
	{
		ApuestaTrifecta apuestaTrifecta = new ApuestaTrifecta(participantes);
		apuestaTrifecta.setMontoApostado(montoApostado);
		this.addApuestaToBolsaApuestas(apuestaTrifecta, TipoBolsaApuestas.BOLSA_APUESTAS_TRIFECTA, this.getCarreras(participantes));
		return apuestaTrifecta;
	}

	public Apuesta crearApuestaDoble(List<Participante> participantes, BigDecimal montoApostado) throws CantidadParticipantesInvalidaException, CarreraCerradaAApuestasException
	{
		ApuestaDoble apuestaDoble = new ApuestaDoble(participantes);
		apuestaDoble.setMontoApostado(montoApostado);
		this.addApuestaToBolsaApuestas(apuestaDoble, TipoBolsaApuestas.BOLSA_APUESTAS_DOBLE, this.getCarreras(participantes));
		return apuestaDoble;
	}

	public Apuesta crearApuestaTriplo(List<Participante> participantes, BigDecimal montoApostado) throws CantidadParticipantesInvalidaException, CarreraCerradaAApuestasException
	{
		ApuestaTriplo apuestaTriplo = new ApuestaTriplo(participantes);
		apuestaTriplo.setMontoApostado(montoApostado);
		this.addApuestaToBolsaApuestas(apuestaTriplo, TipoBolsaApuestas.BOLSA_APUESTAS_TRIPLO, this.getCarreras(participantes));
		return apuestaTriplo;
	}

	public Apuesta crearApuestaCuaterna(List<Participante> participantes, BigDecimal montoApostado) throws CantidadParticipantesInvalidaException, CarreraCerradaAApuestasException
	{
		ApuestaCuaterna apuestaCuaterna = new ApuestaCuaterna(participantes);
		apuestaCuaterna.setMontoApostado(montoApostado);
		this.addApuestaToBolsaApuestas(apuestaCuaterna, TipoBolsaApuestas.BOLSA_APUESTAS_CUATERNA, this.getCarreras(participantes));
		return apuestaCuaterna;
	}

	private List<Carrera> getCarrera(Participante participante)
	{
		ArrayList<Carrera> carreras = new ArrayList<Carrera>();
		carreras.add(participante.getCarrera());
		return carreras;
	}

	private List<Carrera> getCarreras(List<Participante> participantes)
	{
		ArrayList<Carrera> carreras = new ArrayList<Carrera>();
		Iterator<Participante> it = participantes.iterator();
		while (it.hasNext())
		{
			Participante participante = (Participante)it.next();
			carreras.add(participante.getCarrera());
		}
		return carreras;
	}

	private void addApuestaToBolsaApuestas(Apuesta apuesta, TipoBolsaApuestas tipoBolsaApuestas, List<Carrera> carreras)
	{
		BolsaApuestas bolsaApuestas = BolsasApuestasManager.getInstance().getBolsaApuestas(tipoBolsaApuestas, carreras);
		bolsaApuestas.addApuesta(apuesta);
	}

}