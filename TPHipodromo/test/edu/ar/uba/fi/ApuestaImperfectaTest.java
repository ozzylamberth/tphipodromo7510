package edu.ar.uba.fi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
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
import edu.ar.uba.fi.model.apuestas.BolsaApuestas;
import edu.ar.uba.fi.model.apuestas.BolsasApuestasManager;

/**
 * Test para la clase ApuestaImperfecta.
 * 
 * Hay cuatro participantes: el participante 0 llega primero,
 * los participantes 1 y 2 empatan el segundo puesto y el
 * participante 3 llega tercero.
 * 
 * Hay seis apuestas. Las cuatro primeras son acertadas
 * (distintas combinaciones de los participantes 0, 1 y 2)
 * y las dos últimas son erradas (distintas combinaciones
 * de los participantes 2 y 3). 
 * 
 */
public class ApuestaImperfectaTest extends TestCase {
	
	private Participante[] participante;
	
	private Apuesta[] apuesta;
	
	private Carrera carrera;

	protected void setUp() throws Exception {
		super.setUp();
		
		carrera = new Carrera();
		
		participante = new Participante[4];
		participante[0] = new Participante(new Caballo(), new Jinete(), carrera);
		participante[1] = new Participante(new Caballo(), new Jinete(), carrera);
		participante[2] = new Participante(new Caballo(), new Jinete(), carrera);
		participante[3] = new Participante(new Caballo(), new Jinete(), carrera);
		carrera.addParticipante(participante[0]);
		carrera.addParticipante(participante[1]);
		carrera.addParticipante(participante[2]);
		carrera.addParticipante(participante[3]);
		
		apuesta = new Apuesta[6];
		
		try {
			List<Participante> list = new LinkedList<Participante>();
			list.add(participante[0]);
			list.add(participante[1]);
			apuesta[0] = ApuestaFactory.getInstance().crearApuestaImperfecta(
					list, new BigDecimal(10));
			
			list = new LinkedList<Participante>();
			list.add(participante[1]);
			list.add(participante[0]);
			apuesta[1] = ApuestaFactory.getInstance().crearApuestaImperfecta(
					list, new BigDecimal(20));
			
			list = new LinkedList<Participante>();
			list.add(participante[1]);
			list.add(participante[2]);
			apuesta[2] = ApuestaFactory.getInstance().crearApuestaImperfecta(
					list, new BigDecimal(30));
			
			list = new LinkedList<Participante>();
			list.add(participante[2]);
			list.add(participante[1]);
			apuesta[3] = ApuestaFactory.getInstance().crearApuestaImperfecta(
					list, new BigDecimal(40));
			
			list = new LinkedList<Participante>();
			list.add(participante[2]);
			list.add(participante[3]);
			apuesta[4] = ApuestaFactory.getInstance().crearApuestaImperfecta(
					list, new BigDecimal(50));
			
			list = new LinkedList<Participante>();
			list.add(participante[3]);
			list.add(participante[2]);
			apuesta[5] = ApuestaFactory.getInstance().crearApuestaImperfecta(
					list, new BigDecimal(60));
			
		} catch(CarreraCerradaAApuestasException ex) {
			fail("Cuando se quizo apostar la carrera estaba cerrada a apuestas.");
			ex.printStackTrace();
		}
		
		List<ResultadoCarrera> resultados = new LinkedList<ResultadoCarrera>();
		
		resultados.add(new ResultadoCarrera(participante[0], 1));
		resultados.add(new ResultadoCarrera(participante[1], 2));
		resultados.add(new ResultadoCarrera(participante[2], 2));
		resultados.add(new ResultadoCarrera(participante[3], 3));
		
		try {
			carrera.cerrarApuestas();
			carrera.comenzar();
			carrera.terminar(resultados);
			carrera.aprobarResultados();
		} catch(TransicionInvalidaEstadoCarreraException ex) {
			fail("Cuando se quizo simular la carrera hubo error de transición de estados.");
			ex.printStackTrace();
		} catch(ResultadosCarreraInvalidosExeption ex) {
			fail("Cuando se quizo simular la carrera se pusieron resultados inválidos.");
			ex.printStackTrace();
		}
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testIsAcertada() {
		assertTrue("isAcertada dio false para la primera apuesta acertada", apuesta[0].isAcertada());
		assertTrue("isAcertada dio false para la primera apuesta acertada", apuesta[1].isAcertada());
		assertTrue("isAcertada dio false para la primera apuesta acertada", apuesta[2].isAcertada());
		assertTrue("isAcertada dio false para la primera apuesta acertada", apuesta[3].isAcertada());
		assertFalse("isAcertada dio true para la primera apuesta no acertada", apuesta[4].isAcertada());
		assertFalse("isAcertada dio true para la primera apuesta no acertada", apuesta[5].isAcertada());
	}
	
	private BigDecimal getTotalApostado() {
		BigDecimal totalApostado = new BigDecimal(0);
		for (int i = 0; i < this.apuesta.length; i++) {
			totalApostado = totalApostado.add(apuesta[i].getMontoApostado());
		}
		return totalApostado;
	}
	
	private BigDecimal getTotalGanadores() {
		BigDecimal totalGanadores = new BigDecimal(0);
		for (int i = 0; i < 4; i++) { // hay 4 ganadores
			totalGanadores = totalGanadores.add(apuesta[i].getMontoApostado());
		}
		return totalGanadores;
	}
	
	private BigDecimal calcularDividendo() {
		BigDecimal totalApostado = this.getTotalApostado();
		BigDecimal totalGanadores = this.getTotalGanadores();
		BigDecimal porcentaje = new BigDecimal(1).subtract(BolsasApuestasManager.porcentajeComisionHipodromo);
		BigDecimal totalARepartir = totalApostado.multiply(porcentaje);
		BigDecimal dividendo = totalARepartir.divide(totalGanadores, BolsaApuestas.DECIMALES, BolsaApuestas.ROUNDING_MODE);
		// si el dividendo de menor que uno, se retorna 1
		if (new BigDecimal(1).compareTo(dividendo) > 0) {
			return new BigDecimal(1);
		} else {
			return dividendo;
		}
	}
	
	private BigDecimal calcularGanancia(Apuesta apuesta) {
		BigDecimal dividendo = this.calcularDividendo();
		BigDecimal ganancia = dividendo.multiply(apuesta.getMontoApostado().divide(apuesta.getValorBase())).setScale(2, RoundingMode.CEILING);
		// si la ganancia de la apuesta es menor al monto apostado
		if (apuesta.getMontoApostado().compareTo(ganancia) > 0) {
			return apuesta.getMontoApostado();
		} else {
			return ganancia;
		}
	}
	
	public void testLiquidar() {
		
		try {
			assertEquals("El monto de la liquidación es incorrecto.",
					this.calcularGanancia(apuesta[0]), 
					apuesta[0].liquidar());
			
			assertEquals("El monto de la liquidación es incorrecto.",
					this.calcularGanancia(apuesta[1]), 
					apuesta[1].liquidar());
			
			assertEquals("El monto de la liquidación es incorrecto.",
					this.calcularGanancia(apuesta[2]), 
					apuesta[2].liquidar());
			
			assertEquals("El monto de la liquidación es incorrecto.",
					this.calcularGanancia(apuesta[3]), 
					apuesta[3].liquidar());
		} catch (CarreraNoFinalizadaException e) {
			fail("La carrera no había terminado cuando se intentó liquidar la apuesta.");
			e.printStackTrace();
		} catch (ApuestaPerdidaException e) {
			fail("Se intentó liquidar una apuesta perdida.");
			e.printStackTrace();
		} catch (TransicionInvalidaEstadoApuestaException e) {
			fail("Transición de estado inválida al querer liquidar la apuesta.");
			e.printStackTrace();
		} catch (ApuestaVencidaException e) {
			fail("La apuesta estaba vencida cuando se la quizo liquidar.");
			e.printStackTrace();
		}
		
		try {
			assertEquals("El monto de la liquidación es incorrecto.",
					this.calcularGanancia(apuesta[2]), 
					apuesta[4].liquidar());
			
			assertEquals("El monto de la liquidación es incorrecto.",
					this.calcularGanancia(apuesta[3]), 
					apuesta[5].liquidar());
		} catch (CarreraNoFinalizadaException e) {
			fail("La carrera no había terminado cuando se intentó liquidar la apuesta.");
			e.printStackTrace();
		} catch (ApuestaPerdidaException e) {
			// no hago nada porque es el camino correcto
		} catch (TransicionInvalidaEstadoApuestaException e) {
			fail("Transición de estado inválida al querer liquidar la apuesta.");
			e.printStackTrace();
		} catch (ApuestaVencidaException e) {
			fail("La apuesta estaba vencida cuando se la quizo liquidar.");
			e.printStackTrace();
		}
		
	}
	
	public void testCantidadParticipantesInvalidaException() {

		List<Participante> participantes = new LinkedList<Participante>();
		Participante participante = new Participante(new Caballo(), new Jinete(), carrera);
		participantes.add(participante);
		
		try {
			ApuestaFactory.getInstance().crearApuestaImperfecta(participantes, new BigDecimal(10));
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
			apuesta[5].liquidar();
			fail("El método debería haber lanzado la excepción ApuestaPerdidaException");
		} catch (ApuestaPerdidaException e) {
		} catch (TransicionInvalidaEstadoApuestaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (CarreraNoFinalizadaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ApuestaVencidaException e) {
			fail("Esta excepción no se debería haber lanzado");
		}

	}
	
	public void testParticipantesEnDistintasCarrerasException() {

		List<Participante> participantes = new LinkedList<Participante>();
		
		Participante participante1 = new Participante(new Caballo(), new Jinete(), carrera);
		participantes.add(participante1);
		
		Carrera carrera2 = new Carrera();
		
		Participante participante2 = new Participante(new Caballo(), new Jinete(), carrera2);
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
