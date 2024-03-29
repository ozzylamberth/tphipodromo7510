package ar.uba.fi.tecnicas.tphipodromo.modelo.test;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.ApuestaExacta;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.ApuestaFactory;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.EstadoApuesta;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ApuestaPerdidaException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ApuestaVencidaException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.CantidadParticipantesInvalidaException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.CarreraCerradaAApuestasException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.CarreraNoFinalizadaException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.HipodromoException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ImposibleFabricarApuestaException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ParticipantesEnDistintasCarrerasException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ResultadosCarreraInvalidosException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.TipoApuestaInvalidoException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.TransicionInvalidaEstadoApuestaException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.TransicionInvalidaEstadoCarreraException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.TransicionInvalidaEstadoParticipanteException;

import junit.framework.TestCase;

public class ApuestaExactaTest extends TestCase {
	
	private Participante participante1;	
	private Participante participante2;
	private Carrera carrera;
	private Apuesta apuesta;
	
	
	protected void setUp() throws Exception {
		carrera = new Carrera(new ReglamentoValeTodo());
		participante1 = new Participante(new Caballo(), new Jockey(), carrera);
		participante1.getCarrera().addParticipante(participante1);
		
		participante2 = new Participante(new Caballo(), new Jockey(), carrera);
		participante2.getCarrera().addParticipante(participante2);
		
		List<Participante> listaPart = new ArrayList<Participante>(2);
		listaPart.add(participante1);
		listaPart.add(participante2);		
		apuesta = ApuestaFactory.getInstance().crear(
				ApuestaExacta.class, listaPart, new BigDecimal(10));
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

	protected void simularCarrera(int[] ordenes) throws HipodromoException{
		carrera.abrirApuestas();
		carrera.cerrarApuestas();
		carrera.comenzar();	
		
		List<Resultado> listaResultados = new LinkedList<Resultado>();
		int i=0;
		for(@SuppressWarnings("unused")
		Participante p : carrera.getParticipantes()){
			Resultado resultado = new Resultado();
			resultado.setOrdenLlegada(ordenes[i]);
			listaResultados.add(resultado);
			i++;			
		}
		
		// --Asignacion de resultados
		List<Participante> participantes = carrera.getParticipantes();
		for (int j = 0; j < participantes.size(); j++) {
			participantes.get(j).setResultado(listaResultados.get(j));
		}
		//
		
		carrera.terminar();
		aprobarResultados(carrera);
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
		} catch (ResultadosCarreraInvalidosException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (HipodromoException e) {
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
		} catch (ResultadosCarreraInvalidosException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (HipodromoException e) {
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
		} catch (ResultadosCarreraInvalidosException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (HipodromoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void testCantidadParticipantesInvalidaException() {

		List<Participante> participantes = new LinkedList<Participante>();
		Participante participante = new Participante(new Caballo(), new Jockey(), carrera);
		participantes.add(participante);
		
		try {
			ApuestaFactory.getInstance().crear(
					ApuestaExacta.class, participantes, new BigDecimal(10));
			fail("El m�todo deber�a haber lanzado la excepci�n CantidadParticipantesInvalidaException");
		} catch (CantidadParticipantesInvalidaException e) {
		} catch (CarreraCerradaAApuestasException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");
		} catch (ParticipantesEnDistintasCarrerasException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");
		} catch (ImposibleFabricarApuestaException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");
		} catch (TipoApuestaInvalidoException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");
		}
	}
	
	public void testApuestaPerdidaException() {

		try {
			simularCarrera(new int[] { 2, 1, 3 });
			
			apuesta.liquidar();
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
	
	public void testParticipantesEnDistintasCarrerasException() {

		List<Participante> participantes = new LinkedList<Participante>();
		participantes.add(participante1);
		
		Carrera carrera2 = new Carrera(new ReglamentoValeTodo());
		participante2 = new Participante(new Caballo(), new Jockey(), carrera2);
		
		participantes.add(participante2);
		
		try {
			ApuestaFactory.getInstance().crear(
					ApuestaExacta.class, participantes, new BigDecimal(10));
			fail("El m�todo deber�a haber lanzado la excepci�n ParticipantesEnDistintasCarrerasException");
		} catch (CantidadParticipantesInvalidaException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");
		} catch (ParticipantesEnDistintasCarrerasException e) {
		} catch (CarreraCerradaAApuestasException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");
		} catch (ImposibleFabricarApuestaException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");
		} catch (TipoApuestaInvalidoException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");
		}
	}
}