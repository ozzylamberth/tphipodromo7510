package edu.ar.uba.fi.model.apuestas;


/**
 * @author Fernando E. Mansilla - 84567
 */
public enum EstadoApuesta {

	VENCIDA(new EstadoApuesta[] {}), 
	PAGADA(new EstadoApuesta[] {}), 
	LIQUIDADA(new EstadoApuesta[] { PAGADA, VENCIDA }),
	CREADA(new EstadoApuesta[] { LIQUIDADA, VENCIDA });

	private EstadoApuesta[] estadosValidos;

	private EstadoApuesta(EstadoApuesta e[]) {
		this.estadosValidos = e;
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
