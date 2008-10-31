package edu.ar.uba.fi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import edu.ar.uba.fi.exceptions.CantidadParticipantesInvalidaException;
import edu.ar.uba.fi.exceptions.CarreraCerradaAApuestasException;
import edu.ar.uba.fi.exceptions.ParticipantesEnDistintasCarrerasException;
import edu.ar.uba.fi.model.Caballo;
import edu.ar.uba.fi.model.Carrera;
import edu.ar.uba.fi.model.Jinete;
import edu.ar.uba.fi.model.Participante;
import edu.ar.uba.fi.model.apuestas.ApuestaFactory;
import junit.framework.TestCase;

public class ExcepcionesApuestaTest extends TestCase {
	
	private Participante participante1, participante2, participante3;
	
	protected void setUp() throws Exception {
		
		Caballo caballo1 = new Caballo();
		Jinete jinete1 = new Jinete();
		
		Carrera carrera1 = new Carrera();
		participante1 = new Participante(caballo1, jinete1, carrera1);
		carrera1.addParticipante(participante1);
		
		Carrera carrera2 = new Carrera();
		participante2 = new Participante(caballo1, jinete1, carrera2);
		carrera2.addParticipante(participante2);
		
		Carrera carrera3 = new Carrera();
		participante3 = new Participante(caballo1, jinete1, carrera3);
		carrera3.addParticipante(participante3);
			
	}
	
	public void testCantidadParticipantesInvalidaApuestaExacta() {

		List<Participante> participantes = new ArrayList<Participante>();
		participantes.add(participante1);
		
		try {
			ApuestaFactory.getInstance().crearApuestaExacta(participantes, new BigDecimal(10));
			fail("El método debería haber lanzado una excepción");
		} catch (CantidadParticipantesInvalidaException e) {
		} catch (ParticipantesEnDistintasCarrerasException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (CarreraCerradaAApuestasException e) {
			fail("Esta excepción no se debería haber lanzado");
		}
	}
	
	public void testCantidadParticipantesInvalidaApuestaImperfecta() {

		List<Participante> participantes = new ArrayList<Participante>();
		participantes.add(participante1);
		
		try {
			ApuestaFactory.getInstance().crearApuestaImperfecta(participantes, new BigDecimal(10));
			fail("El método debería haber lanzado una excepción");
		} catch (CantidadParticipantesInvalidaException e) {
		} catch (ParticipantesEnDistintasCarrerasException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (CarreraCerradaAApuestasException e) {
			fail("Esta excepción no se debería haber lanzado");
		}
	}
	
	public void testCantidadParticipantesInvalidaApuestaTrifecta() {

		List<Participante> participantes = new ArrayList<Participante>();
		participantes.add(participante1);
		
		try {
			ApuestaFactory.getInstance().crearApuestaTrifecta(participantes, new BigDecimal(10));
			fail("El método debería haber lanzado una excepción");
		} catch (CantidadParticipantesInvalidaException e) {
		} catch (ParticipantesEnDistintasCarrerasException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (CarreraCerradaAApuestasException e) {
			fail("Esta excepción no se debería haber lanzado");
		}
	}
	
	public void testCantidadParticipantesInvalidaApuestaDoble() {

		List<Participante> participantes = new ArrayList<Participante>();
		participantes.add(participante1);
		
		try {
			ApuestaFactory.getInstance().crearApuestaDoble(participantes, new BigDecimal(10));
			fail("El método debería haber lanzado una excepción");
		} catch (CantidadParticipantesInvalidaException e) {
		} catch (CarreraCerradaAApuestasException e) {
			fail("Esta excepción no se debería haber lanzado");
		}
	}
	
	public void testCantidadParticipantesInvalidaApuestaTriplo() {

		List<Participante> participantes = new ArrayList<Participante>();
		participantes.add(participante1);
		
		try {
			ApuestaFactory.getInstance().crearApuestaTriplo(participantes, new BigDecimal(10));
			fail("El método debería haber lanzado una excepción");
		} catch (CantidadParticipantesInvalidaException e) {
		} catch (CarreraCerradaAApuestasException e) {
			fail("Esta excepción no se debería haber lanzado");
		}
	}
	
	public void testCantidadParticipantesInvalidaApuestaCuaterna() {

		List<Participante> participantes = new ArrayList<Participante>();
		participantes.add(participante1);
		
		try {
			ApuestaFactory.getInstance().crearApuestaCuaterna(participantes, new BigDecimal(10));
			fail("El método debería haber lanzado una excepción");
		} catch (CantidadParticipantesInvalidaException e) {
		} catch (CarreraCerradaAApuestasException e) {
			fail("Esta excepción no se debería haber lanzado");
		}
	}
	
	
	public void testParticipantesEnDistintasCarrerasApuestaExacta() {

		List<Participante> participantes = new ArrayList<Participante>();
		participantes.add(participante1);
		participantes.add(participante2);
		
		try {
			ApuestaFactory.getInstance().crearApuestaExacta(participantes, new BigDecimal(10));
			fail("El método debería haber lanzado una excepción");
		} catch (CantidadParticipantesInvalidaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ParticipantesEnDistintasCarrerasException e) {
		} catch (CarreraCerradaAApuestasException e) {
			fail("Esta excepción no se debería haber lanzado");
		}
	}
	
	public void testParticipantesEnDistintasCarrerasApuestaImperfecta() {

		List<Participante> participantes = new ArrayList<Participante>();
		participantes.add(participante1);
		participantes.add(participante2);
		
		try {
			ApuestaFactory.getInstance().crearApuestaImperfecta(participantes, new BigDecimal(10));
			fail("El método debería haber lanzado una excepción");
		} catch (CantidadParticipantesInvalidaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ParticipantesEnDistintasCarrerasException e) {
		} catch (CarreraCerradaAApuestasException e) {
			fail("Esta excepción no se debería haber lanzado");
		}
	}
	
	public void testParticipantesEnDistintasCarrerasApuestaTrifecta() {

		List<Participante> participantes = new ArrayList<Participante>();
		participantes.add(participante1);
		participantes.add(participante2);
		participantes.add(participante3);
		
		try {
			ApuestaFactory.getInstance().crearApuestaTrifecta(participantes, new BigDecimal(10));
			fail("El método debería haber lanzado una excepción");
		} catch (CantidadParticipantesInvalidaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ParticipantesEnDistintasCarrerasException e) {
		} catch (CarreraCerradaAApuestasException e) {
			fail("Esta excepción no se debería haber lanzado");
		}
	}
	

}
