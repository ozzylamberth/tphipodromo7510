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
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.ApuestaTercero;
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
				fail("Los resultados de la carrera no son v�lidos");
				e.printStackTrace();
			} catch (TransicionInvalidaEstadoCarreraException e) {
				fail("El Estado de la carrera no es v�lido");
				e.printStackTrace();
			} catch (HipodromoException e) {
				e.printStackTrace();
				fail("El Estado de la carrera no es v�lido");
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
			fail("Los resultados de la carrera no son v�lidos");
			e.printStackTrace();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("El Estado de la carrera no es v�lido");
			e.printStackTrace();
		} catch (HipodromoException e) {
			e.printStackTrace();
			fail("El Estado de la carrera no es v�lido");
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
			fail("Los resultados de la carrera no son v�lidos");
			e.printStackTrace();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("El Estado de la carrera no es v�lido");
			e.printStackTrace();
		} catch (HipodromoException e) {
			e.printStackTrace();
			fail("El Estado de la carrera no es v�lido");
		}
		assertEquals(this.apuestaTerceraGanada.getEstadoApuesta(),
				EstadoApuesta.CREADA);
		try {
			assertEquals(this.apuestaTerceraGanada.liquidar().compareTo(
					this.apuestaTerceraGanada.getMontoApostado()) == 0, true);
		} catch (ApuestaPerdidaException e) {
			fail("La apuesta esta perdida cuando deber�a estar ganada");
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
			fail("Los resultados de la carrera no son v�lidos");
			e.printStackTrace();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("El Estado de la carrera no es v�lido");
			e.printStackTrace();
		} catch (HipodromoException e) {
			e.printStackTrace();
			fail("El Estado de la carrera no es v�lido");
		}
		assertEquals(this.apuestaTerceraGanada.getEstadoApuesta(),
				EstadoApuesta.CREADA);
		try {
			assertEquals(this.apuestaTerceraGanada.liquidar().compareTo(
					this.apuestaTerceraGanada.getMontoApostado()) == 0, true);
		} catch (ApuestaPerdidaException e) {
			fail("La apuesta esta perdida cuando deber�a estar ganada");
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
			fail("Los resultados de la carrera no son v�lidos");
			e.printStackTrace();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("El Estado de la carrera no es v�lido");
			e.printStackTrace();
		} catch (HipodromoException e) {
			e.printStackTrace();
			fail("El Estado de la carrera no es v�lido");
		}
		assertEquals(this.apuestaTerceraGanada.getEstadoApuesta(),
				EstadoApuesta.CREADA);
		try {
			assertEquals(this.apuestaTerceraGanada.liquidar().compareTo(
					this.apuestaTerceraGanada.getMontoApostado()) == 0, true);
		} catch (ApuestaPerdidaException e) {
			fail("La apuesta esta perdida cuando deber�a estar ganada");
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
			fail("El m�todo deber�a haber lanzado la excepci�n ApuestaPerdidaException");
		} catch (ApuestaPerdidaException e) {
		} catch (TransicionInvalidaEstadoApuestaException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");
		} catch (CarreraNoFinalizadaException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");
		} catch (ApuestaVencidaException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");
		} catch (ResultadosCarreraInvalidosException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");
		} catch (HipodromoException e) {
		}

	}

}