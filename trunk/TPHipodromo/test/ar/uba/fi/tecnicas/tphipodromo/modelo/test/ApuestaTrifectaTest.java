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
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.ApuestaFactory;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.ApuestaTrifecta;
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
public class ApuestaTrifectaTest extends TestCase {

	private Participante participante1;
	private Participante participante2;
	private Participante participante3;
	private Carrera carrera;

	private BigDecimal MONTO_APUESTA = new BigDecimal(50);

	private Apuesta apuesta1;
	private Apuesta apuesta2;

	protected void setUp() throws Exception {
		carrera = new Carrera(new ReglamentoValeTodo());

		participante1 = new Participante(new Caballo(), new Jockey(), carrera);
		participante1.getCarrera().addParticipante(participante1);

		participante2 = new Participante(new Caballo(), new Jockey(), carrera);
		participante2.getCarrera().addParticipante(participante2);

		participante3 = new Participante(new Caballo(), new Jockey(), carrera);
		participante3.getCarrera().addParticipante(participante3);

		List<Participante> listaPart = new LinkedList<Participante>();
		listaPart.add(participante1);
		listaPart.add(participante2);
		listaPart.add(participante3);

		apuesta1 = ApuestaFactory.getInstance().crear(ApuestaTrifecta.class,
				listaPart, MONTO_APUESTA);

		listaPart = new LinkedList<Participante>();
		listaPart.add(participante1);
		listaPart.add(participante3);
		listaPart.add(participante2);
		apuesta2 = ApuestaFactory.getInstance().crear(ApuestaTrifecta.class,
				listaPart, MONTO_APUESTA);
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

	protected void simularCarrera(int[] ordenes) throws HipodromoException {
		carrera.abrirApuestas();
		carrera.cerrarApuestas();
		carrera.comenzar();

		List<Resultado> listaResultados = new LinkedList<Resultado>();
		int i = 0;
		for (@SuppressWarnings("unused")
		Participante p : carrera.getParticipantes()) {
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

	public void testGanador1() {
		try {
			int[] ordenes = { 1, 2, 3 };
			simularCarrera(ordenes);
			assertTrue(apuesta1.isAcertada());
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

	public void testPerdedor1() {
		try {
			int[] ordenes = { 2, 1, 3 };
			simularCarrera(ordenes);
			assertFalse(apuesta1.isAcertada());
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

	public void testPerdedor2() {
		try {
			int[] ordenes = { 3, 1, 2 };
			simularCarrera(ordenes);
			assertFalse(apuesta1.isAcertada());
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
		} catch (ResultadosCarreraInvalidosException e) {
			fail("Falló la Simulación de la carrera.");
			e.printStackTrace();
		} catch (HipodromoException e) {
			fail(e.getMessage());
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
		} catch (HipodromoException e) {
			fail("Error al liquidar");
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
		} catch (HipodromoException e) {
		}
	}

	public void testCantidadParticipantesInvalidaException() {

		List<Participante> participantes = new LinkedList<Participante>();
		Participante participante = new Participante(new Caballo(),
				new Jockey(), carrera);
		participantes.add(participante);

		try {
			ApuestaFactory.getInstance().crear(ApuestaTrifecta.class,
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
		} catch (ResultadosCarreraInvalidosException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (HipodromoException e) {
		}

	}

	public void testParticipantesEnDistintasCarrerasException() {

		List<Participante> participantes = new LinkedList<Participante>();
		participantes.add(participante1);

		Carrera carrera2 = new Carrera(new ReglamentoValeTodo());
		participante2 = new Participante(new Caballo(), new Jockey(), carrera2);

		participantes.add(participante2);
		participantes.add(participante3);

		try {
			ApuestaFactory.getInstance().crear(ApuestaTrifecta.class,
					participantes, new BigDecimal(10));
			fail("El método debería haber lanzado la excepción ParticipantesEnDistintasCarrerasException");
		} catch (CantidadParticipantesInvalidaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ParticipantesEnDistintasCarrerasException e) {
		} catch (CarreraCerradaAApuestasException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ImposibleFabricarApuestaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (TipoApuestaInvalidoException e) {
			fail("Esta excepción no se debería haber lanzado");
		}
	}

}