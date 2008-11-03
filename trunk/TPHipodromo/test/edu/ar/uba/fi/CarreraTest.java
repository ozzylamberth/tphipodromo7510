package edu.ar.uba.fi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import edu.ar.uba.fi.exceptions.ApuestaPerdidaException;
import edu.ar.uba.fi.exceptions.ApuestaVencidaException;
import edu.ar.uba.fi.exceptions.CantidadParticipantesInvalidaException;
import edu.ar.uba.fi.exceptions.CarreraCerradaAApuestasException;
import edu.ar.uba.fi.exceptions.CarreraNoFinalizadaException;
import edu.ar.uba.fi.exceptions.ImposibleFabricarApuestaException;
import edu.ar.uba.fi.exceptions.ParticipanteNoCalificadoException;
import edu.ar.uba.fi.exceptions.ParticipantesEnDistintasCarrerasException;
import edu.ar.uba.fi.exceptions.ResultadosCarreraInvalidosException;
import edu.ar.uba.fi.exceptions.TipoApuestaInvalidoException;
import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoApuestaException;
import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoCarreraException;
import edu.ar.uba.fi.model.Caballo;
import edu.ar.uba.fi.model.Carrera;
import edu.ar.uba.fi.model.EstadoCarrera;
import edu.ar.uba.fi.model.Jinete;
import edu.ar.uba.fi.model.Participante;
import edu.ar.uba.fi.model.ReglamentoValeTodo;
import edu.ar.uba.fi.model.ResultadoCarrera;
import edu.ar.uba.fi.model.apuestas.Apuesta;
import edu.ar.uba.fi.model.apuestas.ApuestaFactory;
import edu.ar.uba.fi.model.apuestas.ApuestaGanador;
import junit.framework.TestCase;

/**
 * @version 1.1 Fernando E. Mansilla - 84567 Comente algunos de los test debido
 *          a que perdieron sentido por el cambio en la forma de manejar los
 *          estados de la carrera.
 */
public class CarreraTest extends TestCase {

	private Apuesta apuestaGanador;

	private Carrera carrera;
	private Participante participante;
	private ResultadoCarrera resultado;
	private List<ResultadoCarrera> resultados;

	protected void setUp() throws Exception {

		carrera = new Carrera(new ReglamentoValeTodo());
		participante = new Participante(new Caballo(), new Jinete(), carrera);
		carrera.addParticipante(participante);

		apuestaGanador = ApuestaFactory.getInstance().crear(
				ApuestaGanador.class, participante, new BigDecimal(10));

		resultado = new ResultadoCarrera(participante);
		resultado.setOrdenLlegada(1);
		resultados = new ArrayList<ResultadoCarrera>();
		resultados.add(resultado);

	}

	public void testEstadosCarrera() {

		assertEquals(carrera.getEstadoCarrera(),
				EstadoCarrera.ABIERTA_A_APUESTAS);

		try {
			carrera.cerrarApuestas();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Esta excepción no se debería haber lanzado");
		}

		assertEquals(carrera.getEstadoCarrera(),
				EstadoCarrera.CERRADA_A_APUESTAS);

		try {
			carrera.comenzar();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Esta excepción no se debería haber lanzado");
		}

		assertEquals(carrera.getEstadoCarrera(), EstadoCarrera.EN_CURSO);

		try {
			carrera.terminar(resultados);
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ResultadosCarreraInvalidosException e) {
			fail("Esta excepción no se debería haber lanzado");
		}

		assertEquals(carrera.getEstadoCarrera(), EstadoCarrera.A_AUDITAR);

		try {
			carrera.aprobarResultados();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Esta excepción no se debería haber lanzado");
		}

		assertEquals(carrera.getEstadoCarrera(), EstadoCarrera.FINALIZADA);

	}

	public void testCancelarCarrera() {

		try {
			carrera.cancelar();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Esta excepción no se debería haber lanzado");
		}

		assertEquals(carrera.getEstadoCarrera(), EstadoCarrera.CANCELADA);
	}

	public void testApostarACarreraCerradaAApuestas() {

		try {
			carrera.cerrarApuestas();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Esta excepción no se debería haber lanzado");
		}

		try {
			ApuestaFactory.getInstance().crear(ApuestaGanador.class,
					participante, new BigDecimal(10));
			fail("El método debería haber lanzado la excepción CarreraCerradaAApuestasException");
		} catch (CarreraCerradaAApuestasException e) {
		} catch (CantidadParticipantesInvalidaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ParticipantesEnDistintasCarrerasException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ImposibleFabricarApuestaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (TipoApuestaInvalidoException e) {
			fail("Esta excepción no se debería haber lanzado");
		}
	}

	public void testLiquidarApuestaCarreraNoFinalizada() {

		try {
			carrera.cerrarApuestas();
			carrera.comenzar();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Esta excepción no se debería haber lanzado");
		}

		try {
			apuestaGanador.liquidar();
			fail("El método debería haber lanzado excepción CarreraNoFinalizadaException");
		} catch (CarreraNoFinalizadaException e) {
		} catch (ApuestaPerdidaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (TransicionInvalidaEstadoApuestaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ApuestaVencidaException e) {
			fail("Esta excepción no se debería haber lanzado");
		}
	}

	public void testResultadosCarreraInvalidos()
			throws ParticipanteNoCalificadoException {

		participante = new Participante(new Caballo(), new Jinete(), carrera);
		carrera.addParticipante(participante);

		try {
			carrera.cerrarApuestas();
			carrera.comenzar();
			carrera.terminar(resultados);
			fail("El método debería haber lanzado excepción ResultadosCarreraInvalidosExeption");
		} catch (ResultadosCarreraInvalidosException e) {
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Esta excepción no se debería haber lanzado");
		}
	}

}
