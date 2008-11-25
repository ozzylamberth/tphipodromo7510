package ar.uba.fi.tecnicas.tphipodromo.modelo.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.modelo.EstadoCarrera;
import ar.uba.fi.tecnicas.tphipodromo.modelo.EstadoParticipante;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Jockey;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Participante;
import ar.uba.fi.tecnicas.tphipodromo.modelo.ReglamentoValeTodo;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Resultado;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.Apuesta;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.ApuestaFactory;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.ApuestaGanador;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ApuestaPerdidaException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ApuestaVencidaException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.CantidadParticipantesInvalidaException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.CarreraCerradaAApuestasException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.CarreraNoFinalizadaException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.HipodromoException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ImposibleFabricarApuestaException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ParticipanteNoCalificadoException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ParticipantesEnDistintasCarrerasException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ResultadosCarreraInvalidosException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.TipoApuestaInvalidoException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.TransicionInvalidaEstadoApuestaException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.TransicionInvalidaEstadoCarreraException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.TransicionInvalidaEstadoParticipanteException;

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

	public void testAgregarParticipantes() {
		
		Participante participante1 = new Participante(new Caballo(), new Jockey(), carrera);
		Participante participante2 = new Participante(new Caballo(), new Jockey(), carrera);
		Participante participante3 = new Participante(new Caballo(), new Jockey(), carrera);
		
		List<Participante> participantesList = new ArrayList<Participante>();
		
		participantesList.add(participante1);
		participantesList.add(participante2);
		participantesList.add(participante3);
		
		try{
			carrera.setParticipantes(participantesList);				
		}catch (HipodromoException e){
			fail ("Error al setear List de Participantes");
		}
	}
}
