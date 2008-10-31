package edu.ar.uba.fi;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import edu.ar.uba.fi.exceptions.ApuestaPerdidaException;
import edu.ar.uba.fi.exceptions.ApuestaVencidaException;
import edu.ar.uba.fi.exceptions.CarreraNoFinalizadaException;
import edu.ar.uba.fi.exceptions.ParticipanteNoCalificadoException;
import edu.ar.uba.fi.exceptions.ResultadosCarreraInvalidosException;
import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoApuestaException;
import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoCarreraException;
import edu.ar.uba.fi.model.Caballo;
import edu.ar.uba.fi.model.Carrera;
import edu.ar.uba.fi.model.Jinete;
import edu.ar.uba.fi.model.Participante;
import edu.ar.uba.fi.model.ReglamentoValeTodo;
import edu.ar.uba.fi.model.ResultadoCarrera;
import edu.ar.uba.fi.model.apuestas.Apuesta;
import edu.ar.uba.fi.model.apuestas.ApuestaFactory;
import edu.ar.uba.fi.model.apuestas.ApuestaSegundo;
import edu.ar.uba.fi.model.apuestas.EstadoApuesta;
/**
 *  Test de Apuesta a Segundo Ganada.
 *	Anibal Irrera
 */
public class ApuestaSegundoTest extends TestCase {

	private Apuesta apuestaSegundoGanada;
	private Apuesta apuestaSegundoPerdida;
	
	private Caballo caballo;
	private Jinete jinete;
	private Carrera carrera;
	private Participante participante;
	
	
	protected void setUp() throws Exception {	
		caballo = new Caballo();
		jinete = new Jinete();
		carrera = new Carrera(new ReglamentoValeTodo());
		participante = new Participante(caballo, jinete, carrera);
		carrera.addParticipante(participante);
		apuestaSegundoGanada = ApuestaFactory.getInstance().crear(
				ApuestaSegundo.class, participante, new BigDecimal(30));
		apuestaSegundoPerdida = ApuestaFactory.getInstance().crear(
				ApuestaSegundo.class, participante, new BigDecimal(40));
	}

	public void testIsAcertada() throws ParticipanteNoCalificadoException {
		
		List<ResultadoCarrera> listaResultados;
		ResultadoCarrera resultado;
		int i = 1;
		while ( i <= 2 )
		{
			carrera = new Carrera(new ReglamentoValeTodo());	
			carrera.addParticipante(participante);
			resultado = new ResultadoCarrera(participante);
			resultado.setOrdenLlegada(i++);
			listaResultados = new LinkedList<ResultadoCarrera>();
			listaResultados.add(resultado);
			try {
				carrera.cerrarApuestas();
				carrera.comenzar();
				carrera.terminar(listaResultados);
				carrera.aprobarResultados();
			} catch (ResultadosCarreraInvalidosException e) {
				fail("Los resultados de la carrera no son vï¿½lidos");	
				e.printStackTrace();
			} catch (TransicionInvalidaEstadoCarreraException e) {
				fail("El Estado de la carrera no es vï¿½lido");	
				e.printStackTrace();
			}
			assertTrue(this.apuestaSegundoGanada.isAcertada());
		}
	}
	public void testIsNotAcertada() throws ParticipanteNoCalificadoException {
		
		List<ResultadoCarrera> listaResultados;
		ResultadoCarrera resultado;
		
		carrera = new Carrera(new ReglamentoValeTodo());	
		carrera.addParticipante(participante);
		resultado = new ResultadoCarrera(participante);
		resultado.setOrdenLlegada(5);
		listaResultados = new LinkedList<ResultadoCarrera>();
		listaResultados.add(resultado);
		try {
			carrera.cerrarApuestas();
			carrera.comenzar();
			carrera.terminar(listaResultados);
			carrera.aprobarResultados();
		} catch (ResultadosCarreraInvalidosException e) {
			fail("Los resultados de la carrera no son vï¿½lidos");	
			e.printStackTrace();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("El Estado de la carrera no es vï¿½lido");	
			e.printStackTrace();
		}
		assertFalse(this.apuestaSegundoPerdida.isAcertada());
	}
	

	public void testLiquidarSiEsPrimero() {
		
		ResultadoCarrera resultado = new ResultadoCarrera(participante);
		resultado.setOrdenLlegada(1);
		List<ResultadoCarrera> listaResultados = new LinkedList<ResultadoCarrera>();
		listaResultados.add(resultado);	
		try {
			carrera.cerrarApuestas();
			carrera.comenzar();
			carrera.terminar(listaResultados);
			carrera.aprobarResultados();
		} catch (ResultadosCarreraInvalidosException e) {
			fail("Los resultados de la carrera no son vï¿½lidos");	
			e.printStackTrace();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("El Estado de la carrera no es vï¿½lido");	
			e.printStackTrace();
		}	
		assertEquals(this.apuestaSegundoGanada.getEstadoApuesta(), EstadoApuesta.CREADA);
		try {
			assertEquals(this.apuestaSegundoGanada.liquidar().compareTo(this.apuestaSegundoGanada.getMontoApostado()) == 0, true);
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
		}
		assertEquals(this.apuestaSegundoGanada.getEstadoApuesta(), EstadoApuesta.LIQUIDADA);
	}
	
	public void testLiquidarSiEsSegundo() {
			
		ResultadoCarrera resultado = new ResultadoCarrera(participante);
		resultado.setOrdenLlegada(2);
		List<ResultadoCarrera> listaResultados = new LinkedList<ResultadoCarrera>();
		listaResultados.add(resultado);
		try {
			carrera.cerrarApuestas();
			carrera.comenzar();
			carrera.terminar(listaResultados);
			carrera.aprobarResultados();
		} catch (ResultadosCarreraInvalidosException e) {
			fail("Los resultados de la carrera no son vï¿½lidos");	
			e.printStackTrace();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("El Estado de la carrera no es vï¿½lido");	
			e.printStackTrace();
		}	
		assertEquals(this.apuestaSegundoGanada.getEstadoApuesta(), EstadoApuesta.CREADA);
		try {
			assertEquals(this.apuestaSegundoGanada.liquidar().compareTo(this.apuestaSegundoGanada.getMontoApostado()) == 0, true);
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
		}
		assertEquals(this.apuestaSegundoGanada.getEstadoApuesta(), EstadoApuesta.LIQUIDADA);
	}
	
	public void testApuestaPerdidaException() throws ParticipanteNoCalificadoException {
		
		List<ResultadoCarrera> listaResultados;
		ResultadoCarrera resultado;
		
		carrera = new Carrera(new ReglamentoValeTodo());	
		carrera.addParticipante(participante);
		resultado = new ResultadoCarrera(participante);
		resultado.setOrdenLlegada(5);
		listaResultados = new LinkedList<ResultadoCarrera>();
		listaResultados.add(resultado);
		
		try {
			carrera.cerrarApuestas();
			carrera.comenzar();
			carrera.terminar(listaResultados);
			carrera.aprobarResultados();
			
			apuestaSegundoPerdida.liquidar();
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
		}

	}
	
}