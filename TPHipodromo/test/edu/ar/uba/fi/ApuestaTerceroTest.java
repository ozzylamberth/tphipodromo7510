package edu.ar.uba.fi;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
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
import edu.ar.uba.fi.model.EstadoParticipante;
import edu.ar.uba.fi.model.Jockey;
import edu.ar.uba.fi.model.Participante;
import edu.ar.uba.fi.model.ReglamentoValeTodo;
import edu.ar.uba.fi.model.Resultado;
import edu.ar.uba.fi.model.apuestas.Apuesta;
import edu.ar.uba.fi.model.apuestas.ApuestaFactory;
import edu.ar.uba.fi.model.apuestas.ApuestaTercero;
import edu.ar.uba.fi.model.apuestas.EstadoApuesta;

/**
 * Test de Apuesta a Tercero Ganada. Anibal Irrera
 */
public class ApuestaTerceroTest extends TestCase {

	private Apuesta apuestaTerceraGanada;
	private Apuesta apuestaTerceraPerdida;

	private Caballo caballo;
	private Jockey jockey;
	private Carrera carrera;
	private Participante participante;

	protected void setUp() throws Exception {
		caballo = new Caballo();
		jockey = new Jockey();
		carrera = new Carrera(new ReglamentoValeTodo());
		participante = new Participante(caballo, jockey, carrera);
		carrera.addParticipante(participante);
		apuestaTerceraGanada = ApuestaFactory.getInstance().crear(
				ApuestaTercero.class, participante, new BigDecimal(30));
		apuestaTerceraPerdida = ApuestaFactory.getInstance().crear(
				ApuestaTercero.class, participante, new BigDecimal(30));
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

	public void testIsAcertada() throws ParticipanteNoCalificadoException {
		Apuesta apuestaTerceraGanada = null;
		Participante participante;
		int i = 1;
		while (i <= 3) {
			carrera = new Carrera(new ReglamentoValeTodo());
			participante = new Participante(caballo, jockey, carrera);
			try {
				apuestaTerceraGanada = ApuestaFactory.getInstance().crear(
						ApuestaTercero.class, participante, new BigDecimal(30));
			} catch (ImposibleFabricarApuestaException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (CantidadParticipantesInvalidaException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (CarreraCerradaAApuestasException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParticipantesEnDistintasCarrerasException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (TipoApuestaInvalidoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				carrera.addParticipante(participante);
				Resultado resultado = new Resultado();
				resultado.setOrdenLlegada(i++);
				List<Resultado> listaResultados = new LinkedList<Resultado>();
				listaResultados.add(resultado);
				carrera.abrirApuestas();
				carrera.cerrarApuestas();
				carrera.comenzar();
				// --Asignacion de resultados
				List<Participante> participantes = carrera.getParticipantes();
				for (int j = 0; j < participantes.size(); j++) {
					participantes.get(j).setResultado(listaResultados.get(j));
				}
				//
				carrera.terminar();
				aprobarResultados(carrera);
				carrera.aprobarResultados();
				assertTrue(apuestaTerceraGanada.isAcertada());

			} catch (ResultadosCarreraInvalidosException e) {
				fail("Los resultados de la carrera no son vï¿½lidos");
				e.printStackTrace();
			} catch (TransicionInvalidaEstadoCarreraException e) {
				fail("El Estado de la carrera no es vï¿½lido");
				e.printStackTrace();
			} catch (HipodromoException e) {
				e.printStackTrace();
				fail("El Estado de la carrera no es vï¿½lido");
			}

		}
	}

	public void testIsNotAcertada() throws ParticipanteNoCalificadoException {

		try {

			carrera = new Carrera(new ReglamentoValeTodo());
			Participante participante = new Participante(caballo, jockey,
					carrera);
			carrera.addParticipante(participante);
			Resultado resultado = new Resultado();
			resultado.setOrdenLlegada(4);
			List<Resultado> listaResultados = new LinkedList<Resultado>();
			listaResultados.add(resultado);
			carrera.abrirApuestas();
			carrera.cerrarApuestas();
			carrera.comenzar();
			// --Asignacion de resultados
			List<Participante> participantes = carrera.getParticipantes();
			for (int j = 0; j < participantes.size(); j++) {
				participantes.get(j).setResultado(listaResultados.get(j));
			}
			//
			carrera.terminar();
			aprobarResultados(carrera);
			carrera.aprobarResultados();

			assertFalse(this.apuestaTerceraPerdida.isAcertada());

		} catch (ResultadosCarreraInvalidosException e) {
			fail("Los resultados de la carrera no son vï¿½lidos");
			e.printStackTrace();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("El Estado de la carrera no es vï¿½lido");
			e.printStackTrace();
		} catch (HipodromoException e) {
			e.printStackTrace();
			fail("El Estado de la carrera no es vï¿½lido");
		}

	}

	public void testLiquidarSiEsPrimero() {

		Resultado resultado = new Resultado();
		resultado.setOrdenLlegada(1);
		List<Resultado> listaResultados = new LinkedList<Resultado>();
		listaResultados.add(resultado);
		try {
			carrera.abrirApuestas();
			carrera.cerrarApuestas();
			carrera.comenzar();
			// --Asignacion de resultados
			List<Participante> participantes = carrera.getParticipantes();
			for (int j = 0; j < participantes.size(); j++) {
				participantes.get(j).setResultado(listaResultados.get(j));
			}
			//
			carrera.terminar();
			aprobarResultados(carrera);
			carrera.aprobarResultados();
		} catch (ResultadosCarreraInvalidosException e) {
			fail("Los resultados de la carrera no son vï¿½lidos");
			e.printStackTrace();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("El Estado de la carrera no es vï¿½lido");
			e.printStackTrace();
		} catch (HipodromoException e) {
			e.printStackTrace();
			fail("El Estado de la carrera no es vï¿½lido");
		}
		assertEquals(this.apuestaTerceraGanada.getEstadoApuesta(),
				EstadoApuesta.CREADA);
		try {
			assertEquals(this.apuestaTerceraGanada.liquidar().compareTo(
					this.apuestaTerceraGanada.getMontoApostado()) == 0, true);
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
			fail("Error al liquidar");
			e.printStackTrace();
		}
		assertEquals(this.apuestaTerceraGanada.getEstadoApuesta(),
				EstadoApuesta.LIQUIDADA);
	}

	public void testLiquidarSiEsSegundo() {

		Resultado resultado = new Resultado();
		resultado.setOrdenLlegada(2);
		List<Resultado> listaResultados = new LinkedList<Resultado>();
		listaResultados.add(resultado);
		try {
			carrera.abrirApuestas();
			carrera.cerrarApuestas();
			carrera.comenzar();
			// --Asignacion de resultados
			List<Participante> participantes = carrera.getParticipantes();
			for (int j = 0; j < participantes.size(); j++) {
				participantes.get(j).setResultado(listaResultados.get(j));
			}
			//
			carrera.terminar();
			aprobarResultados(carrera);
			carrera.aprobarResultados();
		} catch (ResultadosCarreraInvalidosException e) {
			fail("Los resultados de la carrera no son vï¿½lidos");
			e.printStackTrace();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("El Estado de la carrera no es vï¿½lido");
			e.printStackTrace();
		} catch (HipodromoException e) {
			e.printStackTrace();
			fail("El Estado de la carrera no es vï¿½lido");
		}
		assertEquals(this.apuestaTerceraGanada.getEstadoApuesta(),
				EstadoApuesta.CREADA);
		try {
			assertEquals(this.apuestaTerceraGanada.liquidar().compareTo(
					this.apuestaTerceraGanada.getMontoApostado()) == 0, true);
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
			fail("Error al liquidar");
			e.printStackTrace();
		}
		assertEquals(this.apuestaTerceraGanada.getEstadoApuesta(),
				EstadoApuesta.LIQUIDADA);
	}

	public void testLiquidarSiEsTercero() {

		Resultado resultado = new Resultado();
		resultado.setOrdenLlegada(3);
		List<Resultado> listaResultados = new LinkedList<Resultado>();
		listaResultados.add(resultado);
		try {
			carrera.abrirApuestas();
			carrera.cerrarApuestas();
			carrera.comenzar();
			// --Asignacion de resultados
			List<Participante> participantes = carrera.getParticipantes();
			for (int j = 0; j < participantes.size(); j++) {
				participantes.get(j).setResultado(listaResultados.get(j));
			}
			//
			carrera.terminar();
			aprobarResultados(carrera);
			carrera.aprobarResultados();
		} catch (ResultadosCarreraInvalidosException e) {
			fail("Los resultados de la carrera no son vï¿½lidos");
			e.printStackTrace();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("El Estado de la carrera no es vï¿½lido");
			e.printStackTrace();
		} catch (HipodromoException e) {
			e.printStackTrace();
			fail("El Estado de la carrera no es vï¿½lido");
		}
		assertEquals(this.apuestaTerceraGanada.getEstadoApuesta(),
				EstadoApuesta.CREADA);
		try {
			assertEquals(this.apuestaTerceraGanada.liquidar().compareTo(
					this.apuestaTerceraGanada.getMontoApostado()) == 0, true);
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
			fail("Error al liquidar");
			e.printStackTrace();
		}
		assertEquals(this.apuestaTerceraGanada.getEstadoApuesta(),
				EstadoApuesta.LIQUIDADA);
	}

	public void testApuestaPerdidaException()
			throws ParticipanteNoCalificadoException {

		try {

			List<Resultado> listaResultados;
			Resultado resultado;

			carrera = new Carrera(new ReglamentoValeTodo());
			Participante participante = new Participante(caballo, jockey,
					carrera);
			carrera.addParticipante(participante);
			resultado = new Resultado();
			resultado.setOrdenLlegada(5);
			listaResultados = new LinkedList<Resultado>();
			listaResultados.add(resultado);
			Apuesta apuestaTerceraPerdida = ApuestaFactory.getInstance().crear(
					ApuestaTercero.class, participante, new BigDecimal(30));

			carrera.abrirApuestas();
			carrera.cerrarApuestas();

			carrera.comenzar();
			// --Asignacion de resultados
			List<Participante> participantes = carrera.getParticipantes();
			for (int j = 0; j < participantes.size(); j++) {
				participantes.get(j).setResultado(listaResultados.get(j));
			}
			//
			carrera.terminar();
			aprobarResultados(carrera);
			carrera.aprobarResultados();

			apuestaTerceraPerdida.liquidar();
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
		} catch (HipodromoException e) {
			e.printStackTrace();
			fail("Esta excepción no se debería haber lanzado");
		}

	}

}