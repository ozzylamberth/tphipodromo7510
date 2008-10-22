package edu.ar.uba.fi;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import edu.ar.uba.fi.model.apuestas.BolsasApuestasManager;

/**
 * @author Fernando E. Mansilla - 84567
 */
public class ApuestaDobleTest extends TestCase {

	private final int CANTIDAD_PARTICIPANTES = 3;
	private final int CANTIDAD_CARRERAS = 2;
	private BigDecimal MONTO_APUESTA = new BigDecimal(50);

	private LinkedList<Carrera> carreras;
	private Apuesta apuesta1;
	private Apuesta apuesta2;

	protected void setUp() throws Exception {
		carreras = new LinkedList<Carrera>();
		for (int j = 0; j < CANTIDAD_CARRERAS; j++) {
			Carrera carrera = new Carrera();
			for (int i = 0; i < CANTIDAD_PARTICIPANTES; i++) {

				Participante participante = new Participante(new Caballo(),
						new Jinete(), carrera);
				carrera.addParticipante(participante);
			}
			carreras.add(carrera);
		}

		LinkedList<Participante> participantesApostados = new LinkedList<Participante>();
		participantesApostados.addLast(carreras.get(0).getParticipantes()
				.get(0));
		participantesApostados.addLast(carreras.get(1).getParticipantes()
				.get(0));

		apuesta1 = ApuestaFactory.getInstance().crearApuestaDoble(
				participantesApostados, MONTO_APUESTA);

		participantesApostados = new LinkedList<Participante>();
		participantesApostados.addLast(carreras.get(0).getParticipantes()
				.get(2));
		participantesApostados.addLast(carreras.get(1).getParticipantes()
				.get(1));
		apuesta2 = ApuestaFactory.getInstance().crearApuestaDoble(
				participantesApostados, MONTO_APUESTA);

	}

	protected void simularCarrera(Carrera carreraSimulada, int[] ordenes)
			throws TransicionInvalidaEstadoCarreraException,
			ResultadosCarreraInvalidosExeption {
		carreraSimulada.cerrarApuestas();
		carreraSimulada.comenzar();

		List<ResultadoCarrera> listaResultados = new LinkedList<ResultadoCarrera>();
		int i = 0;
		for (Participante p : carreraSimulada.getParticipantes()) {
			ResultadoCarrera resultado = new ResultadoCarrera(p);
			resultado.setOrdenLlegada(ordenes[i]);
			listaResultados.add(resultado);
			i++;
		}

		carreraSimulada.terminar(listaResultados);
		carreraSimulada.aprobarResultados();
	}

	public void testGanador1() {
		try {
			simularCarrera(carreras.get(0), new int[] { 1, 2, 3 });
			simularCarrera(carreras.get(1), new int[] { 1, 2, 3 });

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
			simularCarrera(carreras.get(0), new int[] { 1, 2, 3 });
			simularCarrera(carreras.get(1), new int[] { 2, 1, 3 });

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
			simularCarrera(carreras.get(0), new int[] { 2, 1, 3 });
			simularCarrera(carreras.get(1), new int[] { 2, 1, 3 });

			assertFalse(apuesta1.isAcertada());
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ResultadosCarreraInvalidosExeption e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	public void testPerdedor3() {
		try {
			simularCarrera(carreras.get(0), new int[] { 2, 1, 3 });
			simularCarrera(carreras.get(1), new int[] { 1, 3, 2 });

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

			simularCarrera(carreras.get(0), new int[] { 1, 2, 3 });
			simularCarrera(carreras.get(1), new int[] { 1, 3, 2 });
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

}
