package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import edu.ar.uba.fi.model.Carrera;

/**
 * Contiene todas las apuestas del mismo tipo que se realizaron sobre una
 * carrera o conjunto de carreras
 */
public class BolsaApuestas {
	public static final int DECIMALES = 2;
	public static final RoundingMode ROUNDING_MODE = RoundingMode.CEILING;
	
	private Class<? extends Apuesta> tipoBolsaApuestas;
	private Set<Carrera> carreras = new HashSet<Carrera>();
	private ArrayList<Apuesta> apuestas = new ArrayList<Apuesta>();
	private BigDecimal porcentajeComisionHipodromo = new BigDecimal(0);

	public BolsaApuestas(Class<? extends Apuesta> tipoBolsaApuestas, Set<Carrera> carreras) {
		this.setTipoBolsaApuestas(tipoBolsaApuestas);
		this.setCarreras(carreras);
	}

	public BigDecimal getDividendo() {
		BigDecimal totalApostado = new BigDecimal(0);
		BigDecimal totalGanadores = new BigDecimal(0);
		Iterator<Apuesta> it = this.apuestas.iterator();
		while (it.hasNext()) {
			Apuesta apuesta = (Apuesta) it.next();
			totalApostado = totalApostado.add(apuesta.getMontoApostado());
			if (apuesta.isAcertada()) {
				totalGanadores = totalGanadores.add(apuesta.getMontoApostado());
			}
		}
		BigDecimal porcentajeARepartir = new BigDecimal(1).subtract(this.getPorcentajeComisionHipodromo());
		BigDecimal totalARepartir = totalApostado.multiply(porcentajeARepartir);
		BigDecimal dividendo = totalARepartir.divide(totalGanadores, DECIMALES, ROUNDING_MODE);
		// si el dividendo de menor que uno, se retorna 1
		if (new BigDecimal(1).compareTo(dividendo) > 0) {
			return new BigDecimal(1);
		} else {
			return dividendo;
		}
	}
	
	public boolean correspondeACarreras(Set<Carrera> carreras) {
		return carreras.equals(getCarreras());
	}

	public Class<? extends Apuesta> getTipoBolsaApuestas() {
		return tipoBolsaApuestas;
	}

	public void setTipoBolsaApuestas(Class<? extends Apuesta> tipoBolsaApuestas) {
		this.tipoBolsaApuestas = tipoBolsaApuestas;
	}

	public Set<Carrera> getCarreras() {
		return carreras;
	}

	public void setCarreras(Set<Carrera> carreras) {
		this.carreras = carreras;
	}

	public ArrayList<Apuesta> getApuestas() {
		return this.apuestas;
	}

	public void setApuestas(ArrayList<Apuesta> apuestas) {
		this.apuestas = apuestas;
	}

	public void addApuesta(Apuesta apuesta) {
		// TODO: validar que todas las apuestas sean del tipo correspondiente
		apuesta.setBolsaApuestas(this);
		this.apuestas.add(apuesta);
	}

	public BigDecimal getPorcentajeComisionHipodromo() {
		return this.porcentajeComisionHipodromo;
	}

	public void setPorcentajeComisionHipodromo(
			BigDecimal porcentajeComisionHipodromo) {
		this.porcentajeComisionHipodromo = porcentajeComisionHipodromo;
	}
	
}