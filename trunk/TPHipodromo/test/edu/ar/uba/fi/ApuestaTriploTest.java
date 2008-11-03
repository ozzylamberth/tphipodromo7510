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
import edu.ar.uba.fi.exceptions.ImposibleFabricarApuestaException;
import edu.ar.uba.fi.exceptions.ParticipantesEnDistintasCarrerasException;
import edu.ar.uba.fi.exceptions.ResultadosCarreraInvalidosException;
import edu.ar.uba.fi.exceptions.TipoApuestaInvalidoException;
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
import edu.ar.uba.fi.model.apuestas.ApuestaTriplo;
import edu.ar.uba.fi.model.apuestas.BolsasApuestasManager;

/**
 * @author Fernando E. Mansilla - 84567
 */
public class ApuestaTriploTest extends TestCase {

	private final int CANTIDAD_PARTICIPANTES = 3;
	private final int CANTIDAD_CARRERAS = 3;
	private BigDecimal MONTO_APUESTA = new BigDecimal(40);

	private LinkedList<Carrera> carreras;
	private Apuesta apuesta1;
	private Apuesta apuesta2;

	protected void setUp() throws Exception {
		carreras = new LinkedList<Carrera>();
		for (int j = 0; j < CANTIDAD_CARRERAS; j++) {
			Carrera carrera = new Carrera(new ReglamentoValeTodo());
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
		participantesApostados.addLast(carreras.get(2).getParticipantes()
				.get(0));

		apuesta1 = ApuestaFactory.getInstance().crear(ApuestaTriplo.class,
				participantesApostados, MONTO_APUESTA);

		participantesApostados = new LinkedList<Participante>();
		participantesApostados.addLast(carreras.get(0).getParticipantes()
				.get(0));
		participantesApostados.addLast(carreras.get(1).getParticipantes()
				.get(2));
		participantesApostados.addLast(carreras.get(2).getParticipantes()
				.get(1));

		apuesta2 = ApuestaFactory.getInstance().crear(ApuestaTriplo.class,
				participantesApostados, MONTO_APUESTA);
	}

	protected void simularCarrera(Carrera carreraSimulada, int[] ordenes)
			throws TransicionInvalidaEstadoCarreraException,
			ResultadosCarreraInvalidosException {
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
			simularCarrera(carreras.get(2), new int[] { 1, 2, 3 });

			assertTrue(apuesta1.isAcertada());
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ResultadosCarreraInvalidosException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	public void testPerdedor1() {
		try {
			simularCarrera(carreras.get(0), new int[] { 1, 2, 3 });
			simularCarrera(carreras.get(1), new int[] { 2, 1, 3 });
			simularCarrera(carreras.get(2), new int[] { 1, 2, 3 });

			assertFalse(apuesta1.isAcertada());
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ResultadosCarreraInvalidosException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	public void testPerdedor2() {
		try {
			simularCarrera(carreras.get(0), new int[] { 1, 2, 3 });
			simularCarrera(carreras.get(1), new int[] { 3, 1, 2 });
			simularCarrera(carreras.get(2), new int[] { 2, 1, 3 });

			assertFalse(apuesta1.isAcertada());
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ResultadosCarreraInvalidosException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	public void testPerdedor3() {
		try {
			simularCarrera(carreras.get(0), new int[] { 2, 1, 3 });
			simularCarrera(carreras.get(1), new int[] { 1, 3, 2 });
			simularCarrera(carreras.get(2), new int[] { 3, 1, 2 });

			assertFalse(apuesta1.isAcertada());
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ResultadosCarreraInvalidosException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	private BigDecimal calcularDividendo() {
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
		return dividendo;
	}

	private BigDecimal calcularValorACobrar(Apuesta apuesta) {
		BigDecimal valorACobrar = this.calcularDividendo().multiply(
				apuesta.getMontoApostado().divide(apuesta.getValorBase()));
		// si valor a cobrar es menor al monto apostado
		if (apuesta.getMontoApostado().compareTo(valorACobrar) > 0) {
			return apuesta.getMontoApostado();
		} else {
			return valorACobrar;
		}
	}

	public void testLiquidar() {

		try {
			simularCarrera(carreras.get(0), new int[] { 1, 2, 3 });
			simularCarrera(carreras.get(1), new int[] { 1, 2, 3 });
			simularCarrera(carreras.get(2), new int[] { 1, 2, 3 });
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Fallï¿½ la Simulaciï¿½n de la carrera.");
			e.printStackTrace();
		} catch (ResultadosCarreraInvalidosException e) {
			fail("Fallï¿½ la Simulaciï¿½n de la carrera.");
			e.printStackTrace();
		}

		try {
			BigDecimal valorACobrar = this.calcularValorACobrar(apuesta1);

			assertEquals("El monto de la liquidaciï¿½n es incorrecto.",
					valorACobrar.setScale(2, RoundingMode.CEILING), apuesta1
							.liquidar().setScale(2));

		} catch (CarreraNoFinalizadaException e) {
			fail("La carrera no habï¿½a terminado cuando se intentï¿½ liquidar la apuesta.");
			e.printStackTrace();
		} catch (ApuestaPerdidaException e) {
			fail("Se intentï¿½ liquidar una apuesta perdida.");
			e.printStackTrace();
		} catch (TransicionInvalidaEstadoApuestaException e) {
			fail("Transiciï¿½n de estado invï¿½lida al querer liquidar la apuesta.");
			e.printStackTrace();
		} catch (ApuestaVencidaException e) {
			fail("La apuesta estaba vencida cuando se la quizo liquidar.");
			e.printStackTrace();
		}

		try {
			BigDecimal valorACobrar = this.calcularValorACobrar(apuesta2);

			assertEquals("El monto de la liquidaciï¿½n es incorrecto.",
					valorACobrar.setScale(2, RoundingMode.CEILING), apuesta2
							.liquidar().setScale(2));

		} catch (CarreraNoFinalizadaException e) {
			fail("La carrera no habï¿½a terminado cuando se intentï¿½ liquidar la apuesta.");
			e.printStackTrace();
		} catch (ApuestaPerdidaException e) {
			assertTrue(true);
			// e.printStackTrace();
		} catch (TransicionInvalidaEstadoApuestaException e) {
			fail("Transiciï¿½n de estado invï¿½lida al querer liquidar la apuesta.");
			e.printStackTrace();
		} catch (ApuestaVencidaException e) {
			fail("La apuesta estaba vencida cuando se la quizo liquidar.");
			e.printStackTrace();
		}
	}

	public void testCantidadParticipantesInvalidaException() {

		List<Participante> participantes = new LinkedList<Participante>();
		Participante participante = new Participante(new Caballo(),
				new Jinete(), carreras.get(0));
		participantes.add(participante);

		try {
			ApuestaFactory.getInstance().crear(ApuestaTriplo.class,
					participantes, new BigDecimal(10));
			fail("El método debería haber lanzado la excepción CantidadParticipantesInvalidaException");
		} catch (CantidadParticipantesInvalidaException e) {
		} catch (CarreraCerradaAApuestasException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ParticipantesEnDistintasCarrerasException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ImposibleFabricarApuestaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (TipoApuestaInvalidoException e) {
			fail("Esta excepción no se debería haber lanzado");
		}
	}

	public void testApuestaPerdidaException() {

		try {
			simularCarrera(carreras.get(0), new int[] { 2, 1, 3 });
			simularCarrera(carreras.get(1), new int[] { 1, 3, 2 });
			simularCarrera(carreras.get(2), new int[] { 3, 1, 2 });

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
		} catch (ResultadosCarreraInvalidosException e) {
			fail("Esta excepción no se debería haber lanzado");
		}

	}
}
