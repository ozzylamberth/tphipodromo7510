package edu.ar.uba.fi;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import edu.ar.uba.fi.model.apuestas.ApuestaImperfecta;
import edu.ar.uba.fi.model.apuestas.BolsaApuestasAbstracta;
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
 * @author jgrande
 * 
 */
public class ApuestaImperfectaTest extends TestCase {
	
	private Participante[] participante;
	
	private Apuesta[] apuesta;
	
	private Carrera carrera;

	protected void setUp() throws Exception {
		super.setUp();
		
		carrera = new Carrera(new ReglamentoValeTodo());
		
		participante = new Participante[4];
		participante[0] = new Participante(new Caballo(), new Jockey(), carrera);
		participante[1] = new Participante(new Caballo(), new Jockey(), carrera);
		participante[2] = new Participante(new Caballo(), new Jockey(), carrera);
		participante[3] = new Participante(new Caballo(), new Jockey(), carrera);
		carrera.addParticipante(participante[0]);
		carrera.addParticipante(participante[1]);
		carrera.addParticipante(participante[2]);
		carrera.addParticipante(participante[3]);
		
		apuesta = new Apuesta[6];
		
		try {
			List<Participante> list = new LinkedList<Participante>();
			list.add(participante[0]);
			list.add(participante[1]);
			apuesta[0] = ApuestaFactory.getInstance().crear(
					ApuestaImperfecta.class, list, new BigDecimal(10));
			
			list = new LinkedList<Participante>();
			list.add(participante[1]);
			list.add(participante[0]);
			apuesta[1] = ApuestaFactory.getInstance().crear(
					ApuestaImperfecta.class, list, new BigDecimal(20));
			
			list = new LinkedList<Participante>();
			list.add(participante[1]);
			list.add(participante[2]);
			apuesta[2] = ApuestaFactory.getInstance().crear(
					ApuestaImperfecta.class, list, new BigDecimal(30));
			
			list = new LinkedList<Participante>();
			list.add(participante[2]);
			list.add(participante[1]);
			apuesta[3] = ApuestaFactory.getInstance().crear(
					ApuestaImperfecta.class, list, new BigDecimal(40));
			
			list = new LinkedList<Participante>();
			list.add(participante[2]);
			list.add(participante[3]);
			apuesta[4] = ApuestaFactory.getInstance().crear(
					ApuestaImperfecta.class, list, new BigDecimal(50));
			
			list = new LinkedList<Participante>();
			list.add(participante[3]);
			list.add(participante[2]);
			apuesta[5] = ApuestaFactory.getInstance().crear(
					ApuestaImperfecta.class, list, new BigDecimal(60));
			
		} catch(CarreraCerradaAApuestasException ex) {
			fail("Cuando se quizo apostar la carrera estaba cerrada a apuestas.");
			ex.printStackTrace();
		}
		
		List<Resultado> resultados = new LinkedList<Resultado>();
		
		resultados.add(new Resultado(1, 10));
		resultados.add(new Resultado(2, 11));
		resultados.add(new Resultado(2, 12));
		resultados.add(new Resultado(3, 13));
		
		try {
			carrera.cerrarApuestas();
			carrera.comenzar();
			// --Asignacion de resultados
			List<Participante> participantes = carrera.getParticipantes();
			for (int j = 0; j < participantes.size(); j++) {
				participantes.get(j).setResultado(resultados.get(j));
			}
			//
			carrera.terminar();
			aprobarResultados(carrera);
			carrera.aprobarResultados();
		} catch(TransicionInvalidaEstadoCarreraException ex) {
			fail("Cuando se quizo simular la carrera hubo error de transición de estados.");
			ex.printStackTrace();
		} catch(ResultadosCarreraInvalidosException ex) {
			fail("Cuando se quizo simular la carrera se pusieron resultados inválidos.");
			ex.printStackTrace();
		} catch(HipodromoException ex) {
			fail("Cuando se quizo simular la carrera se pusieron resultados inválidos.");
			ex.printStackTrace();
		}
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
		BigDecimal dividendo = totalARepartir.divide(totalGanadores, BolsaApuestasAbstracta.DECIMALES, BolsaApuestasAbstracta.ROUNDING_MODE);
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
			apuesta[4].liquidar();
			fail("Se intentó liquidar una apuesta perdida y no lanzó excepción.");
			
			apuesta[5].liquidar();
			fail("Se intentó liquidar una apuesta perdida y no lanzó excepción.");
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
		Participante participante = new Participante(new Caballo(), new Jockey(), carrera);
		participantes.add(participante);
		
		try {
			ApuestaFactory.getInstance().crear(
					ApuestaImperfecta.class, participantes, new BigDecimal(10));
			fail("El método debería haber lanzado la excepción CantidadParticipantesInvalidaException");
		} catch (CantidadParticipantesInvalidaException e) {
		} catch (CarreraCerradaAApuestasException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ParticipantesEnDistintasCarrerasException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ImposibleFabricarApuestaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (TipoApuestaInvalidoException e) {
			fail("Esta excepción no se debería haber lanzado");		}
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
		
		Participante participante1 = new Participante(new Caballo(), new Jockey(), carrera);
		participantes.add(participante1);
		
		Carrera carrera2 = new Carrera(new ReglamentoValeTodo());
		
		Participante participante2 = new Participante(new Caballo(), new Jockey(), carrera2);
		participantes.add(participante2);
		
		try {
			ApuestaFactory.getInstance().crear(
					ApuestaImperfecta.class, participantes, new BigDecimal(10));
			fail("El método debería haber lanzado la excepción ParticipantesEnDistintasCarrerasException");
		} catch (CantidadParticipantesInvalidaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ParticipantesEnDistintasCarrerasException e) {
		} catch (CarreraCerradaAApuestasException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ImposibleFabricarApuestaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (TipoApuestaInvalidoException e) {
			fail("Esta excepción no se debería haber lanzado");		}
	}

}
