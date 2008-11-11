package edu.ar.uba.fi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ar.uba.fi.exceptions.ApuestaPerdidaException;
import edu.ar.uba.fi.exceptions.ApuestaVencidaException;
import edu.ar.uba.fi.exceptions.CantidadParticipantesInvalidaException;
import edu.ar.uba.fi.exceptions.CarreraCerradaAApuestasException;
import edu.ar.uba.fi.exceptions.CarreraNoFinalizadaException;
import edu.ar.uba.fi.exceptions.HipodromoException;
import edu.ar.uba.fi.exceptions.ImposibleFabricarApuestaException;
import edu.ar.uba.fi.exceptions.ParticipanteNoCalificadoException;
import edu.ar.uba.fi.exceptions.ParticipantesEnDistintasCarrerasException;
import edu.ar.uba.fi.exceptions.ResultadosCarreraInvalidosException;
import edu.ar.uba.fi.exceptions.TipoApuestaInvalidoException;
import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoApuestaException;
import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoCarreraException;
import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoParticipanteException;
import edu.ar.uba.fi.model.Caballo;
import edu.ar.uba.fi.model.Carrera;
import edu.ar.uba.fi.model.EstadoCarrera;
import edu.ar.uba.fi.model.EstadoParticipante;
import edu.ar.uba.fi.model.Jockey;
import edu.ar.uba.fi.model.Participante;
import edu.ar.uba.fi.model.ReglamentoValeTodo;
import edu.ar.uba.fi.model.Resultado;
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
	private Resultado resultado;
	private List<Resultado> resultados;

	protected void setUp() throws Exception {

		carrera = new Carrera(new ReglamentoValeTodo());
		participante = new Participante(new Caballo(), new Jockey(), carrera);
		carrera.addParticipante(participante);

		apuestaGanador = ApuestaFactory.getInstance().crear(
				ApuestaGanador.class, participante, new BigDecimal(10));

		resultado = new Resultado();
		resultado.setOrdenLlegada(1);
		resultados = new ArrayList<Resultado>();
		resultados.add(resultado);

	}

	private void aprobarResultados(Carrera carrera)
			throws TransicionInvalidaEstadoParticipanteException {
		Iterator<Participante> it = carrera.getParticipantes().iterator();
		while (it.hasNext()) {
			Participante participante = it.next();
			if (participante.getEstado().equals(EstadoParticipante.A_AUDITAR)) {
				participante.setEstado(EstadoParticipante.FINALIZADO);
			}
		}
	}

	public void testEstadosCarrera() {
		assertEquals(carrera.getEstadoCarrera(),
				EstadoCarrera.INSCRIPCION_PARTICIPANTES);

		try {
			carrera.abrirApuestas();
		} catch (HipodromoException e) {
			fail("Esta excepción no se debería haber lanzado");
		}
		assertEquals(carrera.getEstadoCarrera(),
				EstadoCarrera.ABIERTA_A_APUESTAS);

		try {
			carrera.cerrarApuestas();
		} catch (HipodromoException e) {
			fail("Esta excepción no se debería haber lanzado");
		}

		assertEquals(carrera.getEstadoCarrera(),
				EstadoCarrera.CERRADA_A_APUESTAS);

		try {
			carrera.comenzar();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (HipodromoException e) {
			e.printStackTrace();
			fail("Esta excepción no se debería haber lanzado");
		}

		assertEquals(carrera.getEstadoCarrera(), EstadoCarrera.EN_CURSO);

		try {

			// --Asignacion de resultados
			List<Participante> participantes = carrera.getParticipantes();
			for (int j = 0; j < participantes.size(); j++) {
				participantes.get(j).setResultado(resultados.get(j));
			}
			//
			carrera.terminar();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ResultadosCarreraInvalidosException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (HipodromoException e) {
			e.printStackTrace();
			fail("Esta excepción no se debería haber lanzado");
		}

		assertEquals(carrera.getEstadoCarrera(), EstadoCarrera.A_AUDITAR);

		try {
			aprobarResultados(carrera);
			carrera.aprobarResultados();
		} catch (HipodromoException e) {
			fail("Esta excepción no se debería haber lanzado");
		}

		assertEquals(carrera.getEstadoCarrera(), EstadoCarrera.FINALIZADA);

	}

	public void testCancelarCarrera() {

		try {
			carrera.cancelar();
		} catch (HipodromoException e) {
			fail("Esta excepción no se debería haber lanzado");
		}

		assertEquals(carrera.getEstadoCarrera(), EstadoCarrera.CANCELADA);
	}

	public void testApostarACarreraCerradaAApuestas() {

		try {
			carrera.abrirApuestas();
			carrera.cerrarApuestas();
		} catch (HipodromoException e) {
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
			carrera.abrirApuestas();
			carrera.cerrarApuestas();
			carrera.comenzar();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (HipodromoException e) {
			e.printStackTrace();
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
		} catch (HipodromoException e) {
			fail("Error al liquidar");
			e.printStackTrace();
		}
	}

	public void testResultadosCarreraInvalidos()
			throws ParticipanteNoCalificadoException {

		try {
			participante = new Participante(new Caballo(), new Jockey(),
					carrera);
			carrera.addParticipante(participante);
			carrera.abrirApuestas();
			carrera.cerrarApuestas();
			carrera.comenzar();
			carrera.terminar();
			fail("El método debería haber lanzado excepción ResultadosCarreraInvalidosExeption");
		} catch (HipodromoException e) {
		}
	}

}
