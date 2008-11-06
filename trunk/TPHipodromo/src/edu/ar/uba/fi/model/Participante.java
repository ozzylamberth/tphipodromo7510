package edu.ar.uba.fi.model;

import java.math.BigDecimal;

import edu.ar.uba.fi.exceptions.ResultadosCarreraInvalidosException;
import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoParticipanteException;

/**
 * @author Fernando E. Mansilla - 84567
 */
public class Participante {
	private int nroParticipante;
	private BigDecimal peso;
	private Resultado resultado = null;
	private Caballo caballo;
	private Jockey jockey;
	private Carrera carrera;
	private EstadoParticipante estado;

	public Participante(Caballo caballo, Jockey jockey, Carrera carrera) {
		this.caballo = caballo;
		this.jockey = jockey;
		this.carrera = carrera;
		this.estado = EstadoParticipante.LARGADA_PENDIENTE;
	}

	public int getNroParticipante() {
		return this.nroParticipante;
	}

	public void setNroParticipante(int nroParticipante) {
		this.nroParticipante = nroParticipante;
	}

	public BigDecimal getPeso() {
		return this.peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public Resultado getResultado() throws ResultadosCarreraInvalidosException {
		if (this.estado.equals(EstadoParticipante.A_AUDITAR)
				|| this.estado.equals(EstadoParticipante.FINALIZADO)) {
			return this.resultado;
		} else {
			throw new ResultadosCarreraInvalidosException();
		}
	}

	public void setResultado(Resultado resultado)
			throws TransicionInvalidaEstadoParticipanteException {
		this.resultado = resultado;
		setEstado(EstadoParticipante.A_AUDITAR);
	}

	public Caballo getCaballo() {
		return this.caballo;
	}

	public void setCaballo(Caballo caballo) {
		this.caballo = caballo;
	}

	public Jockey getJinete() {
		return this.jockey;
	}

	public void setJinete(Jockey jockey) {
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
	 *             se intenta pasar a estado A_AUDITAR y no se asocio ning�n
	 *             resultado.
	 */
	public void setEstado(EstadoParticipante nuevoEstado)
			throws TransicionInvalidaEstadoParticipanteException {
		if (!estado.equals(nuevoEstado)) {
			if (estado.esSiguienteEstadoValido(nuevoEstado)) {
				if (nuevoEstado.equals(EstadoParticipante.A_AUDITAR)
						&& resultado == null) {
					throw new TransicionInvalidaEstadoParticipanteException();
				} else {
					this.estado = nuevoEstado;
				}
			} else {
				throw new TransicionInvalidaEstadoParticipanteException(estado,
						nuevoEstado);
			}
		}
	}

	public EstadoParticipante getEstado() {
		return this.estado;
	}
}