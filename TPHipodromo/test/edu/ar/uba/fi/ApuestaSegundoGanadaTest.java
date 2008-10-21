package edu.ar.uba.fi;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import edu.ar.uba.fi.exceptions.ApuestaPerdidaException;
import edu.ar.uba.fi.exceptions.ApuestaVencidaException;
import edu.ar.uba.fi.exceptions.CarreraNoFinalizadaException;
import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoApuestaException;
import edu.ar.uba.fi.model.Caballo;
import edu.ar.uba.fi.model.Carrera;
import edu.ar.uba.fi.model.Jinete;
import edu.ar.uba.fi.model.Participante;
import edu.ar.uba.fi.model.ResultadoCarrera;
import edu.ar.uba.fi.model.apuestas.Apuesta;
import edu.ar.uba.fi.model.apuestas.ApuestaFactory;
import edu.ar.uba.fi.model.apuestas.EstadoApuesta;
/**
 * Caso de Prueba 2: Liquidacion Apuesta a Segundo Ganada.
 *
 */
public class ApuestaSegundoGanadaTest extends TestCase {

	private Apuesta apuestaSegundo;
	
	protected void setUp() throws Exception {
		Caballo caballo = new Caballo();
		Jinete jinete = new Jinete();
		Carrera carrera = new Carrera();
		Participante participante = new Participante(caballo, jinete, carrera);
		carrera.addParticipante(participante);
		apuestaSegundo = ApuestaFactory.getInstance().crearApuestaSegundo(participante, new BigDecimal(20));
		ResultadoCarrera resultado = new ResultadoCarrera(participante);
		resultado.setOrdenLlegada(2);
		List<ResultadoCarrera> listaResultados = new LinkedList<ResultadoCarrera>();
		listaResultados.add(resultado);
		carrera.cerrarApuestas();
		carrera.comenzar();
		carrera.terminar(listaResultados);
		carrera.aprobarResultados();
	}

	public void testIsAcertada() {
		assertTrue(this.apuestaSegundo.isAcertada());
	}

	public void testLiquidarPrimero() {
		assertEquals(this.apuestaSegundo.getEstadoApuesta(), EstadoApuesta.CREADA);
		try {
			assertEquals(this.apuestaSegundo.liquidar(), this.apuestaSegundo.getMontoApostado());
		} catch (ApuestaPerdidaException e) {
			fail("La apuesta esta perdida cuando debería estar ganada");
			e.printStackTrace();
		} catch (TransicionInvalidaEstadoApuestaException e) {
			fail("No se pudo realizar la transicion de estado a liquidada");
			e.printStackTrace();
		} catch (CarreraNoFinalizadaException e) {
			fail("La carrera no esta en estado finalizada");
			e.printStackTrace();
		} catch (ApuestaVencidaException e) {
			fail("La apuesta esta vencida");
			e.printStackTrace();
		}
		assertEquals(this.apuestaSegundo.getEstadoApuesta(), EstadoApuesta.LIQUIDADA);
	}
	
	public void testLiquidarSegundo() {
			
		assertEquals(this.apuestaSegundo.getEstadoApuesta(), EstadoApuesta.CREADA);
		try {
			assertEquals(this.apuestaSegundo.liquidar(), this.apuestaSegundo.getMontoApostado());
		} catch (ApuestaPerdidaException e) {
			fail("La apuesta esta perdida cuando debería estar ganada");
			e.printStackTrace();
		} catch (TransicionInvalidaEstadoApuestaException e) {
			fail("No se pudo realizar la transicion de estado a liquidada");
			e.printStackTrace();
		} catch (CarreraNoFinalizadaException e) {
			fail("La carrera no esta en estado finalizada");
			e.printStackTrace();
		} catch (ApuestaVencidaException e) {
			fail("La apuesta esta vencida");
			e.printStackTrace();
		}
		assertEquals(this.apuestaSegundo.getEstadoApuesta(), EstadoApuesta.LIQUIDADA);
	}

}