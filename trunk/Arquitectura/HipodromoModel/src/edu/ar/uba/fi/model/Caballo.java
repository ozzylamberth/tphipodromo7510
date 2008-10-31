package edu.ar.uba.fi.model;

import java.math.BigDecimal;

public class Caballo {
	private String caballeriza;
	private String criador;
	private int edad;
	private String nombre;
	private String pelaje;
	private BigDecimal peso;
	private boolean puraSangre;
	private Caballo padre;
	private Caballo madre;

	public String getCaballeriza() {
		return this.caballeriza;
	}

	public void setCaballeriza(String caballeriza) {
		this.caballeriza = caballeriza;
	}

	public String getCriador() {
		return this.criador;
	}

	public void setCriador(String criador) {
		this.criador = criador;
	}

	public int getEdad() {
		return this.edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPelaje() {
		return this.pelaje;
	}

	public void setPelaje(String pelaje) {
		this.pelaje = pelaje;
	}

	public BigDecimal getPeso() {
		return this.peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public boolean isPuraSangre() {
		return this.puraSangre;
	}

	public void setPuraSangre(boolean puraSangre) {
		this.puraSangre = puraSangre;
	}

	public Caballo getPadre() {
		return this.padre;
	}

	public void setPadre(Caballo padre) {
		this.padre = padre;
	}

	public Caballo getMadre() {
		return this.madre;
	}

	public void setMadre(Caballo madre) {
		this.madre = madre;
	}

}