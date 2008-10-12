package model;
import java.math.BigDecimal;
import java.util.List;

public class Carrera {

	private int distancia;
	private String estado;
	private int fecha;
	private String hora;
	private String nombre;
	private int numero;
	private BigDecimal dividendo;
	//private List resultados;
	//private List participantes;
	
	public Carrera() {

	}

	public BigDecimal getDividendo(Apuesta Apuesta) {
		return null;
	}

	public Resultado getResultado(Participante Participante) {
		return null;
	}

	public void setDividendo(BigDecimal dividendo) {
		this.dividendo = dividendo;
	}

	public BigDecimal getDividendo() {
		return dividendo;
	}

}