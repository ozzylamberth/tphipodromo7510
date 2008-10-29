package edu.ar.uba.fi.model;

import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoCarreraException;

/**
 * @author Fernando E. Mansilla - 84567
 */
public enum EstadoCarrera {
	CANCELADA(new EstadoCarrera[] {}), FINALIZADA(new EstadoCarrera[] {}), A_AUDITAR(
			new EstadoCarrera[] { FINALIZADA }), EN_CURSO(
			new EstadoCarrera[] { A_AUDITAR }), CERRADA_A_APUESTAS(
			new EstadoCarrera[] { EN_CURSO }), ABIERTA_A_APUESTAS(
			new EstadoCarrera[] { CERRADA_A_APUESTAS });

	private EstadoCarrera[] estadosValidos;

	private EstadoCarrera(EstadoCarrera e[]) {
		this.estadosValidos = e;
	}

	/**
	 * @return Devuelve el siguiente estado siguiendo el flujo normal.
	 * @throws TransicionInvalidaEstadoCarreraException
	 *             Excepcion lanzada si no existe un estado hacia el cual hacer
	 *             la transición.
	 */
	public EstadoCarrera siguienteEstadoFlujoNormal()
			throws TransicionInvalidaEstadoCarreraException {
		if (estadosValidos.length > 0) {
			return estadosValidos[0];
		} else {
			throw new TransicionInvalidaEstadoCarreraException();
		}
	}

	/**
	 * @return Devuelve el estado asociado a la cancelación de carrera.
	 * @throws TransicionInvalidaEstadoCarreraException
	 *             Excepcion lanzada si no es posible la transición hacia el
	 *             estado cancelado.
	 */
	public EstadoCarrera cancelar()
			throws TransicionInvalidaEstadoCarreraException {
		if (!this.equals(FINALIZADA) && !this.equals(CANCELADA)) {
			return CANCELADA;
		} else {
			throw new TransicionInvalidaEstadoCarreraException();
		}
	}

	/**
	 * @param nuevoEstado
	 *            Nuevo estado hacia el que se desea evaluar la posible
	 *            transición.
	 * @return Devuelve true si la transición es posible, false en caso
	 *         contrario.
	 */
	public boolean esSiguienteEstadoValido(EstadoCarrera nuevoEstado) {
		for (int i = 0; i < estadosValidos.length; i++) {
			if (estadosValidos[i].equals(nuevoEstado)) {
				return true;
			}
		}

		if (CANCELADA.equals(nuevoEstado) && !this.equals(FINALIZADA)
				&& !this.equals(CANCELADA)) {
			return true;
		}

		return false;
	}
}
