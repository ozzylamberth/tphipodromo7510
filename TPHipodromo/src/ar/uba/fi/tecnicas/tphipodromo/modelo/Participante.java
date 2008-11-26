package ar.uba.fi.tecnicas.tphipodromo.modelo;

import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ResultadosCarreraInvalidosException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.TransicionInvalidaEstadoParticipanteException;

/**
 * 
 * Clase que asocia un Caballo y un Jockey. Como adicional se
 * encarga de actualizar los resultados en la estadística asociada al caballo.
 * 
 * @author Fernando E. Mansilla - 84567
 */
public class Participante implements Identificable {
	
	private Long id = new Long(0);
	private int nroParticipante;
	private Resultado rresultado = null;
	private Caballo caballo;
	private Jockey jockey;
	private Carrera carrera;
	private EstadoParticipante eestado;

	public Participante(Caballo caballo, Jockey jockey, Carrera carrera) {
		this.caballo = caballo;
		this.jockey = jockey;
		this.carrera = carrera;
		this.eestado = EstadoParticipante.LARGADA_PENDIENTE;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNroParticipante() {
		return this.nroParticipante;
	}

	public void setNroParticipante(int nroParticipante) {
		this.nroParticipante = nroParticipante;
	}

	public Resultado getResultado() throws ResultadosCarreraInvalidosException {
		if (this.eestado.equals(EstadoParticipante.A_AUDITAR)
				|| this.eestado.equals(EstadoParticipante.FINALIZADO)) {
			return this.rresultado;
		} else {
			throw new ResultadosCarreraInvalidosException();
		}
	}

	public void setResultado(Resultado resultado)
			throws TransicionInvalidaEstadoParticipanteException {
		this.rresultado = resultado;
		setEstado(EstadoParticipante.A_AUDITAR);
	}

	public Caballo getCaballo() {
		return this.caballo;
	}

	public void setCaballo(Caballo caballo) {
		this.caballo = caballo;
	}

	public Jockey getJockey() {
		return this.jockey;
	}

	public void setJockey(Jockey jockey) {
		this.jockey = jockey;
	}

	public Carrera getCarrera() {
		return this.carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	/**
	 * @param nuevoEstado
	 * @throws TransicionInvalidaEstadoParticipanteException
	 *             Lanzada si el siguiente estado no es valido segun las reglas
	 *             en EStadoParticipante. Un caso particular de error es cuando
	 *             se intenta pasar a estado A_AUDITAR y no se asocio ningún
	 *             resultado.
	 */
	public void setEstado(EstadoParticipante nuevoEstado)
			throws TransicionInvalidaEstadoParticipanteException {
		if (!eestado.equals(nuevoEstado)) {
			if (eestado.esSiguienteEstadoValido(nuevoEstado)) {
				if (nuevoEstado.equals(EstadoParticipante.A_AUDITAR)
						&& rresultado == null) {
					throw new TransicionInvalidaEstadoParticipanteException();
				} else {
					this.eestado = nuevoEstado;
					if (this.eestado.equals(EstadoParticipante.FINALIZADO)) {
						try {
							getCaballo().getEstadisticas().agregarResultado(
									getResultado().getOrdenLlegada());
						} catch (ResultadosCarreraInvalidosException e) {
						}
					}
				}
			} else {
				throw new TransicionInvalidaEstadoParticipanteException(eestado,
						nuevoEstado);
			}
		}
	}

	public EstadoParticipante getEstado() {
		return this.eestado;
	}

	@SuppressWarnings("all")
	private Resultado getRresultado() {
		return rresultado;
	}

	@SuppressWarnings("all")
	private void setRresultado(Resultado rresultado) {
		this.rresultado = rresultado;
	}

	@SuppressWarnings("all")
	private EstadoParticipante getEestado() {
		return eestado;
	}

	@SuppressWarnings("all")
	private void setEestado(EstadoParticipante eestado) {
		this.eestado = eestado;
	}
}