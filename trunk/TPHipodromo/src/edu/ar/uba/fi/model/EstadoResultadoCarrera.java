package edu.ar.uba.fi.model;

import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoResultadoCarreraException;

/**
 * @author Fernando E. Mansilla - 84567
 */
public enum EstadoResultadoCarrera {

	DESCALIFICADO(new EstadoResultadoCarrera[] {}), 
	FINALIZADO(new EstadoResultadoCarrera[] {DESCALIFICADO}), 
	ABANDONO(new EstadoResultadoCarrera[] {}),
	EN_CURSO(new EstadoResultadoCarrera[] { FINALIZADO, ABANDONO, DESCALIFICADO });

	private EstadoResultadoCarrera[] estadosValidos;

	private EstadoResultadoCarrera(EstadoResultadoCarrera e[]) {
		this.estadosValidos = e;
	}

	/**
	 * @return Devuelve el siguiente estado siguiendo el flujo normal.
	 * @throws TransicionInvalidaEstadoResultadoCarreraException
	 *             Excepcion lanzada si no existe un estado hacia el cual hacer
	 *             la transición.
	 */
	public EstadoResultadoCarrera siguienteEstadoFlujoNormal()
			throws TransicionInvalidaEstadoResultadoCarreraException {
		if (estadosValidos.length > 0) {
			return estadosValidos[0];
		} else {
			throw new TransicionInvalidaEstadoResultadoCarreraException(this, null);
		}
	}

	/**
	 * @param nuevoEstado
	 *            Nuevo estado hacia el que se desea evaluar la posible
	 *            transición.
	 * @return Devuelve true si la transición es posible, false en caso
	 *         contrario.
	 */
	public boolean esSiguienteEstadoValido(EstadoResultadoCarrera nuevoEstado) {
		for (int i = 0; i < estadosValidos.length; i++) {
			if (estadosValidos[i].equals(nuevoEstado)) {
				return true;
			}
		}
		return false;
	}

}

