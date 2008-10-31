package edu.ar.uba.fi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import edu.ar.uba.fi.exceptions.ResultadosCarreraInvalidosExeption;
import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoCarreraException;
import edu.ar.uba.fi.model.Caballo;
import edu.ar.uba.fi.model.Carrera;
import edu.ar.uba.fi.model.Jinete;
import edu.ar.uba.fi.model.Participante;
import edu.ar.uba.fi.model.ResultadoCarrera;
import edu.ar.uba.fi.model.apuestas.Apuesta;
import edu.ar.uba.fi.model.apuestas.ApuestaFactory;

/**
 * @author Fernando E. Mansilla - 84567
 */
public class ApuestaTrifectaTest extends TestCase {
	
	private Participante participante1;	
	private Participante participante2;
	private Participante participante3;
	private Carrera carrera;
	private Apuesta apuesta;
	
	
	protected void setUp() throws Exception {
		carrera = new Carrera();
		
		participante1 = new Participante(new Caballo(), new Jinete(), carrera);
		participante1.getCarrera().addParticipante(participante1);
		
		participante2 = new Participante(new Caballo(), new Jinete(), carrera);
		participante2.getCarrera().addParticipante(participante2);

		participante3 = new Participante(new Caballo(), new Jinete(), carrera);
		participante3.getCarrera().addParticipante(participante3);
		
		List<Participante> listaPart = new ArrayList<Participante>(2);
		listaPart.add(participante1);
		listaPart.add(participante2);
		listaPart.add(participante3);

		apuesta = ApuestaFactory.getInstance().crearApuestaTrifecta(listaPart, new BigDecimal(25));
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
			int[] ordenes = {1, 2, 3};		
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
	
	public void testPerdedor1(){		
		try {
			int[] ordenes = {2, 1, 3};		
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
	
	public void testPerdedor2(){		
		try {
			int[] ordenes = {3, 1, 2};		
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
	
//	public void testLiquidar(){		
//		try {
//			int[] ordenes = {1, 2, 3};		
//			simularCarrera(ordenes);
//			assertEquals(apuesta.getEstadoApuesta(), EstadoApuesta.CREADA);
//			assertEquals(apuesta.liquidar(), apuesta.getMontoApostado());
//			assertEquals(apuesta.getEstadoApuesta(), EstadoApuesta.LIQUIDADA);
//		} catch (ApuestaPerdidaException e) {
//			fail(e.getMessage());
//			e.printStackTrace();
//		} catch (TransicionInvalidaEstadoApuestaException e) {
//			fail(e.getMessage());
//			e.printStackTrace();
//		} catch (CarreraNoFinalizadaException e) {
//			fail(e.getMessage());
//			e.printStackTrace();
//		} catch (ApuestaVencidaException e) {
//			fail(e.getMessage());
//			e.printStackTrace();
//		} catch (TransicionInvalidaEstadoCarreraException e) {
//			fail(e.getMessage());
//			e.printStackTrace();
//		} catch (ResultadosCarreraInvalidosExeption e) {
//			fail(e.getMessage());
//			e.printStackTrace();
//		}
//
//	}
}