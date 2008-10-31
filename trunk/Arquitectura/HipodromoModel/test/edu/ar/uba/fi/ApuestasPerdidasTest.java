package edu.ar.uba.fi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
import junit.framework.TestCase;

public class ApuestasPerdidasTest extends TestCase {
	
	private Apuesta apuestaGanador, apuestaSegundo, apuestaTercero,
					apuestaExacta, apuestaImperfecta, apuestaTrifecta,
					apuestaDoble, apuestaTriplo, apuestaCuaterna;
	
	protected void setUp() throws Exception {
		
		Caballo caballo1 = new Caballo();
		Jinete jinete1 = new Jinete();
		
		Caballo caballo2 = new Caballo();
		Jinete jinete2 = new Jinete();
		
		Caballo caballo3 = new Caballo();
		Jinete jinete3 = new Jinete();
		
		Caballo caballo4 = new Caballo();
		Jinete jinete4 = new Jinete();
		
		Carrera carrera1 = new Carrera();
		
		Participante participante1 = new Participante(caballo1, jinete1, carrera1);
		Participante participante2 = new Participante(caballo2, jinete2, carrera1);
		Participante participante3 = new Participante(caballo3, jinete3, carrera1);
		Participante participante4 = new Participante(caballo4, jinete4, carrera1);
		
		carrera1.addParticipante(participante1);
		carrera1.addParticipante(participante2);
		carrera1.addParticipante(participante3);
		carrera1.addParticipante(participante4);
		
		ResultadoCarrera resultado1 = new ResultadoCarrera(participante1);
		resultado1.setOrdenLlegada(1);
		ResultadoCarrera resultado2 = new ResultadoCarrera(participante2);
		resultado2.setOrdenLlegada(2);
		ResultadoCarrera resultado3 = new ResultadoCarrera(participante3);
		resultado3.setOrdenLlegada(3);
		ResultadoCarrera resultado4 = new ResultadoCarrera(participante4);
		resultado4.setOrdenLlegada(4);
		
		List<ResultadoCarrera> resultados = new ArrayList<ResultadoCarrera>();
		resultados.add(resultado1);
		resultados.add(resultado2);
		resultados.add(resultado3);
		resultados.add(resultado4);
		
		carrera1.cerrarApuestas();
		carrera1.comenzar();
		carrera1.terminar(resultados);
		carrera1.aprobarResultados();
		
		apuestaGanador = ApuestaFactory.getInstance().crearApuestaGanador(participante2, new BigDecimal(10));
		apuestaSegundo = ApuestaFactory.getInstance().crearApuestaSegundo(participante3, new BigDecimal(10));
		apuestaTercero = ApuestaFactory.getInstance().crearApuestaTercero(participante4, new BigDecimal(10));
		
		List<Participante> participantes = new ArrayList<Participante>();
		participantes.add(participante2);
		participantes.add(participante3);
		
		apuestaExacta = ApuestaFactory.getInstance().crearApuestaExacta(participantes, new BigDecimal(10));
		apuestaImperfecta = ApuestaFactory.getInstance().crearApuestaImperfecta(participantes, new BigDecimal(10));
		
		List<Participante> participantesTrifecta = new ArrayList<Participante>();
		participantesTrifecta.add(participante2);
		participantesTrifecta.add(participante3);
		participantesTrifecta.add(participante4);
		
		apuestaTrifecta = ApuestaFactory.getInstance().crearApuestaTrifecta(participantesTrifecta, new BigDecimal(10));
		
		Caballo caballo5 = new Caballo();
		Jinete jinete5 = new Jinete();
		
		Carrera carrera2 = new Carrera();
		
		Participante participante5 = new Participante(caballo5, jinete5, carrera2);
		
		carrera2.addParticipante(participante5);
		
		ResultadoCarrera resultado5 = new ResultadoCarrera(participante5);
		resultado5.setOrdenLlegada(1);
		
		List<ResultadoCarrera> resultados2 = new ArrayList<ResultadoCarrera>();
		resultados2.add(resultado5);
		
		carrera2.cerrarApuestas();
		carrera2.comenzar();
		carrera2.terminar(resultados2);
		carrera2.aprobarResultados();
		
		List<Participante> participantesApuestasMultiples = new ArrayList<Participante>();
		participantesApuestasMultiples.add(participante2);
		participantesApuestasMultiples.add(participante5);
		
		apuestaDoble = ApuestaFactory.getInstance().crearApuestaDoble(participantesApuestasMultiples, new BigDecimal(10));
		
		Caballo caballo6 = new Caballo();
		Jinete jinete6 = new Jinete();
		
		Carrera carrera3 = new Carrera();
		
		Participante participante6 = new Participante(caballo6, jinete6, carrera3);
		
		carrera3.addParticipante(participante6);
		
		ResultadoCarrera resultado6 = new ResultadoCarrera(participante6);
		resultado6.setOrdenLlegada(1);
		
		List<ResultadoCarrera> resultados3 = new ArrayList<ResultadoCarrera>();
		resultados3.add(resultado6);
		
		carrera3.cerrarApuestas();
		carrera3.comenzar();
		carrera3.terminar(resultados3);
		carrera3.aprobarResultados();
		
		participantesApuestasMultiples.add(participante6);
		
		apuestaTriplo = ApuestaFactory.getInstance().crearApuestaTriplo(participantesApuestasMultiples, new BigDecimal(10));
		
		Caballo caballo7 = new Caballo();
		Jinete jinete7 = new Jinete();
		
		Carrera carrera4 = new Carrera();
		
		Participante participante7 = new Participante(caballo7, jinete7, carrera4);
		
		carrera4.addParticipante(participante7);
		
		ResultadoCarrera resultado7 = new ResultadoCarrera(participante7);
		resultado7.setOrdenLlegada(1);
		
		List<ResultadoCarrera> resultados4 = new ArrayList<ResultadoCarrera>();
		resultados4.add(resultado7);
		
		carrera4.cerrarApuestas();
		carrera4.comenzar();
		carrera4.terminar(resultados4);
		carrera4.aprobarResultados();
		
		participantesApuestasMultiples.add(participante7);
		
		apuestaCuaterna = ApuestaFactory.getInstance().crearApuestaCuaterna(participantesApuestasMultiples, new BigDecimal(10));
		
	}
	
	private void liquidar(Apuesta apuesta) {

		try {
			apuesta.liquidar();
			fail("El método debería haber lanzado una excepción");
		} catch (ApuestaPerdidaException e) {
		} catch (TransicionInvalidaEstadoApuestaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (CarreraNoFinalizadaException e) {
			fail("Esta excepción no se debería haber lanzado");
		} catch (ApuestaVencidaException e) {
			fail("Esta excepción no se debería haber lanzado");
		}

	}

	public void testLiquidacionApuestaGanadorPerdida() {
		
		liquidar(apuestaGanador);
	}
	
	public void testLiquidacionApuestaSegundoPerdida() {
		
		liquidar(apuestaSegundo);
	}
	
	public void testLiquidacionApuestaTerceroPerdida() {
		
		liquidar(apuestaTercero);
	}
	
	public void testLiquidacionApuestaExactaPerdida() {
		
		liquidar(apuestaExacta);
	}
	
	public void testLiquidacionApuestaImperfectaPerdida() {
		
		liquidar(apuestaImperfecta);
	}
	
	public void testLiquidacionApuestaTrifectaPerdida() {
		
		liquidar(apuestaTrifecta);
	}
	
	
	public void testLiquidacionApuestaDoblePerdida() {
		
		liquidar(apuestaDoble);
	}
	
	public void testLiquidacionApuestaTriploPerdida() {
		
		liquidar(apuestaTriplo);
	}
	
	public void testLiquidacionApuestaCuaternaPerdida() {
		
		liquidar(apuestaCuaterna);
	}

}
