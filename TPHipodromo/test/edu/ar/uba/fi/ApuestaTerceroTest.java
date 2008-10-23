package edu.ar.uba.fi;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import edu.ar.uba.fi.exceptions.ApuestaPerdidaException;
import edu.ar.uba.fi.exceptions.ApuestaVencidaException;
import edu.ar.uba.fi.exceptions.CarreraNoFinalizadaException;
import edu.ar.uba.fi.exceptions.ResultadosCarreraInvalidosExeption;
import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoApuestaException;
import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoCarreraException;
import edu.ar.uba.fi.model.Caballo;
import edu.ar.uba.fi.model.Carrera;
import edu.ar.uba.fi.model.Jinete;
import edu.ar.uba.fi.model.Participante;
import edu.ar.uba.fi.model.ResultadoCarrera;
import edu.ar.uba.fi.model.apuestas.Apuesta;
import edu.ar.uba.fi.model.apuestas.ApuestaFactory;
import edu.ar.uba.fi.model.apuestas.EstadoApuesta;
/**
 * Test de Apuesta a Tercero Ganada.
 * Anibal Irrera
 */
public class ApuestaTerceroTest extends TestCase {

	private Apuesta apuestaTerceraGanada;
	private Apuesta apuestaTerceraPerdida;
	
	private Caballo caballo;
	private Jinete jinete;
	private Carrera carrera;
	private Participante participante;
	
	protected void setUp() throws Exception {
		caballo = new Caballo();
		jinete = new Jinete();
		carrera = new Carrera();
		participante = new Participante(caballo, jinete, carrera);
		carrera.addParticipante(participante);
		apuestaTerceraGanada = ApuestaFactory.getInstance().crearApuestaTercero(participante, new BigDecimal(30));
		apuestaTerceraPerdida = ApuestaFactory.getInstance().crearApuestaTercero(participante, new BigDecimal(30));
	}

	public void testIsAcertada() {
	
		int i = 1;
		while ( i <= 3 )
		{
			carrera = new Carrera();	
			carrera.addParticipante(participante);
			ResultadoCarrera resultado = new ResultadoCarrera(participante);
			resultado.setOrdenLlegada(i++);
			List<ResultadoCarrera> listaResultados = new LinkedList<ResultadoCarrera>();
			listaResultados.add(resultado);
			try {
				carrera.cerrarApuestas();
				carrera.comenzar();
				carrera.terminar(listaResultados);
				carrera.aprobarResultados();
			} catch (ResultadosCarreraInvalidosExeption e) {
				fail("Los resultados de la carrera no son v�lidos");	
				e.printStackTrace();
			} catch (TransicionInvalidaEstadoCarreraException e) {
				fail("El Estado de la carrera no es v�lido");	
				e.printStackTrace();
			}
			assertTrue(this.apuestaTerceraGanada.isAcertada());
		}
	}
	
	public void testIsNotAcertada() {
		
			carrera = new Carrera();	
			carrera.addParticipante(participante);
			ResultadoCarrera resultado = new ResultadoCarrera(participante);
			resultado.setOrdenLlegada(4);
			List<ResultadoCarrera> listaResultados = new LinkedList<ResultadoCarrera>();
			listaResultados.add(resultado);
			try {
				carrera.cerrarApuestas();
				carrera.comenzar();
				carrera.terminar(listaResultados);
				carrera.aprobarResultados();
			} catch (ResultadosCarreraInvalidosExeption e) {
				fail("Los resultados de la carrera no son v�lidos");	
				e.printStackTrace();
			} catch (TransicionInvalidaEstadoCarreraException e) {
				fail("El Estado de la carrera no es v�lido");	
				e.printStackTrace();
			}
			assertFalse(this.apuestaTerceraPerdida.isAcertada());
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
		} catch (ResultadosCarreraInvalidosExeption e) {
			fail("Los resultados de la carrera no son v�lidos");	
			e.printStackTrace();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("El Estado de la carrera no es v�lido");	
			e.printStackTrace();
		}	
		assertEquals(this.apuestaTerceraGanada.getEstadoApuesta(), EstadoApuesta.CREADA);
		try {
			assertEquals(this.apuestaTerceraGanada.liquidar().compareTo(this.apuestaTerceraGanada.getMontoApostado()) == 0, true);
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
		}
		assertEquals(this.apuestaTerceraGanada.getEstadoApuesta(), EstadoApuesta.LIQUIDADA);
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
		} catch (ResultadosCarreraInvalidosExeption e) {
			fail("Los resultados de la carrera no son v�lidos");	
			e.printStackTrace();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("El Estado de la carrera no es v�lido");	
			e.printStackTrace();
		}	
		assertEquals(this.apuestaTerceraGanada.getEstadoApuesta(), EstadoApuesta.CREADA);
		try {
			assertEquals(this.apuestaTerceraGanada.liquidar().compareTo(this.apuestaTerceraGanada.getMontoApostado()) == 0, true);
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
		}
		assertEquals(this.apuestaTerceraGanada.getEstadoApuesta(), EstadoApuesta.LIQUIDADA);
	}
	
	public void testLiquidarSiEsTercero() {
		
		ResultadoCarrera resultado = new ResultadoCarrera(participante);
		resultado.setOrdenLlegada(3);
		List<ResultadoCarrera> listaResultados = new LinkedList<ResultadoCarrera>();
		listaResultados.add(resultado);
		try {
			carrera.cerrarApuestas();
			carrera.comenzar();
			carrera.terminar(listaResultados);
			carrera.aprobarResultados();
		} catch (ResultadosCarreraInvalidosExeption e) {
			fail("Los resultados de la carrera no son v�lidos");	
			e.printStackTrace();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("El Estado de la carrera no es v�lido");	
			e.printStackTrace();
		}		assertEquals(this.apuestaTerceraGanada.getEstadoApuesta(), EstadoApuesta.CREADA);
		try {
			assertEquals(this.apuestaTerceraGanada.liquidar().compareTo(this.apuestaTerceraGanada.getMontoApostado()) == 0, true);
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
		}
		assertEquals(this.apuestaTerceraGanada.getEstadoApuesta(), EstadoApuesta.LIQUIDADA);
	}
}