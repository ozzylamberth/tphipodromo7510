package ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Identificable;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.HipodromoException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.TipoApuestaInvalidoException;


/**
 * Una Bolsa de Apuestas contiene todas las apuestas del mismo tipo que se realizaron sobre una
 * carrera o conjunto de carreras. Es una clase abstracta que define la interfaz
 * de las BolsaApuestas y su funcionalidad básica.Permite que se pueda cambiar la forma de calcular los
 * dividendos simplemente heredando de ella y redefiniendo el método en cada
 * clase. Es utilizada por la Clase BolsaApuestasManager para crear las
 * distintas Bolsas de apuestas según el tipo de apuesta.
 */
public abstract class BolsaApuestasAbstracta implements Identificable {
	
	private Long id;
	
	public static final int DECIMALES = 2;
	public static final RoundingMode ROUNDING_MODE = RoundingMode.CEILING;

	protected Class<? extends Apuesta> tipoApuestas;
	protected Set<Carrera> carreras = new HashSet<Carrera>();
	protected Set<Apuesta> apuestas = new HashSet<Apuesta>();
	protected BigDecimal porcentajeComisionHipodromo;
	protected BigDecimal incrementoPozo;
	protected BigDecimal pozoMinimo;

	public BolsaApuestasAbstracta() {
	}

	public abstract BigDecimal getDividendo() throws HipodromoException;

	public boolean correspondeACarreras(Set<Carrera> carreras) {
		return carreras.equals(getCarreras());
	}

	public Class<? extends Apuesta> getTipoBolsaApuestas() {
		return tipoApuestas;
	}

	protected void setTipoBolsaApuestas(
			Class<? extends Apuesta> tipoBolsaApuestas) {
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

	/**
	 * 
	 * @param apuesta
	 * @throws TipoApuestaInvalidoException
	 */
	public void addApuesta(Apuesta apuesta) throws TipoApuestaInvalidoException {
		if (apuesta.getClass().equals(tipoApuestas)) {
			apuesta.setBolsaApuestas(this);
			this.apuestas.add(apuesta);
		} else {
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

	public void setPozoMinimo(BigDecimal pozoMinimo) {
		this.pozoMinimo = pozoMinimo;
	}

	public void setIncrementoPozo(BigDecimal incrementoPozo) {
		this.incrementoPozo = incrementoPozo;
	}

	public BigDecimal getIncrementoPozo() {
		return incrementoPozo;
	}

	public BigDecimal getPozoMinimo() {
		return pozoMinimo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@SuppressWarnings("all")
	private Class<? extends Apuesta> getTipoApuestas() {
		return tipoApuestas;
	}

	@SuppressWarnings("all")
	private void setTipoApuestas(Class<? extends Apuesta> tipoApuestas) {
		this.tipoApuestas = tipoApuestas;
	}

}