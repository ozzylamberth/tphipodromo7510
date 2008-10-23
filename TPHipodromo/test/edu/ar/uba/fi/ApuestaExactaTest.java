package edu.ar.uba.fi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.ar.uba.fi.exceptions.ApuestaPerdidaException;
import edu.ar.uba.fi.exceptions.ApuestaVencidaException;
import edu.ar.uba.fi.exceptions.CantidadParticipantesInvalidaException;
import edu.ar.uba.fi.exceptions.CarreraCerradaAApuestasException;
import edu.ar.uba.fi.exceptions.CarreraNoFinalizadaException;
import edu.ar.uba.fi.exceptions.ParticipantesEnDistintasCarrerasException;
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
import junit.framework.TestCase;

public class ApuestaExactaTest extends TestCase {
	
	private Participante participante1;	
	private Participante participante2;
	private Carrera carrera;
	private Apuesta apuesta;
	
	
	protected void setUp() throws Exception {
		carrera = new Carrera();
		participante1 = new Participante(new Caballo(), new Jinete(), carrera);
		participante1.getCarrera().addParticipante(participante1);
		
		participante2 = new Participante(new Caballo(), new Jinete(), carrera);
		participante2.getCarrera().addParticipante(participante2);
		
		List<Participante> listaPart = new ArrayList<Participante>(2);
		listaPart.add(participante1);
		listaPart.add(participante2);		
		apuesta = ApuestaFactory.getInstance().crearApuestaExacta(listaPart, new BigDecimal(10));
	}
	
	protected void simularCarrera(int[] ordenes) throws TransicionInvalidaEstadoCarreraException, ResultadosCarreraInvalidosExeption{
		carrera.cerrarApuestas();
		carrera.comenzar();	
		
		List<ResultadoCarrera> listaResultados = new LinkedList<ResultadoCarrera>();
		int i=0;
		for(Participante p : carrera.getParticipantes()){
			ResultadoCarrera resultado = new ResultadoCarrera(p);
			resultado.setOrdenLlegada(ordenes[i]);
			listaResultados.add(resultado);
			i++;			
		}
		
		carrera.terminar(listaResultados);
		carrera.aprobarResultados();
	}
	
	public void testGanador1(){		
		try {
			int[] ordenes = {1, 2};		
			simularCarrera(ordenes);	
			assertTrue(apuesta.isAcertada());
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ResultadosCarreraInvalidosExeption e) {
			fail(e.getMessage());
			e.printStackTrace();
		}		
	}
	
	public void testPerdedor(){		
		try {
			int[] ordenes = {2, 1};		
			simularCarrera(ordenes);	
			assertFalse(apuesta.isAcertada());
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ResultadosCarreraInvalidosExeption e) {
			fail(e.getMessage());
			e.printStackTrace();
		}		
	}
	
	public void testLiquidar(){		
		try {
			int[] ordenes = {1, 2};		
			simularCarrera(ordenes);
			assertEquals(apuesta.getEstadoApuesta(), EstadoApuesta.CREADA);
			assertEquals(apuesta.liquidar(), apuesta.getMontoApostado());
			assertEquals(apuesta.getEstadoApuesta(), EstadoApuesta.LIQUIDADA);
		} catch (ApuestaPerdidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (TransicionInvalidaEstadoApuestaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (CarreraNoFinalizadaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ApuestaVencidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ResultadosCarreraInvalidosExeption e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void testCantidadParticipantesInvalidaException() {

		List<Participante> participantes = new LinkedList<Participante>();
		Participante participante = new Participante(new Caballo(), new Jinete(), carrera);
		participantes.add(participante);
		
		try {
			ApuestaFactory.getInstance().crearApuestaExacta(participantes, new BigDecimal(10));
			fail("El método debería haber lanzado la excepción CantidadParticipantesInvalidaException");
		} catch (CantidadParticipantesInvalidaException e) {
		} catch (CarreraCerradaAApuestasException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ParticipantesEnDistintasCarrerasException e) {
			fail("Esta excepción no se debería haber lanzado");
		}
	}
	
	public void testApuestaPerdidaException() {

		try {
			simularCarrera(new int[] { 2, 1, 3 });
			
			apuesta.liquidar();
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
		} catch (ResultadosCarreraInvalidosExeption e) {
			fail("Esta excepción no se debería haber lanzado");
		}

	}
	
	public void testParticipantesEnDistintasCarrerasException() {

		List<Participante> participantes = new LinkedList<Participante>();
		participantes.add(participante1);
		
		Carrera carrera2 = new Carrera();
		participante2 = new Participante(new Caballo(), new Jinete(), carrera2);
		
		participantes.add(participante2);
		
		try {
			ApuestaFactory.getInstance().crearApuestaExacta(participantes, new BigDecimal(10));
			fail("El método debería haber lanzado la excepción ParticipantesEnDistintasCarrerasException");
		} catch (CantidadParticipantesInvalidaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ParticipantesEnDistintasCarrerasException e) {
		} catch (CarreraCerradaAApuestasException e) {
			fail("Esta excepción no se debería haber lanzado");
		}
	}
}