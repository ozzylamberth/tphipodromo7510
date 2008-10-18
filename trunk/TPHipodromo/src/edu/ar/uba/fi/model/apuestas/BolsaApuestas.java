package edu.ar.uba.fi.model.apuestas;

import java.util.ArrayList;

import edu.ar.uba.fi.model.Carrera;

/**
 * Contiene todas las apuestas del mismo tipo que se realizaron sobre una
 * carrera o conjunto de carreras
 */
public class BolsaApuestas {
	private ArrayList<Carrera> carreras = new ArrayList<Carrera>();
	public ArrayList<Apuesta> apuestas;

	public BolsaApuestas(ArrayList<Carrera> carreras) {
		this.carreras = carreras;
	}

	public double getDividendo() {
		// TODO: implementar logica
		return 0;
	}

	public ArrayList<Carrera> getCarreras() {
		return this.carreras;
	}

	public void setCarreras(ArrayList<Carrera> carreras) {
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
		this.apuestas.add(apuesta);
	}

}