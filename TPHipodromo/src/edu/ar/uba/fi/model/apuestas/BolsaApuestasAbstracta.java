package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

import edu.ar.uba.fi.exceptions.TipoApuestaInvalidoException;
import edu.ar.uba.fi.model.Carrera;

/**
 * Contiene todas las apuestas del mismo tipo que se realizaron sobre una
 * carrera o conjunto de carreras
 */
public abstract class BolsaApuestasAbstracta {
	public static final int DECIMALES = 2;
	public static final RoundingMode ROUNDING_MODE = RoundingMode.CEILING;
	
	protected Class<? extends Apuesta> tipoApuestas;
	protected Set<Carrera> carreras = new HashSet<Carrera>();
	protected Set<Apuesta> apuestas = new HashSet<Apuesta>();
	protected BigDecimal porcentajeComisionHipodromo;

	public BolsaApuestasAbstracta() {
	}

	public abstract BigDecimal getDividendo();
	
	public boolean correspondeACarreras(Set<Carrera> carreras) {
		return carreras.equals(getCarreras());
	}

	public Class<? extends Apuesta> getTipoBolsaApuestas() {
		return tipoApuestas;
	}

	protected void setTipoBolsaApuestas(Class<? extends Apuesta> tipoBolsaApuestas) {
		this.tipoApuestas = tipoBolsaApuestas;
	}

	public Set<Carrera> getCarreras() {
		return carreras;
	}

	protected void setCarreras(Set<Carrera> carreras) {
		this.carreras = carreras;
	}

	public Set<Apuesta> getApuestas() {
		return this.apuestas;
	}

	public void setApuestas(Set<Apuesta> apuestas) {
		this.apuestas = apuestas;
	}

	public void addApuesta(Apuesta apuesta) throws TipoApuestaInvalidoException {
		if(apuesta.getClass().equals(tipoApuestas)){
			apuesta.setBolsaApuestas(this);
			this.apuestas.add(apuesta);
		}else{
			throw new TipoApuestaInvalidoException();
		}

	}

	public BigDecimal getPorcentajeComisionHipodromo() {
		return this.porcentajeComisionHipodromo;
	}

	protected void setPorcentajeComisionHipodromo(
			BigDecimal porcentajeComisionHipodromo) {
		this.porcentajeComisionHipodromo = porcentajeComisionHipodromo;
	}
	
}