package edu.ar.uba.fi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import edu.ar.uba.fi.exceptions.ApuestaPerdidaException;
import edu.ar.uba.fi.exceptions.ApuestaVencidaException;
import edu.ar.uba.fi.exceptions.CarreraCerradaAApuestasException;
import edu.ar.uba.fi.exceptions.CarreraNoFinalizadaException;
import edu.ar.uba.fi.exceptions.ResultadosCarreraInvalidosExeption;
import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoApuestaException;
import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoCarreraException;
import edu.ar.uba.fi.model.Caballo;
import edu.ar.uba.fi.model.Carrera;
import edu.ar.uba.fi.model.EstadoCarrera;
import edu.ar.uba.fi.model.Jinete;
import edu.ar.uba.fi.model.Participante;
import edu.ar.uba.fi.model.ResultadoCarrera;
import edu.ar.uba.fi.model.apuestas.Apuesta;
import edu.ar.uba.fi.model.apuestas.ApuestaFactory;
import junit.framework.TestCase;

public class ExcepcionesCarreraTest extends TestCase {
	
	private Apuesta apuestaGanador;
	
	private Caballo caballo;
	private Jinete jinete;
	private Carrera carrera;
	private Participante participante;
	private ResultadoCarrera resultado;
	private List<ResultadoCarrera> resultados;
	
	protected void setUp() throws Exception {
		
		caballo = new Caballo();
		jinete = new Jinete();
		carrera = new Carrera();
		participante = new Participante(caballo, jinete, carrera);
		carrera.addParticipante(participante);
		
		apuestaGanador = ApuestaFactory.getInstance().crearApuestaGanador(participante, new BigDecimal(10));
		
		resultado = new ResultadoCarrera(participante);
		resultado.setOrdenLlegada(1);
		resultados = new ArrayList<ResultadoCarrera>();
		resultados.add(resultado);

	}

	public void testEstadosCarrera() {
		
		assertEquals(this.apuestaGanador.getParticipantes().get(0).getCarrera().getEstadoCarrera(), EstadoCarrera.ABIERTA_A_APUESTAS);
		
		try {
			carrera.cerrarApuestas();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");	
		}
		
		assertEquals(this.apuestaGanador.getParticipantes().get(0).getCarrera().getEstadoCarrera(), EstadoCarrera.CERRADA_A_APUESTAS);	

		try {
			carrera.comenzar();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");
		}
		
		assertEquals(this.apuestaGanador.getParticipantes().get(0).getCarrera().getEstadoCarrera(), EstadoCarrera.EN_CURSO);
		
		try {
			carrera.terminar(resultados);
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");
		} catch (ResultadosCarreraInvalidosExeption e) {
			fail("Esta excepci�n no se deber�a haber lanzado");
		}
		
		assertEquals(this.apuestaGanador.getParticipantes().get(0).getCarrera().getEstadoCarrera(), EstadoCarrera.A_AUDITAR);
		
		try {
			carrera.aprobarResultados();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");
		}
		
		assertEquals(this.apuestaGanador.getParticipantes().get(0).getCarrera().getEstadoCarrera(), EstadoCarrera.FINALIZADA);
		
	}
	
	public void testCancelarCarrera() {
		
		try {
			carrera.cancelar();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");
		}
		
		assertEquals(this.apuestaGanador.getParticipantes().get(0).getCarrera().getEstadoCarrera(), EstadoCarrera.CANCELADA);
	}
	
	public void testApostarACarreraCerradaAApuestas() {
		
		try {
			carrera.cerrarApuestas();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");	
		}
		
		try {
			ApuestaFactory.getInstance().crearApuestaGanador(participante, new BigDecimal(10));
			fail("El m�todo deber�a haber lanzado una excepci�n");
		} catch (CarreraCerradaAApuestasException e) {
		}
	}
	
	public void testLiquidarApuestaCarreraNoFinalizada() {
		
		try {
			carrera.cerrarApuestas();
			carrera.comenzar();
		} catch (TransicionInvalidaEstadoCarreraException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");	
		}
		
		try {
			apuestaGanador.liquidar();
			fail("El m�todo deber�a haber lanzado una excepci�n");
		} catch (CarreraNoFinalizadaException e) {
		} catch (ApuestaPerdidaException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");	
		} catch (TransicionInvalidaEstadoApuestaException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");
		} catch (ApuestaVencidaException e) {
			fail("Esta excepci�n no se deber�a haber lanzado");
		}	
	}
	
	
	/* 
	 * A continuacion se verifica que se lancen las excepciones correspondientes
	 *  para todos los posibles cambios de estado invalidos de una carrera
	 */
	public void testTransicionDeAbiertaAApuestasAEnCurso() {
		
		try {
			carrera.comenzar();
			fail("El m�todo deber�a haber lanzado una excepci�n");
		} catch (TransicionInvalidaEstadoCarreraException e) {
		}
	}
	
	public void testTransicionDeAbiertaAApuestasAAAuditar() {
				
		try {
			carrera.terminar(resultados);
			fail("El m�todo deber�a haber lanzado una excepci�n");
		} catch (TransicionInvalidaEstadoCarreraException e) {
		} catch (ResultadosCarreraInvalidosExeption e) {
		}
	}
	
	public void testTransicionDeAbiertaAApuestasAFinalizada() {
		
		try {
			carrera.aprobarResultados();
			fail("El m�todo deber�a haber lanzado una excepci�n");
		} catch (TransicionInvalidaEstadoCarreraException e) {
		}
	}
	
	public void testTransicionDeCerradaAApuestasAAAuditar() {
		
		try {
			carrera.cerrarApuestas();
			carrera.terminar(resultados);
			fail("El m�todo deber�a haber lanzado una excepci�n");
		} catch (TransicionInvalidaEstadoCarreraException e) {
		} catch (ResultadosCarreraInvalidosExeption e) {
		}
	}
	
	public void testTransicionDeCerradaAApuestasAFinalizada() {
		
		try {
			carrera.cerrarApuestas();
			carrera.aprobarResultados();
			fail("El m�todo deber�a haber lanzado una excepci�n");
		} catch (TransicionInvalidaEstadoCarreraException e) {
		}
	}
	
	public void testTransicionDeEnCursoACerradaAApuestas() {
		
		try {
			carrera.comenzar();
			carrera.cerrarApuestas();
			fail("El m�todo deber�a haber lanzado una excepci�n");
		} catch (TransicionInvalidaEstadoCarreraException e) {
		}
	}
	
	public void testTransicionDeEnCursoAFinalizada() {
		
		try {
			carrera.comenzar();
			carrera.aprobarResultados();
			fail("El m�todo deber�a haber lanzado una excepci�n");
		} catch (TransicionInvalidaEstadoCarreraException e) {
		}
	}
	
	public void testTransicionDeAAuditarACerradaAApuestas() {
		
		try {
			carrera.terminar(resultados);
			carrera.cerrarApuestas();
			fail("El m�todo deber�a haber lanzado una excepci�n");
		} catch (TransicionInvalidaEstadoCarreraException e) {
		} catch (ResultadosCarreraInvalidosExeption e) {
		}
	}
	
	public void testTransicionDeAAuditarACerradaAEnCurso() {
		
		try {
			carrera.terminar(resultados);
			carrera.comenzar();
			fail("El m�todo deber�a haber lanzado una excepci�n");
		} catch (TransicionInvalidaEstadoCarreraException e) {
		} catch (ResultadosCarreraInvalidosExeption e) {
		}
	}

	public void testTransicionDeFinalizdaACerradaAApuestas() {
	
		try {
			carrera.aprobarResultados();
			carrera.cerrarApuestas();
			fail("El m�todo deber�a haber lanzado una excepci�n");
		} catch (TransicionInvalidaEstadoCarreraException e) {
		}
	}
	
	public void testTransicionDeFinalizdaAEnCurso() {
		
		try {
			carrera.aprobarResultados();
			carrera.comenzar();
			fail("El m�todo deber�a haber lanzado una excepci�n");
		} catch (TransicionInvalidaEstadoCarreraException e) {
		}
	}
	
	public void testTransicionDeFinalizdaAAAuditar() {
		
		try {
			carrera.aprobarResultados();
			carrera.terminar(resultados);
			fail("El m�todo deber�a haber lanzado una excepci�n");
		} catch (TransicionInvalidaEstadoCarreraException e) {
		} catch (ResultadosCarreraInvalidosExeption e) {
		}
	}
	
	public void testTransicionDeFinalizdaACancelada() {
		
		try {
			carrera.aprobarResultados();
			carrera.cancelar();
			fail("El m�todo deber�a haber lanzado una excepci�n");
		} catch (TransicionInvalidaEstadoCarreraException e) {
		}
	}
	
	public void testTransicionDeCancelarACerradaAApuestas() {
		
		try {
			carrera.cancelar();
			carrera.cerrarApuestas();
			fail("El m�todo deber�a haber lanzado una excepci�n");
		} catch (TransicionInvalidaEstadoCarreraException e) {
		}
	}
	
	public void testTransicionDeCancelarAEnCurso() {
		
		try {
			carrera.cancelar();
			carrera.comenzar();
			fail("El m�todo deber�a haber lanzado una excepci�n");
		} catch (TransicionInvalidaEstadoCarreraException e) {
		}
	}
	
	public void testTransicionDeCancelarAAAuditar() {
		
		try {
			carrera.cancelar();
			carrera.terminar(resultados);
			fail("El m�todo deber�a haber lanzado una excepci�n");
		} catch (TransicionInvalidaEstadoCarreraException e) {
		} catch (ResultadosCarreraInvalidosExeption e) {
		}
	}
	
	public void testTransicionDeCancelarACerradaAFinlizada() {
		
		try {
			carrera.cancelar();
			carrera.aprobarResultados();
			fail("El m�todo deber�a haber lanzado una excepci�n");
		} catch (TransicionInvalidaEstadoCarreraException e) {
		}
	}

}
