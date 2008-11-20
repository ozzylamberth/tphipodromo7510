package ar.uba.fi.tecnicas.tphipodromo.modelo.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.ApuestaDoble;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.ApuestaFactory;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.BolsasApuestasManager;
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
			Carrera carrera = new Carrera(new ReglamentoValeTodo());
			for (int i = 0; i < CANTIDAD_PARTICIPANTES; i++) {

				Participante participante = new Participante(new Caballo(),
						new Jockey(), carrera);
				carrera.addParticipante(participante);
			}
			carreras.add(carrera);
		}

		LinkedList<Participante> participantesApostados = new LinkedList<Participante>();
		participantesApostados.addLast(carreras.get(0).getParticipantes()
				.get(0));
		participantesApostados.addLast(carreras.get(1).getParticipantes()
				.get(0));

		apuesta1 = ApuestaFactory.getInstance().crear(ApuestaDoble.class,
				participantesApostados, MONTO_APUESTA);

		participantesApostados = new LinkedList<Participante>();
		participantesApostados.addLast(carreras.get(0).getParticipantes()
				.get(2));
		participantesApostados.addLast(carreras.get(1).getParticipantes()
				.get(1));
		apuesta2 = ApuestaFactory.getInstance().crear(ApuestaDoble.class,
				participantesApostados, MONTO_APUESTA);

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

	protected void simularCarrera(Carrera carreraSimulada, int[] ordenes)
			throws HipodromoException {
		carreraSimulada.abrirApuestas();
		carreraSimulada.cerrarApuestas();
		carreraSimulada.comenzar();

		List<Resultado> listaResultados = new LinkedList<Resultado>();
		int i = 0;
		for (@SuppressWarnings("unused")
		Participante p : carreraSimulada.getParticipantes()) {
			Resultado resultado = new Resultado();
			resultado.setOrdenLlegada(ordenes[i]);
			listaResultados.add(resultado);
			i++;
		}
		// --Asignacion de resultados
		List<Participante> participantes = carreraSimulada.getParticipantes();
		for (int j = 0; j < participantes.size(); j++) {
			participantes.get(j).setResultado(listaResultados.get(j));
		}
		//
		carreraSimulada.terminar();
		aprobarResultados(carreraSimulada);
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
		} catch (ResultadosCarreraInvalidosException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (HipodromoException e) {
			e.printStackTrace();
			fail(e.getMessage());
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
		} catch (ResultadosCarreraInvalidosException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (HipodromoException e) {
			e.printStackTrace();
			fail(e.getMessage());
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
		} catch (ResultadosCarreraInvalidosException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (HipodromoException e) {
			e.printStackTrace();
			fail(e.getMessage());
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
		} catch (ResultadosCarreraInvalidosException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (HipodromoException e) {
			e.printStackTrace();
			fail(e.getMessage());
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
			simularCarrera(carreras.get(1), new int[] { 1, 3, 2 });
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Fallï¿½ la Simulaciï¿½n de la carrera.");
			e.printStackTrace();
		} catch (ResultadosCarreraInvalidosException e) {
			fail("Fallï¿½ la Simulaciï¿½n de la carrera.");
			e.printStackTrace();
		} catch (HipodromoException e) {
		}

		try {
			BigDecimal valorACobrar = this.calcularValorACobrar(apuesta1);

			assertEquals("El monto de la liquidaciï¿½n es incorrecto.",
					valorACobrar.setScale(2, RoundingMode.CEILING).compareTo(
							apuesta1.liquidar().setScale(2)) == 0, true);

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
		} catch (HipodromoException e) {
			e.printStackTrace();
			fail("Error al liquidar.");
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
		} catch (HipodromoException e) {
		}
	}

	public void testCantidadParticipantesInvalidaException() {

		List<Participante> participantes = new LinkedList<Participante>();
		Participante participante = new Participante(new Caballo(),
				new Jockey(), carreras.get(0));
		participantes.add(participante);

		try {
			ApuestaFactory.getInstance().crear(ApuestaDoble.class,
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
		} catch (HipodromoException e) {
		}

	}

}
