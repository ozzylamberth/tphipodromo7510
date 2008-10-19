package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ar.uba.fi.model.Carrera;

/**
 * Contiene todas las apuestas del mismo tipo que se realizaron sobre una
 * carrera o conjunto de carreras
 */
public class BolsaApuestas {
	private TipoBolsaApuestas tipoBolsaApuestas;
	private List<Carrera> carreras = new ArrayList<Carrera>();
	public ArrayList<Apuesta> apuestas = new ArrayList<Apuesta>();

	public BolsaApuestas(TipoBolsaApuestas tipoBolsaApuestas, List<Carrera> carreras) {
		this.setTipoBolsaApuestas(tipoBolsaApuestas);
		this.setCarreras(carreras);
	}

	public BigDecimal getDividendo() {
		// TODO: implementar logica
		return new BigDecimal(0);
	}
	
	public boolean correspondeACarreras(List<Carrera> carreras) {
		if (this.getCarreras().size() != carreras.size()) {
			return false;
		}
		Iterator<Carrera> it = carreras.iterator();
		while (it.hasNext()) {
			Carrera carrera = (Carrera) it.next();
			if (!this.getCarreras().contains(carrera)) {
				return false;
			}
		}
		return true;
	}

	public TipoBolsaApuestas getTipoBolsaApuestas() {
		return this.tipoBolsaApuestas;
	}

	public void setTipoBolsaApuestas(TipoBolsaApuestas tipoBolsaApuestas) {
		this.tipoBolsaApuestas = tipoBolsaApuestas;
	}

	public List<Carrera> getCarreras() {
		return this.carreras;
	}

	public void setCarreras(List<Carrera> carreras) {
		this.carreras = carreras;
	}

	public ArrayList<Apuesta> getApuestas() {
		return this.apuestas;
	}

	public void setApuestas(ArrayList<Apuesta> apuestas) {
		this.apuestas = apuestas;
	}

	public void addApuesta(Apuesta apuesta) {
		// TODO: validar que todas las apuestas sean de un mismo tipo
		apuesta.setBolsaApuestas(this);
		this.apuestas.add(apuesta);
	}

}