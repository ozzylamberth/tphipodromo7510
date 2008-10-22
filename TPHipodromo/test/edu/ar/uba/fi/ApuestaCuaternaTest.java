package edu.ar.uba.fi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.ar.uba.fi.exceptions.ResultadosCarreraInvalidosExeption;
import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoCarreraException;
import edu.ar.uba.fi.model.Caballo;
import edu.ar.uba.fi.model.Carrera;
import edu.ar.uba.fi.model.Jinete;
import edu.ar.uba.fi.model.Participante;
import edu.ar.uba.fi.model.ResultadoCarrera;
import edu.ar.uba.fi.model.apuestas.Apuesta;
import edu.ar.uba.fi.model.apuestas.ApuestaFactory;
import junit.framework.TestCase;

/**
 * @author Fernando E. Mansilla - 84567
 */
public class ApuestaCuaternaTest extends TestCase {

	private final int CANTIDAD_PARTICIPANTES = 3;
	private final int CANTIDAD_CARRERAS = 4;

	private LinkedList<Carrera> carreras;
	private Apuesta apuesta;

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
		participantesApostados.addLast(carreras.get(0).getParticipantes().get(0));
		participantesApostados.addLast(carreras.get(1).getParticipantes().get(0));
		participantesApostados.addLast(carreras.get(2).getParticipantes().get(0));
		participantesApostados.addLast(carreras.get(3).getParticipantes().get(0));
		
		apuesta = ApuestaFactory.getInstance().crearApuestaCuaterna(
				participantesApostados, new BigDecimal(25));
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
			simularCarrera(carreras.get(0),new int[]{ 1, 2, 3});
			simularCarrera(carreras.get(1),new int[]{ 1, 2, 3 });
			simularCarrera(carreras.get(2),new int[]{ 1, 2, 3 });
			simularCarrera(carreras.get(3),new int[]{ 1, 2, 3 });
			
			assertTrue(apuesta.isAcertada());
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
			simularCarrera(carreras.get(0),new int[]{ 1, 2, 3});
			simularCarrera(carreras.get(1),new int[]{ 2, 1, 3 });
			simularCarrera(carreras.get(2),new int[]{ 1, 2, 3 });
			simularCarrera(carreras.get(3),new int[]{ 1, 2, 3 });

			assertFalse(apuesta.isAcertada());
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ResultadosCarreraInvalidosExeption e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}


	// public void testLiquidar(){
	// try {
	// int[] ordenes = {1, 2, 3};
	// simularCarrera(ordenes);
	// assertEquals(apuesta.getEstadoApuesta(), EstadoApuesta.CREADA);
	// assertEquals(apuesta.liquidar(), apuesta.getMontoApostado());
	// assertEquals(apuesta.getEstadoApuesta(), EstadoApuesta.LIQUIDADA);
	// } catch (ApuestaPerdidaException e) {
	// fail(e.getMessage());
	// e.printStackTrace();
	// } catch (TransicionInvalidaEstadoApuestaException e) {
	// fail(e.getMessage());
	// e.printStackTrace();
	// } catch (CarreraNoFinalizadaException e) {
	// fail(e.getMessage());
	// e.printStackTrace();
	// } catch (ApuestaVencidaException e) {
	// fail(e.getMessage());
	// e.printStackTrace();
	// } catch (TransicionInvalidaEstadoCarreraException e) {
	// fail(e.getMessage());
	// e.printStackTrace();
	// } catch (ResultadosCarreraInvalidosExeption e) {
	// fail(e.getMessage());
	// e.printStackTrace();
	// }
	//
	// }
}
