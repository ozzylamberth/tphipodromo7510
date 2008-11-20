package edu.ar.uba.fi.model;

/**
 * Enumerado en donde se definen los estados posibles del participante de la
 * carrera y las reglas que rigen las secuencias de transiciones.
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
	 *            transición.
	 * @return Devuelve true si la transición es posible, false en caso
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