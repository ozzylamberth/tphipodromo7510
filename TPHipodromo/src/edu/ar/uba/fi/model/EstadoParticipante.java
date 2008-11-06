package edu.ar.uba.fi.model;

/**
 * Enumerado con los estados posibles para un participante. Se encarga de
 * controlar secuencias de estados v�lidas.
 * 
 * @author Fernando E. Mansilla - 84567
 */
public enum EstadoParticipante {

	DESCALIFICADO(new EstadoParticipante[] {}), FINALIZADO(
			new EstadoParticipante[] {}), A_AUDITAR(new EstadoParticipante[] {
			FINALIZADO, DESCALIFICADO }), ABANDONO(new EstadoParticipante[] {}), EN_CURSO(
			new EstadoParticipante[] { A_AUDITAR, ABANDONO }), LARGADA_PENDIENTE(
			new EstadoParticipante[] { EN_CURSO, ABANDONO });

	private EstadoParticipante[] estadosValidos;

	private EstadoParticipante(EstadoParticipante e[]) {
		this.estadosValidos = e;
	}

	/**
	 * @param nuevoEstado
	 *            Nuevo estado hacia el que se desea evaluar la posible
	 *            transici�n.
	 * @return Devuelve true si la transici�n es posible, false en caso
	 *         contrario.
	 */
	public boolean esSiguienteEstadoValido(EstadoParticipante nuevoEstado) {
		for (int i = 0; i < estadosValidos.length; i++) {
			if (estadosValidos[i].equals(nuevoEstado)) {
				return true;
			}
		}
		return false;
	}

}