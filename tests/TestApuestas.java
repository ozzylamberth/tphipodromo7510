package tests;

import java.math.BigDecimal;

import model.*;
import junit.framework.TestCase;

public class TestApuestas extends TestCase {
	
	/*
	public TestApuestas(String nombre)
	{
		super(nombre);
	}
	
	public static void main(String args[])
	{
		junit.textui.TestRunner.run(TestApuestas.class);
	}
	*/
	
	private Ganador apuestaGanador;
	private Segundo apuestaSegundo;
	private Tercero apuestaTercero;
	private Exacta apuestaExacta;
	private Imperfecta apuestaImperfecta;
	private Doble apuestaDoble;
	private Triplo apuestaTriplo;
	private Cuaterna apuestaCuaterna;
	private Trifecta apuestaTrifecta;
	
	
	private Caballo caballo;
	private Jinete jinete;
	private Resultado[] resultados;
	private Resultado resultado;
	private Carrera carrera;

	public void setUp() throws Exception {
		super.setUp();
		
		resultados = new Resultado[4];
		caballo = new Caballo();
		jinete = new Jinete();
		
		resultado = new Resultado(new Participante(caballo,jinete));
		resultado.setOrdenLlegada(1);
		resultados[0] = resultado;
		
		resultado = new Resultado(new Participante(caballo,jinete));
		resultado.setOrdenLlegada(2);
		resultados[1] = resultado;
		
		resultado = new Resultado(new Participante(caballo,jinete));
		resultado.setOrdenLlegada(3);
		resultados[2] = resultado;
		
		resultado = new Resultado(new Participante(caballo,jinete));
		resultado.setOrdenLlegada(4);
		resultados[3] = resultado;
		
		apuestaGanador = new Ganador();
		apuestaGanador.setMontoApostado(50);
		carrera = new Carrera();
		carrera.setDividendo(new BigDecimal(12.7));
	}
	
	public void testApuestaGanador() {

		// el valor base valor base de la apuesta a ganador es $1.
		// el importe a cobrar debe ser (12.7 * 50) / 1 = 635
		assertEquals(apuestaGanador.liquidar(),635);
	}
	
	public void testApuestaSegundo() {
	}
	
	public void testApuestaTercera() {
	}
	
	public void testApuestaExacta() {
	}
	
	public void testApuestaImperfecta() {
	}
	
	public void testApuestaDoble() {
	}
	
	public void testApuestaTriplo() {
	}
	
	public void testApuestaCuaterna() {
	}
	
	public void testApuestaTrifecta() {
	}
}
