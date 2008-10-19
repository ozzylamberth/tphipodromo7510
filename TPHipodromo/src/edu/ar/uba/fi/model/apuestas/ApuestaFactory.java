package edu.ar.uba.fi.model.apuestas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ar.uba.fi.model.Carrera;
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

	public Apuesta crearApuestaGanador(Participante participante){
		ApuestaGanador apuestaGanador = new ApuestaGanador(participante);
		BolsaApuestas bolsaApuestas = this.getBolsaApuestas(TipoBolsaApuestas.BOLSA_APUESTAS_GANADOR, this.getCarrera(participante));
		apuestaGanador.setBolsaApuestas(bolsaApuestas);
		return apuestaGanador;
	}

	public Apuesta crearApuestaSegundo(Participante participante){
		ApuestaSegundo apuestaSegundo = new ApuestaSegundo(participante);
		BolsaApuestas bolsaApuestas = this.getBolsaApuestas(TipoBolsaApuestas.BOLSA_APUESTAS_SEGUNDO, this.getCarrera(participante));
		apuestaSegundo.setBolsaApuestas(bolsaApuestas);
		return apuestaSegundo;
	}

	public Apuesta crearApuestaTercero(Participante participante){
		ApuestaTercero apuestaTercero = new ApuestaTercero(participante);
		BolsaApuestas bolsaApuestas = this.getBolsaApuestas(TipoBolsaApuestas.BOLSA_APUESTAS_TERCERO, this.getCarrera(participante));
		apuestaTercero.setBolsaApuestas(bolsaApuestas);
		return apuestaTercero;
	}

	public Apuesta crearApuestaExacta(List<Participante> participantes){
		ApuestaExacta apuestaExacta = new ApuestaExacta(participantes);
		BolsaApuestas bolsaApuestas = this.getBolsaApuestas(TipoBolsaApuestas.BOLSA_APUESTAS_EXACTA, this.getCarreras(participantes));
		apuestaExacta.setBolsaApuestas(bolsaApuestas);
		return apuestaExacta;
	}

	public Apuesta crearApuestaImperfecta(List<Participante> participantes){
		ApuestaImperfecta apuestaImperfecta = new ApuestaImperfecta(participantes);
		BolsaApuestas bolsaApuestas = this.getBolsaApuestas(TipoBolsaApuestas.BOLSA_APUESTAS_IMPERFECTA, this.getCarreras(participantes));
		apuestaImperfecta.setBolsaApuestas(bolsaApuestas);
		return apuestaImperfecta;
	}

	public Apuesta crearApuestaTrifecta(List<Participante> participantes){
		ApuestaTrifecta apuestaTrifecta = new ApuestaTrifecta(participantes);
		BolsaApuestas bolsaApuestas = this.getBolsaApuestas(TipoBolsaApuestas.BOLSA_APUESTAS_TRIFECTA, this.getCarreras(participantes));
		apuestaTrifecta.setBolsaApuestas(bolsaApuestas);
		return apuestaTrifecta;
	}

	public Apuesta crearApuestaDoble(List<Participante> participantes){
		ApuestaDoble apuestaDoble = new ApuestaDoble(participantes);
		BolsaApuestas bolsaApuestas = this.getBolsaApuestas(TipoBolsaApuestas.BOLSA_APUESTAS_DOBLE, this.getCarreras(participantes));
		apuestaDoble.setBolsaApuestas(bolsaApuestas);
		return apuestaDoble;
	}

	public Apuesta crearApuestaTriplo(List<Participante> participantes){
		ApuestaTriplo apuestaTriplo = new ApuestaTriplo(participantes);
		BolsaApuestas bolsaApuestas = this.getBolsaApuestas(TipoBolsaApuestas.BOLSA_APUESTAS_TRIPLO, this.getCarreras(participantes));
		apuestaTriplo.setBolsaApuestas(bolsaApuestas);
		return apuestaTriplo;
	}

	public Apuesta crearApuestaCuaterna(List<Participante> participantes){
		ApuestaCuaterna apuestaCuaterna = new ApuestaCuaterna(participantes);
		BolsaApuestas bolsaApuestas = this.getBolsaApuestas(TipoBolsaApuestas.BOLSA_APUESTAS_CUATERNA, this.getCarreras(participantes));
		apuestaCuaterna.setBolsaApuestas(bolsaApuestas);
		return apuestaCuaterna;
	}
	
	private List<Carrera> getCarrera(Participante participante) {
		ArrayList<Carrera> carreras = new ArrayList<Carrera>();
		carreras.add(participante.getCarrera());
		return carreras;
	}
	
	private List<Carrera> getCarreras(List<Participante> participantes) {
		ArrayList<Carrera> carreras = new ArrayList<Carrera>();
		Iterator<Participante> it = participantes.iterator();
		while (it.hasNext()) {
			Participante participante = (Participante) it.next();
			carreras.add(participante.getCarrera());
		}
		return carreras;
	}
	
	private BolsaApuestas getBolsaApuestas(TipoBolsaApuestas tipoBolsaApuestas, List<Carrera> carreras) {
		return BolsasApuestasManager.getInstance().getBolsaApuestas(tipoBolsaApuestas, carreras);
	}

}