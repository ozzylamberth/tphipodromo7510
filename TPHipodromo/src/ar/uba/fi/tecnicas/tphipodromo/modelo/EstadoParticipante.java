package ar.uba.fi.tecnicas.tphipodromo.modelo;

/**
 * Enumerado en donde se definen los estados posibles del participante de la
 * carrera y las reglas que rigen las secuencias de transiciones.
 * 
 * @author Fernando E. Mansilla - 84567
 */
public enum EstadoParticipante {

	DESCALIFICADO("Descalificado", new EstadoParticipante[] {}),
	FINALIZADO("Finalizado", new EstadoParticipante[] {}),
	A_AUDITAR("A Auditar", new EstadoParticipante[] { FINALIZADO, DESCALIFICADO }),
	ABANDONO("Abonado", new EstadoParticipante[] {}),
	EN_CURSO("En curso", new EstadoParticipante[] { A_AUDITAR, ABANDONO }),
	LARGADA_PENDIENTE("Largada Pendiente", new EstadoParticipante[] { EN_CURSO, ABANDONO });

	private String nombre;
	private EstadoParticipante[] estadosValidos;

	private EstadoParticipante(String nombre, EstadoParticipante e[]) {
		this.nombre = nombre;
		this.estadosValidos = e;
	}
	
	public String getNombre() {
		return this.nombre;
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