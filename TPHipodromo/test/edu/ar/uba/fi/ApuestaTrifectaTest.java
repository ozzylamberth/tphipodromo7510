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
import edu.ar.uba.fi.model.apuestas.BolsasApuestasManager;

/**
 * @author Fernando E. Mansilla - 84567
 */
public class ApuestaTrifectaTest extends TestCase {

	private Participante participante1;
	private Participante participante2;
	private Participante participante3;
	private Carrera carrera;

	private BigDecimal MONTO_APUESTA = new BigDecimal(50);

	private Apuesta apuesta1;
	private Apuesta apuesta2;

	protected void setUp() throws Exception {
		carrera = new Carrera();

		participante1 = new Participante(new Caballo(), new Jinete(), carrera);
		participante1.getCarrera().addParticipante(participante1);

		participante2 = new Participante(new Caballo(), new Jinete(), carrera);
		participante2.getCarrera().addParticipante(participante2);

		participante3 = new Participante(new Caballo(), new Jinete(), carrera);
		participante3.getCarrera().addParticipante(participante3);

		List<Participante> listaPart = new LinkedList<Participante>();
		listaPart.add(participante1);
		listaPart.add(participante2);
		listaPart.add(participante3);

		apuesta1 = ApuestaFactory.getInstance().crearApuestaTrifecta(listaPart,
				MONTO_APUESTA);

		listaPart = new LinkedList<Participante>();
		listaPart.add(participante1);
		listaPart.add(participante3);
		listaPart.add(participante2);
		apuesta2 = ApuestaFactory.getInstance().crearApuestaTrifecta(listaPart,
				MONTO_APUESTA);
	}

	protected void simularCarrera(int[] ordenes)
			throws TransicionInvalidaEstadoCarreraException,
			ResultadosCarreraInvalidosExeption {
		carrera.cerrarApuestas();
		carrera.comenzar();

		List<ResultadoCarrera> listaResultados = new LinkedList<ResultadoCarrera>();
		int i = 0;
		for (Participante p : carrera.getParticipantes()) {
			ResultadoCarrera resultado = new ResultadoCarrera(p);
			resultado.setOrdenLlegada(ordenes[i]);
			listaResultados.add(resultado);
			i++;
		}

		carrera.terminar(listaResultados);
		carrera.aprobarResultados();
	}

	public void testGanador1() {
		try {
			int[] ordenes = { 1, 2, 3 };
			simularCarrera(ordenes);
			assertTrue(apuesta1.isAcertada());
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ResultadosCarreraInvalidosExeption e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	public void testPerdedor1() {
		try {
			int[] ordenes = { 2, 1, 3 };
			simularCarrera(ordenes);
			assertFalse(apuesta1.isAcertada());
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ResultadosCarreraInvalidosExeption e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	public void testPerdedor2() {
		try {
			int[] ordenes = { 3, 1, 2 };
			simularCarrera(ordenes);
			assertFalse(apuesta1.isAcertada());
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ResultadosCarreraInvalidosExeption e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	public void testLiquidar() {
		// Dos apuestas de MONTO_APUESTA donde solo 1 es acertada.
		BigDecimal montoApostado = MONTO_APUESTA.multiply(new BigDecimal(2));
		BigDecimal porcentajeARepartir = BigDecimal.ONE
				.subtract(BolsasApuestasManager.porcentajeComisionHipodromo);
		BigDecimal totalARepartir = montoApostado.multiply(porcentajeARepartir);
		BigDecimal dividendo = totalARepartir.divide(MONTO_APUESTA, 2,
				BigDecimal.ROUND_CEILING);
		if (BigDecimal.ONE.compareTo(dividendo) > 0) {
			dividendo = BigDecimal.ONE.setScale(2);

		}

		try {

			simularCarrera(new int[] { 1, 2, 3 });
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Falló la Simulación de la carrera.");
			e.printStackTrace();
		} catch (ResultadosCarreraInvalidosExeption e) {
			fail("Falló la Simulación de la carrera.");
			e.printStackTrace();
		}

		try {
			BigDecimal valorACobrar = dividendo.multiply(apuesta1
					.getMontoApostado().divide(apuesta1.getValorBase()));

			assertEquals("El monto de la liquidación es incorrecto.",
					valorACobrar.setScale(2, RoundingMode.CEILING), apuesta1
							.liquidar().setScale(2));

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
			BigDecimal valorACobrar = dividendo.multiply(apuesta2
					.getMontoApostado().divide(apuesta2.getValorBase()));

			assertEquals("El monto de la liquidación es incorrecto.",
					valorACobrar.setScale(2, RoundingMode.CEILING), apuesta2
							.liquidar().setScale(2));

		} catch (CarreraNoFinalizadaException e) {
			fail("La carrera no había terminado cuando se intentó liquidar la apuesta.");
			e.printStackTrace();
		} catch (ApuestaPerdidaException e) {
			assertTrue(true);
			// e.printStackTrace();
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
			ApuestaFactory.getInstance().crearApuestaTrifecta(participantes, new BigDecimal(10));
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
			
			apuesta1.liquidar();
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
		participantes.add(participante3);
		
		try {
			ApuestaFactory.getInstance().crearApuestaTrifecta(participantes, new BigDecimal(10));
			fail("El método debería haber lanzado la excepción ParticipantesEnDistintasCarrerasException");
		} catch (CantidadParticipantesInvalidaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ParticipantesEnDistintasCarrerasException e) {
		} catch (CarreraCerradaAApuestasException e) {
			fail("Esta excepción no se debería haber lanzado");
		}
	}

	
	
}