package ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones;

import ar.uba.fi.tecnicas.tphipodromo.modelo.EstadoParticipante;

/**
 * Excepcion utilizada para indica que hubo un error al intentar pasar un
 * participante a un nuevo estado.
 * 
 * @author Fernando E. Mansilla - 84567
 */
public class TransicionInvalidaEstadoParticipanteException extends
		CarreraException {
	private static final long serialVersionUID = 6787136424781511707L;
	private EstadoParticipante estadoOriginal = null;
	private EstadoParticipante estadoPretendido = null;

	public TransicionInvalidaEstadoParticipanteException() {
		super("");
	}

	/**
	 * @param estadoOriginal
	 * @param estadoPretendido
	 */
	public TransicionInvalidaEstadoParticipanteException(
			EstadoParticipante estadoOriginal,
			EstadoParticipante estadoPretendido) {
		
		super("");
		this.estadoOriginal = estadoOriginal;
		this.estadoPretendido = estadoPretendido;
	}

	public EstadoParticipante getEstadoOriginal() {
		return estadoOriginal;
	}

	public EstadoParticipante getEstadoPretendido() {
		return estadoPretendido;
	}
}
