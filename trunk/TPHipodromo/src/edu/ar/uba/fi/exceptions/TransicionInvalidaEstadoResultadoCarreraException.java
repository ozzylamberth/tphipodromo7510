package edu.ar.uba.fi.exceptions;

import edu.ar.uba.fi.model.EstadoResultadoCarrera;

/**
 * @author Fernando E. Mansilla - 84567
 */
public class TransicionInvalidaEstadoResultadoCarreraException extends
		CarreraException {
	private static final long serialVersionUID = 6787136424781511707L;
	private EstadoResultadoCarrera estadoOriginal = null;
	private EstadoResultadoCarrera estadoPretendido = null;

	public TransicionInvalidaEstadoResultadoCarreraException() {
	}

	/**
	 * @param estadoOriginal
	 * @param estadoPretendido
	 */
	public TransicionInvalidaEstadoResultadoCarreraException(
			EstadoResultadoCarrera estadoOriginal,
			EstadoResultadoCarrera estadoPretendido) {
		this.estadoOriginal = estadoOriginal;
		this.estadoPretendido = estadoPretendido;
	}

	public EstadoResultadoCarrera getEstadoOriginal() {
		return estadoOriginal;
	}

	public EstadoResultadoCarrera getEstadoPretendido() {
		return estadoPretendido;
	}
}
