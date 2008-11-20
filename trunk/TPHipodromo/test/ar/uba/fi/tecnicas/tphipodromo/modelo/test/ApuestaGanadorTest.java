package ar.uba.fi.tecnicas.tphipodromo.modelo.test;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.modelo.EstadoParticipante;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Jockey;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Participante;
import ar.uba.fi.tecnicas.tphipodromo.modelo.ReglamentoValeTodo;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Resultado;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.Apuesta;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.ApuestaFactory;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.ApuestaGanador;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.EstadoApuesta;
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
 * Caso de Prueba 1: Liquidacion Apuesta a Ganador Ganada.
 * 
 */
public class ApuestaGanadorTest extends TestCase {

	private Apuesta apuestaGanador;

	protected void setUp() throws Exception {
		Caballo caballo = new Caballo();
		Jockey jockey = new Jockey();
		Carrera carrera = new Carrera(new ReglamentoValeTodo());
		Participante participante = new Participante(caballo, jockey, carrera);
		carrera.addParticipante(participante);
		apuestaGanador = ApuestaFactory.getInstance().crear(
				ApuestaGanador.class, participante, new BigDecimal(10));
		carrera.abrirApuestas();
		carrera.cerrarApuestas();
		carrera.comenzar();
		// --Asignacion de resultados
		Iterator<Participante> itParticipantes = carrera.getParticipantes()
				.iterator();
		while (itParticipantes.hasNext()) {
			itParticipantes.next().setResultado(new Resultado(1, 10));
		}
		//
		carrera.terminar();
		aprobarResultados(carrera);
		carrera.aprobarResultados();
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

	public void testIsAcertada() throws HipodromoException {
		assertTrue(this.apuestaGanador.isAcertada());
	}

	public void testLiquidar() {
		assertEquals(this.apuestaGanador.getEstadoApuesta(),
				EstadoApuesta.CREADA);
		try {
			assertEquals(this.apuestaGanador.liquidar().compareTo(
					this.apuestaGanador.getMontoApostado()) == 0, true);
		} catch (ApuestaPerdidaException e) {
			fail("La apuesta esta perdida cuando deberï¿½a estar ganada");
			e.printStackTrace();
		} catch (TransicionInvalidaEstadoApuestaException e) {
			fail("No se pudo realizar la transicion de estado a liquidada");
			e.printStackTrace();
		} catch (CarreraNoFinalizadaException e) {
			fail("La carrera no esta en estado finalizada");
			e.printStackTrace();
		} catch (ApuestaVencidaException e) {
			fail("La apuesta esta vencida");
			e.printStackTrace();
		} catch (HipodromoException e) {
			e.printStackTrace();
			fail("Error al liquidar.");
		}
		assertEquals(this.apuestaGanador.getEstadoApuesta(),
				EstadoApuesta.LIQUIDADA);
	}

	public void testApuestaPerdidaException()
			throws ParticipanteNoCalificadoException {

		try {
			Carrera carrera = new Carrera(new ReglamentoValeTodo());
			Participante participante = new Participante(new Caballo(),
					new Jockey(), carrera);
			carrera.addParticipante(participante);

			Resultado resultado = new Resultado(2, 10);
			List<Resultado> listaResultados = new LinkedList<Resultado>();
			listaResultados.add(resultado);

			apuestaGanador = ApuestaFactory.getInstance().crear(
					ApuestaGanador.class, participante, new BigDecimal(10));
			carrera.abrirApuestas();
			carrera.cerrarApuestas();
			carrera.comenzar();
			// --Asignacion de resultados
			List<Participante> participantes = carrera.getParticipantes();
			for (int i = 0; i < participantes.size(); i++) {
				participantes.get(i).setResultado(listaResultados.get(i));
			}
			//
			carrera.terminar();
			aprobarResultados(carrera);
			carrera.aprobarResultados();

			apuestaGanador.liquidar();
			fail("El método debería haber lanzado la excepción ApuestaPerdidaException");
		} catch (ApuestaPerdidaException e) {
		} catch (TransicionInvalidaEstadoApuestaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (CarreraNoFinalizadaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ApuestaVencidaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ResultadosCarreraInvalidosException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (CarreraCerradaAApuestasException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (CantidadParticipantesInvalidaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ParticipantesEnDistintasCarrerasException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ImposibleFabricarApuestaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (TipoApuestaInvalidoException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (TransicionInvalidaEstadoParticipanteException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (HipodromoException e) {

		}

	}

}
