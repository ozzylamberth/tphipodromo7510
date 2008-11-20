package ar.uba.fi.tecnicas.tphipodromo.modelo;

import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.TransicionInvalidaEstadoCarreraException;

/**
 * Enumerado en donde se definen los estados posibles de una carrera y las
 * reglas que rigen las secuencias de transiciones.
 * 
 * @author Fernando E. Mansilla - 84567
 */
public enum EstadoCarrera {
	CANCELADA(new EstadoCarrera[] {}), FINALIZADA(new EstadoCarrera[] {}), A_AUDITAR(
			new EstadoCarrera[] { FINALIZADA }), EN_CURSO(new EstadoCarrera[] {
			A_AUDITAR, CANCELADA }), CERRADA_A_APUESTAS(new EstadoCarrera[] {
			EN_CURSO, CANCELADA }), ABIERTA_A_APUESTAS(new EstadoCarrera[] {
			CERRADA_A_APUESTAS, CANCELADA }), INSCRIPCION_PARTICIPANTES(
			new EstadoCarrera[] { ABIERTA_A_APUESTAS, CANCELADA });

	private EstadoCarrera[] estadosValidos;

	private EstadoCarrera(EstadoCarrera e[]) {
		this.estadosValidos = e;
	}

	/**
	 * @return Devuelve el estado asociado a la cancelación de carrera.
	 * @throws TransicionInvalidaEstadoCarreraException
	 *             Excepcion lanzada si no es posible la transición hacia el
	 *             estado cancelado.
	 */
	public EstadoCarrera cancelar()
			throws TransicionInvalidaEstadoCarreraException {
		if (esSiguienteEstadoValido(CANCELADA)) {
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
		return false;
	}
}
