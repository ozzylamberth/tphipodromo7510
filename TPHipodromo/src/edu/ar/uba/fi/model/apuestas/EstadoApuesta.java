package edu.ar.uba.fi.model.apuestas;

import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoApuestaException;

/**
 * @author Fernando E. Mansilla - 84567
 */
public enum EstadoApuesta {

	VENCIDA(new EstadoApuesta[] {}),
	PAGADA(new EstadoApuesta[] {}),
	LIQUIDADA(new EstadoApuesta[] { PAGADA, VENCIDA }),
	CREADA(new EstadoApuesta[] { LIQUIDADA });

	private EstadoApuesta[] estadosValidos;

	private EstadoApuesta(EstadoApuesta e[]) {
		this.estadosValidos = e;
	}

	/**
	 * @return Devuelve el siguiente estado siguiendo el flujo normal.
	 * @throws TransicionInvalidaEstadoApuestaException
	 *             Excepcion lanzada si no existe un estado hacia el cual hacer
	 *             la transición.
	 */
	public EstadoApuesta siguienteEstadoFlujoNormal()
			throws TransicionInvalidaEstadoApuestaException {
		if (estadosValidos.length > 0) {
			return estadosValidos[0];
		} else {
			throw new TransicionInvalidaEstadoApuestaException();
		}
	}

	/**
	 * @param nuevoEstado
	 *            Nuevo estado hacia el que se desea evaluar la posible
	 *            transición.
	 * @return Devuelve true si la transición es posible, false en caso
	 *         contrario.
	 */
	public boolean esSiguienteEstadoValido(EstadoApuesta nuevoEstado) {
		for (int i = 0; i < estadosValidos.length; i++) {
			if (estadosValidos[i].equals(nuevoEstado)) {
				return true;
			}
		}
		return false;
	}

}
