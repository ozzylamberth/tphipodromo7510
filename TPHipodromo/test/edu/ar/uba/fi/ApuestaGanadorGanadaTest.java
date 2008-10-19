package edu.ar.uba.fi;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import edu.ar.uba.fi.exceptions.ApuestaPerdidaException;
import edu.ar.uba.fi.model.Caballo;
import edu.ar.uba.fi.model.Carrera;
import edu.ar.uba.fi.model.Jinete;
import edu.ar.uba.fi.model.Participante;
import edu.ar.uba.fi.model.ResultadoCarrera;
import edu.ar.uba.fi.model.apuestas.Apuesta;
import edu.ar.uba.fi.model.apuestas.ApuestaFactory;
import edu.ar.uba.fi.model.apuestas.EstadoApuesta;
/**
 * Caso de Prueba 1: Liquidacion Apuesta a Ganador Ganada.
 *
 */
public class ApuestaGanadorGanadaTest extends TestCase {

	private Apuesta apuestaGanador;
	
	protected void setUp() throws Exception {
		Caballo caballo = new Caballo();
		Jinete jinete = new Jinete();
		Carrera carrera = new Carrera();
		Participante participante = new Participante(caballo, jinete, carrera);
		carrera.addParticipante(participante);
		apuestaGanador = ApuestaFactory.getInstance().crearApuestaGanador(participante);
		ResultadoCarrera resultado = new ResultadoCarrera(participante);
		resultado.setOrdenLlegada(1);
		List<ResultadoCarrera> listaResultados = new LinkedList<ResultadoCarrera>();
		listaResultados.add(resultado);
		carrera.cerrarApuestas();
		carrera.comenzar();
		carrera.terminar(listaResultados);
		carrera.aprobarResultados();
	}

	public void testIsAcertada() {
		assertTrue(apuestaGanador.isAcertada());
	}

	public void testLiquidar() {
		apuestaGanador.setMontoApostado(new BigDecimal("0"));
		assertEquals(apuestaGanador.getEstadoApuesta(), EstadoApuesta.CREADA);
		try {
			assertEquals(apuestaGanador.liquidar(), new BigDecimal("10"));
		} catch (ApuestaPerdidaException e) {
			fail("La apuesta esta perdida cuando debería estar ganada");
			e.printStackTrace();
		}
		assertEquals(apuestaGanador.getEstadoApuesta(), EstadoApuesta.LIQUIDADA);
	}
	

}
