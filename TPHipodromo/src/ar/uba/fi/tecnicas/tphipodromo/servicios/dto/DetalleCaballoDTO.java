package ar.uba.fi.tecnicas.tphipodromo.servicios.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DetalleCaballoDTO implements IsSerializable {
	private String caballeriza;
	
	private String criador;
	
	private int edad;
	
	private String nombre;
	
	private String pelaje;
	
	private double peso;
	
	private boolean puraSangre;
	
	private String padre;
	
	private String madre;

	public String getCaballeriza() {
		return caballeriza;
	}

	public void setCaballeriza(String caballeriza) {
		this.caballeriza = caballeriza;
	}

	public String getCriador() {
		return criador;
	}

	public void setCriador(String criador) {
		this.criador = criador;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPelaje() {
		return pelaje;
	}

	public void setPelaje(String pelaje) {
		this.pelaje = pelaje;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public boolean isPuraSangre() {
		return puraSangre;
	}

	public void setPuraSangre(boolean puraSangre) {
		this.puraSangre = puraSangre;
	}

	public String getPadre() {
		return padre;
	}

	public void setPadre(String padre) {
		this.padre = padre;
	}

	public String getMadre() {
		return madre;
	}

	public void setMadre(String madre) {
		this.madre = madre;
	}
	
}
