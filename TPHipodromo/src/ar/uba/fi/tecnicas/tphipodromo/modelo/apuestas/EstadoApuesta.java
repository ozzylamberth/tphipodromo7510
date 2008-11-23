package ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas;

/**
 * Enumerado en donde se definen los estados posibles de una apuesta y las
 * reglas que rigen las secuencias de transiciones.
 * 
 * @author Fernando E. Mansilla - 84567
 */
public enum EstadoApuesta {

	VENCIDA("Vencida", new EstadoApuesta[] {}),
	PAGADA("Pagada", new EstadoApuesta[] {}),
	LIQUIDADA("Liquidada", new EstadoApuesta[] { PAGADA, VENCIDA }),
	CREADA("Creada", new EstadoApuesta[] { LIQUIDADA, VENCIDA });
	
	private String nombre;
	private EstadoApuesta[] estadosValidos;

	private EstadoApuesta(String nombre, EstadoApuesta e[]) {
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
	public boolean esSiguienteEstadoValido(EstadoApuesta nuevoEstado) {
		for (int i = 0; i < estadosValidos.length; i++) {
			if (estadosValidos[i].equals(nuevoEstado)) {
				return true;
			}
		}
		return false;
	}

}
