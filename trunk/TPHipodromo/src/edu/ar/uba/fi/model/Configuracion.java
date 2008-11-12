package edu.ar.uba.fi.model;

import java.math.BigDecimal;

import edu.ar.uba.fi.exceptions.ConfiguracionInvalidaException;
import edu.ar.uba.fi.model.apuestas.Apuesta;

/**
 * La clase configuración define la configuración de una carrera con respecto a
 * las apuestas. Indica si las apuestas del tipo indicado para la carrera
 * asociada pueden o no ser realizadas.
 * 
 * @author Fernando E. Mansilla - 84567
 */
public class Configuracion {

	private BigDecimal incrementoPozo;
	private BigDecimal pozoMinimo;
	private Class<? extends Apuesta> tipoApuesta;
	private Carrera carrera;

	/**
	 * @param carrera
	 *            Carrera sobre la cual tiene vigencia la Configuración
	 * @param tipoApuesta
	 *            Tipo de apuesta sobre el que aplica.
	 * @param pozoMinimo
	 *            Pozo mínimo, debe ser mayor o igual a cero.
	 * @param incrementoPozo
	 *            Incremento sobre el pozo final acumulado, debe ser mayor o
	 *            igual a cero.
	 * @throws ConfiguracionInvalidaException
	 *             Excepción lanzada en el caso de que alguno de los parámetros
	 *             sea inválido.
	 */
	public Configuracion(Carrera carrera, Class<? extends Apuesta> tipoApuesta,
			BigDecimal pozoMinimo, BigDecimal incrementoPozo)
			throws ConfiguracionInvalidaException {

		if (carrera == null || tipoApuesta == null || pozoMinimo == null
				|| incrementoPozo == null) {
			throw new ConfiguracionInvalidaException();
		} else if (pozoMinimo.compareTo(BigDecimal.ZERO) < 0
				|| incrementoPozo.compareTo(BigDecimal.ZERO) < 0) {
			throw new ConfiguracionInvalidaException();
		}

		this.carrera = carrera;
		this.tipoApuesta = tipoApuesta;
		this.pozoMinimo = pozoMinimo;
		this.incrementoPozo = incrementoPozo;

	}

	public BigDecimal getIncrementoPozo() {
		return incrementoPozo;
	}

	public void setIncrementoPozo(BigDecimal incrementoPozo) {
		this.incrementoPozo = incrementoPozo;
	}

	public BigDecimal getPozoMinimo() {
		return pozoMinimo;
	}

	public void setPozoMinimo(BigDecimal pozoMinimo) {
		this.pozoMinimo = pozoMinimo;
	}

	public Class<? extends Apuesta> getTipoApuesta() {
		return tipoApuesta;
	}

	public Carrera getCarrera() {
		return carrera;
	}

}